package com.dor.smarthome.app.sensors.types.dht11;

import com.dor.smarthome.app.db.daos.AbstractDao;
import com.dor.smarthome.app.db.persistance.TemperatureHistoryPO;
import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.sensors.types.interfaces.AbstractSensor;
import com.dor.smarthome.app.sensors.types.interfaces.Valueable;
import com.dor.smarthome.app.status.interfaces.Notifiable;
import com.dor.smarthome.utils.SensorUtils;

import java.util.Date;

/**
 *
 * Created by dor on 27.04.2014.
 */
public class DHT11Sensor extends AbstractSensor {

    private DHT11Reader reader;

    private int gpio;

    private Notifiable notifier;

    private AbstractDao temperatureHistoryDao;

    private DHT11Sensor(int indexNumberParam, SensorType sensorType, int gpio, Notifiable notifier) {
        super(indexNumberParam, sensorType);
        this.gpio = gpio;
        this.notifier = notifier;
        reader = new DHT11Reader();
    }

    public static DHT11Sensor getInstance(int indexNumberParam, int gpio, Notifiable notifier, AbstractDao dao) {
        DHT11Sensor dht11Sensor = new DHT11Sensor(indexNumberParam, SensorType.DHT11, gpio, notifier);
        dht11Sensor.temperatureHistoryDao = dao;
        return dht11Sensor;
    }

    @Override
    public Value getResponse() {
        notifyAtResponse();
        int[] response = null;
        try {
            response = reader.getClearResponse(gpio);
        } catch (Throwable error) {
            System.out.println("An error! " + error.getMessage());
        }
        if (response != null && response.length > 3) {
            TemperatureHistoryPO temperatureHistoryPO = new TemperatureHistoryPO();
            temperatureHistoryPO.setMeasureDate(new Date());
            temperatureHistoryPO.setTemperature(response[2]);
            temperatureHistoryDao.merge(temperatureHistoryPO);
            return new Value(response[2], response[0], SensorUtils.getDateFormat().format(new Date()));
        }
        return new Value(999, 999, null);
    }

    private void notifyAtResponse() {
        if (notifier != null) {
            notifier.notifyEvent();
        }
    }

    public static class Value implements Valueable {

        private int temp;

        private int hum;

        private String date;

        public Value(int temperature, int humidity, String date) {
            this.temp = temperature;
            this.hum = humidity;
            this.date = date;
        }

        public int getTemp() {
            return temp;
        }

        public int getHum() {
            return hum;
        }

        public String getDate() {
            return date;
        }
    }
}
