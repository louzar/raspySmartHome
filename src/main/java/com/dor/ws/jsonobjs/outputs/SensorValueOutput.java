package com.dor.ws.jsonobjs.outputs;

import com.dor.smarthome.app.sensors.types.interfaces.Valueable;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 29.04.2014.
 */
public class SensorValueOutput {

    private Valueable value;

    public SensorValueOutput(Valueable value) {
        this.value = value;
    }

    public Valueable getValue() {
        return value;
    }
}
