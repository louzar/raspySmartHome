package com.dor.smarthome.app.db.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 07.06.2014.
 */
public abstract class AbstractDao<T, ID extends Serializable> {

    private EntityManager entityManager;

    private Class<T> classType;

    public AbstractDao() {
        classType = findEntityType();
    }

    @SuppressWarnings("unchecked")
    private Class<T> findEntityType() {
        Class<?> c = getClass();
        while (c.getGenericSuperclass() instanceof Class) {
            c = c.getSuperclass();
        }
        return (Class<T>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Class<T> getType() {
        return classType;
    }

    public final T read(ID id) {
        return entityManager.find(classType, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query tq = entityManager.createQuery("select t from " + getType().getName() + " t order by t.id");
        return tq.getResultList();
    }

    /** {@inheritDoc} */
    public final T persist(T transientObject) {
        try {
            getEntityManager().persist(transientObject);
            return transientObject;
        } catch (PersistenceException ex) {
            return null; // this line is never reached
        }
    }

    public final T merge(T transientObject) {
        try {
            return getEntityManager().merge(transientObject);
        } catch (PersistenceException ex) {
            return null; // this line is never reached
        }
    }

    public final List<T> mergeAll(Collection<T> transientObjects) {
        List<T> result = new ArrayList<T>();

        for (T transientObject : transientObjects) {
            T merge = merge(transientObject);
            if (merge != null) {
                result.add(merge);
            }
        }

        getEntityManager().flush();

        return result;
    }

    /** {@inheritDoc} */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /** {@inheritDoc} */
    public final EntityManager getEntityManager() {
        return entityManager;
    }
}
