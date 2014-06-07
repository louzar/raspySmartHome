package com.dor.smarthome.app.sensors.types.interfaces;

import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.sensors.types.interfaces.Valueable;

/**
 *
 * Created by dor on 27.04.2014.
 */
public interface Sensor {

    Valueable getResponse();

    SensorType getType();

    Integer getIndexNumber();
}
