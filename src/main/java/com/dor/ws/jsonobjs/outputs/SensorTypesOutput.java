package com.dor.ws.jsonobjs.outputs;

import com.dor.smarthome.app.sensors.types.SensorType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 28.04.2014.
 */
public class SensorTypesOutput implements Serializable {

    private static final long serialVersionUID = -6872411413031289057L;

    private static final List<SensorTypesOutput> sensorTypes;

    static {
        sensorTypes = new ArrayList<>();
        for (SensorType stype : SensorType.values()) {
            SensorTypesOutput sensorTypesOutput = new SensorTypesOutput();
            sensorTypesOutput.setType(stype.name());
            sensorTypes.add(sensorTypesOutput);
        }
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<SensorTypesOutput> getStaticOutput() {
        return sensorTypes;
    }
}
