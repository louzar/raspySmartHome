package com.dor.smarthome.app.db.daos;

import com.dor.smarthome.app.db.persistance.HumidityHistoryPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andrew on 09.11.14.
 */
public class HumidityHistoryDao extends AbstractDao<HumidityHistoryPO, Long> {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * {@inheritDoc}
     */
    @PersistenceContext(unitName = "smartUnitName")
    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}
