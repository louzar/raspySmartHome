package com.dor.smarthome.app.db.daos;

import com.dor.smarthome.app.db.persistance.TemperatureHistoryPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andrew on 26.10.14.
 */
public class TemperatureHistoryDao extends AbstractDao<TemperatureHistoryPO, Long> {

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
