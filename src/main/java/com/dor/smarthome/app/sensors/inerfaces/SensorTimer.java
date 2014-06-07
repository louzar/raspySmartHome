package com.dor.smarthome.app.sensors.inerfaces;

import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.sensors.types.interfaces.Valueable;

import java.util.List;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 03.05.2014.
 */
public interface SensorTimer {

    List<Valueable> getResponsesHistory(SensorType sensorType, Integer index);
}
