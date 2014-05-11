package com.dor.ws.jsonobjs.inputs;

import com.dor.smarthome.app.sensors.types.SensorType;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 29.04.2014.
 */
public class SensorValueInput {

    private Integer sensorIndex;

    private SensorType sensorType;

    public Integer getSensorIndex() {
        return sensorIndex;
    }

    public void setSensorIndex(Integer sensorIndex) {
        this.sensorIndex = sensorIndex;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }
}
