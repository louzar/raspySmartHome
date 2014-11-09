package com.dor.smarthome.app.db.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contains all DAOs.
 * Created by andrew on 09.11.14.
 */
@Service("daoHolder")
public class DaoHolder {

    @Autowired
    private TemperatureHistoryDao temperatureHistoryDao;

    @Autowired
    private HumidityHistoryDao humidityHistoryDao;

    public TemperatureHistoryDao getTemperatureHistoryDao() {
        return temperatureHistoryDao;
    }

    public HumidityHistoryDao getHumidityHistoryDao() {
        return humidityHistoryDao;
    }
}
