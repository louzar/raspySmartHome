package com.dor.smarthome.utils.props.values;

import com.dor.smarthome.app.status.types.StatusNotifierType;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 11.05.2014.
 */
public class SensorPropertyValue {
    private String index;
    private String gpio;
    private String description;
    private StatusNotifierType notifierType;
    private String notifierIndex;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getGpio() {
        return gpio;
    }

    public void setGpio(String gpio) {
        this.gpio = gpio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusNotifierType getNotifierType() {
        return notifierType;
    }

    public void setNotifierType(StatusNotifierType notifierType) {
        this.notifierType = notifierType;
    }

    public String getNotifierIndex() {
        return notifierIndex;
    }

    public void setNotifierIndex(String notifierIndex) {
        this.notifierIndex = notifierIndex;
    }
}
