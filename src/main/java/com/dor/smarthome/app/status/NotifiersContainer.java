package com.dor.smarthome.app.status;

import com.dor.smarthome.app.status.interfaces.Notifiable;
import com.dor.smarthome.app.status.types.LEDBlinkNotifier;
import com.dor.smarthome.app.status.types.StatusNotifierType;
import com.dor.smarthome.utils.props.PropertiesManager;
import com.dor.smarthome.utils.props.values.NotifierPropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 11.05.2014.
 */
@Service("notifiersContainer")
public class NotifiersContainer {

    private Map<StatusNotifierType, Map<Integer, Notifiable>> notifiers = new HashMap<>();

    @Autowired
    private PropertiesManager propertiesManager;

    @PostConstruct
    public void init() {
        StatusNotifierType[] types = StatusNotifierType.values();
        for (StatusNotifierType type : types) {
            int number = propertiesManager.getNotifierNumber(type);
            for (int i = 0; i < number; i++) {
                NotifierPropertyValue value = propertiesManager.getNotifierPropertyValue(type, i);
                Notifiable notifier = getNotifierByType(value, type);
                addNotifier(notifier);
            }
        }
    }

    public Notifiable getExistingNotifier(StatusNotifierType type, Integer index) {
        Map<Integer, Notifiable> integerNotifiableMap = notifiers.get(type);
        if (integerNotifiableMap != null) {
            return integerNotifiableMap.get(index);
        }
        return null;
    }

    public Map<Integer, Notifiable> getNotifiersByType(StatusNotifierType type) {
        return notifiers.get(type) != null ? notifiers.get(type) : new HashMap<>() ;
    }

    private Notifiable getNotifierByType(NotifierPropertyValue value, StatusNotifierType type) {
        switch (type) {
            case LED_BLINK:
                int index = Integer.valueOf(value.getIndex());
                long speed = Long.valueOf(value.getSpeed());
                int gpio = Integer.valueOf(value.getGpio());
                return new LEDBlinkNotifier(index, gpio, value.getDescription(), speed, StatusNotifierType.LED_BLINK);
            default:
                return null;
        }
    }

    private void addNotifier(Notifiable notifier) {
        Map<Integer, Notifiable> integerNotifiableMap = notifiers.get(notifier.getType());
        if (integerNotifiableMap == null) {
            integerNotifiableMap = new HashMap<>();
            notifiers.put(notifier.getType(), integerNotifiableMap);
        }
        integerNotifiableMap.put(notifier.getIndex(), notifier);
    }


}
