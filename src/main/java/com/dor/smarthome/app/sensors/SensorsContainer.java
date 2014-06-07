package com.dor.smarthome.app.sensors;

import com.dor.smarthome.app.sensors.types.bmp085.BMP085Sensor;
import com.dor.smarthome.app.sensors.types.interfaces.AbstractSensor;
import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.sensors.types.dht11.DHT11Sensor;
import com.dor.smarthome.app.status.NotifiersContainer;
import com.dor.smarthome.app.status.interfaces.Notifiable;
import com.dor.smarthome.utils.props.PropertiesManager;
import com.dor.smarthome.utils.props.values.SensorPropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by dor on 27.04.2014.
 */
@Service("sensorsBean")
@DependsOn("notifiersContainer")
public class SensorsContainer {

    private Map<SensorType, Map<Integer, AbstractSensor>> sensors = new HashMap<>();

    @Autowired
    private PropertiesManager propertiesManager;

    @Autowired
    private NotifiersContainer notifiersContainer;

    @PostConstruct
    public void init() {
        SensorType[] types = SensorType.values();
        for (SensorType type : types) {
            int number = propertiesManager.getSensorNumber(type);
            for (int i = 0; i < number; i++) {
                SensorPropertyValue value = propertiesManager.getSensorPropertyValue(type, i);
                AbstractSensor sensor = getNewSensor(value, type);
                addSensor(sensor);
            }
        }
    }

    public void addSensor(AbstractSensor sensor) {
        Map<Integer, AbstractSensor> integerSensorMap = sensors.get(sensor.getType());
        if (integerSensorMap == null) {
            integerSensorMap = new HashMap<>();
            sensors.put(sensor.getType(), integerSensorMap);
        }
        integerSensorMap.put(sensor.getIndexNumber(), sensor);
    }

    public AbstractSensor getExistingSensor(SensorType sensorType, Integer index) {
        Map<Integer, AbstractSensor> integerSensorMap = sensors.get(sensorType);
        if (integerSensorMap != null) {
            return integerSensorMap.get(index);
        }
        return null;
    }

    private AbstractSensor getNewSensor(SensorPropertyValue sensorPropertyValue, SensorType sensorType) {
        AbstractSensor sensor = null;
        Integer index;
        Integer gpio;
        switch (sensorType) {
            case DHT11:
                index = Integer.valueOf(sensorPropertyValue.getIndex());
                gpio = Integer.valueOf(sensorPropertyValue.getGpio());
                Notifiable notifier = notifiersContainer.getExistingNotifier(
                        sensorPropertyValue.getNotifierType(), Integer.valueOf(sensorPropertyValue.getNotifierIndex()));
                sensor = DHT11Sensor.getInstance(index, gpio, notifier);
                break;
            case BMP085:
                index = Integer.valueOf(sensorPropertyValue.getIndex());
                sensor = BMP085Sensor.getInstance(index);
                break;
        }
        return sensor;
    }

    public Map<Integer, AbstractSensor> getSensorsByType(SensorType sensorType) {
        return sensors.get(sensorType) != null ? sensors.get(sensorType) : new HashMap<>();
    }
}
