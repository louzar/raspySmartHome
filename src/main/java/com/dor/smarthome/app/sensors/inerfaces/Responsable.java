package com.dor.smarthome.app.sensors.inerfaces;

import com.dor.smarthome.app.sensors.types.SensorType;

/**
 *
 * Created by dor on 27.04.2014.
 */
public interface Responsable {

    Valueable getResponse();

    SensorType getType();

    Integer getIndexNumber();
}
