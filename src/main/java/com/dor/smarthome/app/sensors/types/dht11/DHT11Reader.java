package com.dor.smarthome.app.sensors.types.dht11;

/**
 *
 * Created by dor on 26.04.2014.
 */
public class DHT11Reader {

    private static final int FAKED_RESULT_999 = 999;

    private static final int FAKED_RESULT_ZERO = 0;

    private static final int ATTEMPTS_TO_FAIL = 5;

    static {
        System.load("/home/pi/java_projects/dht11/read-sensor.so");
    }

    public native int[] getResponse(int gpio);

    public int[] getClearResponse(int gpio) {
        int[] response = getResponse(gpio);
        int attempts = 0;
        while (!checkResponse(response)) {
            attempts++;
            if (attempts >= ATTEMPTS_TO_FAIL) {
                return null;
            }
            try {
                Thread.sleep(4000);
                response = getResponse(gpio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private boolean checkResponse(int[] response) {
        if (response != null && response.length >= 4) {
            if (response[0] == FAKED_RESULT_999 || response[2] == FAKED_RESULT_999) {
                return false;
            }
            return !(response[0] == response[2] && response[0] == FAKED_RESULT_ZERO);
        }
        return false;
    }
}
