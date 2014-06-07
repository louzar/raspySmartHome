package com.dor.smarthome.app.sensors.types.interfaces;

import com.dor.smarthome.app.sensors.types.SensorType;

/**
 *
 * Created by dor on 27.04.2014.
 */
public abstract class AbstractSensor implements Sensor {

    private final int indexNumber;

    private final SensorType type;

    protected AbstractSensor(int indexNumber, SensorType type) {
        this.indexNumber = indexNumber;
        this.type = type;
    }

    @Override
    public Integer getIndexNumber() {
        return indexNumber;
    }

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSensor sensor = (AbstractSensor) o;

        if (indexNumber != sensor.indexNumber) return false;
        if (type != sensor.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNumber;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
