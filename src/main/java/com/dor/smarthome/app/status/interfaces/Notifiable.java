package com.dor.smarthome.app.status.interfaces;

import com.dor.smarthome.app.status.types.StatusNotifierType;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 11.05.2014.
 */
public interface Notifiable {

    void notifyEvent();

    int getIndex();

    StatusNotifierType getType();
}
