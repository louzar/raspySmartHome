package com.dor.ws.jsonobjs.outputs;

import com.dor.smarthome.app.sensors.types.interfaces.Valueable;

import java.util.List;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 29.04.2014.
 */
public class SensorHistoryOutput {

    private List<Valueable> history;

    public SensorHistoryOutput(List<Valueable> history) {
        this.history = history;
    }

    public List<Valueable> getHistory() {
        return history;
    }
}
