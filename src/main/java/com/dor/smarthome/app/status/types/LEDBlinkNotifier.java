package com.dor.smarthome.app.status.types;

import com.dor.smarthome.app.status.interfaces.Notifiable;
import com.dor.smarthome.utils.GPIOUtils;
import com.pi4j.io.gpio.*;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 11.05.2014.
 */
public class LEDBlinkNotifier implements Notifiable {

    private int index;

    private int gpio;

    private String description;

    private long duration;

    private final GpioPinDigitalOutput pin;

    private StatusNotifierType type;

    public LEDBlinkNotifier(int index, int gpio, String description, long duration, StatusNotifierType type) {
        this.index = index;
        this.gpio = gpio;
        this.description = description;
        this.duration = duration;
        this.type = type;
        GpioController GPIO_CONTROLLER = GpioFactory.getInstance();
        pin = GPIO_CONTROLLER.provisionDigitalOutputPin(GPIOUtils.getPinByGPIO(gpio), description, PinState.LOW);
    }

    @Override
    public void notifyEvent() {
        pin.pulse(duration, true);
    }

    @Override
    public int getIndex() {
        return index;
    }

    public int getGpio() {
        return gpio;
    }

    public String getDescription() {
        return description;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public StatusNotifierType getType() {
        return type;
    }
}
