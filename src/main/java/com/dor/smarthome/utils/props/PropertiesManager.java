package com.dor.smarthome.utils.props;

import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.status.types.StatusNotifierType;
import com.dor.smarthome.utils.props.values.NotifierPropertyValue;
import com.dor.smarthome.utils.props.values.SensorPropertyValue;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 30.04.2014.
 */
@Service("propertyManagerBean")
public class PropertiesManager {

    private static final String PROPERTY_FILE_NAME = "smart.properties";

    private InputStream propertyInputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);

    private static final char PROPERTY_SEPARATOR = '.';
    private static final String PROPERTY_INDEX = "index";
    private static final String PROPERTY_GPIO = "gpio";
    private static final String PROPERTY_DESCRIPTION = "description";
    private static final String PROPERTY_SPEED = "speed";
    private static final String PROPERTY_NUMBER = "number";
    private static final String PROPERTY_TYPE = "type";
    private static final String PROPERTY_SIZE = "size";
    private static final String PROPERTY_INTERVAL = "interval";

    private static final String PROPERTY_SENSOR = "sensor";
    private static final String PROPERTY_NOTIFIER = "notifier";
    private static final String PROPERTY_SENSOR_PATTERN = PROPERTY_SENSOR + PROPERTY_SEPARATOR +
            "%s" + PROPERTY_SEPARATOR + PROPERTY_INDEX + "_%s" + PROPERTY_SEPARATOR;
    private static final String PROPERTY_SENSOR_PATTERN_INDEX = PROPERTY_SENSOR_PATTERN + PROPERTY_INDEX;
    private static final String PROPERTY_SENSOR_PATTERN_GPIO = PROPERTY_SENSOR_PATTERN + PROPERTY_GPIO;
    private static final String PROPERTY_SENSOR_PATTERN_DESCRIPTION = PROPERTY_SENSOR_PATTERN + PROPERTY_DESCRIPTION;
    private static final String PROPERTY_SENSOR_PATTERN_NOTIFIER_TYPE = PROPERTY_SENSOR_PATTERN +
            PROPERTY_NOTIFIER + PROPERTY_SEPARATOR + PROPERTY_TYPE;
    private static final String PROPERTY_SENSOR_PATTERN_NOTIFIER_INDEX = PROPERTY_SENSOR_PATTERN +
            PROPERTY_NOTIFIER + PROPERTY_SEPARATOR + PROPERTY_INDEX;
    private static final String PROPERTY_SENSOR_NUMBER_PATTERN = PROPERTY_SENSOR + PROPERTY_SEPARATOR +
            "%s" + PROPERTY_SEPARATOR + PROPERTY_NUMBER;


    private static final String PROPERTY_NOTIFIER_PATTERN = PROPERTY_NOTIFIER + PROPERTY_SEPARATOR +
            "%s" + PROPERTY_SEPARATOR + PROPERTY_INDEX + "_%s" + PROPERTY_SEPARATOR;
    private static final String PROPERTY_NOTIFIER_PATTERN_INDEX = PROPERTY_NOTIFIER_PATTERN + PROPERTY_INDEX;
    private static final String PROPERTY_NOTIFIER_PATTERN_GPIO = PROPERTY_NOTIFIER_PATTERN + PROPERTY_GPIO;
    private static final String PROPERTY_NOTIFIER_PATTERN_DESCRIPTION = PROPERTY_NOTIFIER_PATTERN + PROPERTY_DESCRIPTION;
    private static final String PROPERTY_NOTIFIER_PATTERN_SPEED = PROPERTY_NOTIFIER_PATTERN + PROPERTY_SPEED;
    private static final String PROPERTY_NOTIFIER_NUMBER_PATTERN = PROPERTY_NOTIFIER + PROPERTY_SEPARATOR +
            "%s" + PROPERTY_SEPARATOR + PROPERTY_NUMBER;

    private static final String PROPERTY_HISTORY = "history";
    public static final String PROPERTY_HISTORY_SIZE = PROPERTY_HISTORY + PROPERTY_SEPARATOR + PROPERTY_SIZE;
    public static final String PROPERTY_HISTORY_INREVAL = PROPERTY_HISTORY + PROPERTY_SEPARATOR + PROPERTY_INTERVAL;

    private Properties prop = new Properties();

    {
        try {
            prop.load(propertyInputStream);
        } catch (IOException e) {
            System.out.println("No such property file!");
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }

    public int getSensorNumber(SensorType sensorType) {
        String propertyName = String.format(PROPERTY_SENSOR_NUMBER_PATTERN, sensorType.name());
        String numberStr = getProperty(propertyName);
        return Integer.valueOf(numberStr);
    }

    public int getNotifierNumber(StatusNotifierType notifierType) {
        String propertyName = String.format(PROPERTY_NOTIFIER_NUMBER_PATTERN, notifierType.name());
        String numberStr = getProperty(propertyName);
        return Integer.valueOf(numberStr);
    }

    public SensorPropertyValue getSensorPropertyValue(SensorType sensorType, int index) {
        String indexString = String.format(PROPERTY_SENSOR_PATTERN_INDEX, sensorType.name(), index);
        String gpioString = String.format(PROPERTY_SENSOR_PATTERN_GPIO, sensorType.name(), index);
        String descriptionString = String.format(PROPERTY_SENSOR_PATTERN_DESCRIPTION, sensorType.name(), index);
        String notofierTypeString = String.format(PROPERTY_SENSOR_PATTERN_NOTIFIER_TYPE, sensorType.name(), index);
        String notofierIndexString = String.format(PROPERTY_SENSOR_PATTERN_NOTIFIER_INDEX, sensorType.name(), index);
        SensorPropertyValue sensorPropertyValue = new SensorPropertyValue();
        sensorPropertyValue.setDescription(getProperty(descriptionString));
        sensorPropertyValue.setGpio(getProperty(gpioString));
        sensorPropertyValue.setIndex(getProperty(indexString));
        String notifierType = getProperty(notofierTypeString);
        if (notifierType != null) {
            sensorPropertyValue.setNotifierType(StatusNotifierType.valueOf(notifierType));
        }
        sensorPropertyValue.setNotifierIndex(getProperty(notofierIndexString));
        return sensorPropertyValue;
    }

    public NotifierPropertyValue getNotifierPropertyValue(StatusNotifierType notifierType, int index) {
        String indexString = String.format(PROPERTY_NOTIFIER_PATTERN_INDEX, notifierType.name(), index);
        String gpioString = String.format(PROPERTY_NOTIFIER_PATTERN_GPIO, notifierType.name(), index);
        String descriptionString = String.format(PROPERTY_NOTIFIER_PATTERN_DESCRIPTION, notifierType.name(), index);
        String speedString = String.format(PROPERTY_NOTIFIER_PATTERN_SPEED, notifierType.name(), index);
        NotifierPropertyValue notifierPropertyValue = new NotifierPropertyValue();
        notifierPropertyValue.setDescription(getProperty(descriptionString));
        notifierPropertyValue.setGpio(getProperty(gpioString));
        notifierPropertyValue.setIndex(getProperty(indexString));
        notifierPropertyValue.setSpeed(getProperty(speedString));
        return notifierPropertyValue;
    }
}
