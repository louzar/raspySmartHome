package com.dor.smarthome.app.db.daos;

import com.dor.smarthome.app.db.persistance.TemperatureHistoryPO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andrew on 26.10.14.
 */
public class TemperatureHistoryDao extends AbstractDao<TemperatureHistoryPO, Long> {

    /**
     * {@inheritDoc}
     */
    @PersistenceContext(unitName = "smartUnitName")
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}
