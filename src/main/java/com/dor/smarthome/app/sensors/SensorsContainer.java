package com.dor.smarthome.app.sensors;

import com.dor.smarthome.app.sensors.inerfaces.Sensor;
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

    private Map<SensorType, Map<Integer, Sensor>> sensors = new HashMap<>();

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
                Sensor sensor = getNewSensor(value, type);
                addSensor(sensor);
            }
        }
    }

    public void addSensor(Sensor sensor) {
        Map<Integer, Sensor> integerSensorMap = sensors.get(sensor.getType());
        if (integerSensorMap == null) {
            integerSensorMap = new HashMap<>();
            sensors.put(sensor.getType(), integerSensorMap);
        }
        integerSensorMap.put(sensor.getIndexNumber(), sensor);
    }

    public Sensor getExistingSensor(SensorType sensorType, Integer index) {
        Map<Integer, Sensor> integerSensorMap = sensors.get(sensorType);
        if (integerSensorMap != null) {
            return integerSensorMap.get(index);
        }
        return null;
    }

    private Sensor getNewSensor(SensorPropertyValue sensorPropertyValue, SensorType sensorType) {
        Sensor sensor = null;
        switch (sensorType) {
            case DHT11:
                Integer index = Integer.valueOf(sensorPropertyValue.getIndex());
                Integer gpio = Integer.valueOf(sensorPropertyValue.getGpio());
                Notifiable notifier = notifiersContainer.getExistingNotifier(
                        sensorPropertyValue.getNotifierType(), Integer.valueOf(sensorPropertyValue.getNotifierIndex()));
                sensor = DHT11Sensor.getInstance(index, gpio, notifier);
                break;
        }
        return sensor;
    }

    public Map<Integer, Sensor> getSensorsByType(SensorType sensorType) {
        return sensors.get(sensorType) != null ? sensors.get(sensorType) : new HashMap<>();
    }
}
