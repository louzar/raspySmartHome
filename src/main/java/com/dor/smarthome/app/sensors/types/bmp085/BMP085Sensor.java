package com.dor.smarthome.app.sensors.types.bmp085;

import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.app.sensors.types.interfaces.AbstractSensor;
import com.dor.smarthome.app.sensors.types.interfaces.Valueable;
import com.dor.smarthome.utils.SensorUtils;

import java.util.Date;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 07.06.2014.
 */
public class BMP085Sensor extends AbstractSensor {

    private BMP085Reader reader;

    private static final double PASCAL_TO_MM_HG = 0.0075;

    protected BMP085Sensor(int indexNumber, SensorType type) {
        super(indexNumber, type);
        reader = new BMP085Reader();
    }

    public static BMP085Sensor getInstance(int indexNumberParam) {
        return new BMP085Sensor(indexNumberParam, SensorType.BMP085);
    }

    @Override
    public Valueable getResponse() {
        BMP085Reader.BMP085Response data = reader.getClearResponse();
        if (data != null) {
            return new Value(data.getTemperature(), data.getPressure() * PASCAL_TO_MM_HG, SensorUtils.getDateFormat().format(new Date()));
        }
        return new Value(999, 999, null);
    }

    public static class Value implements Valueable {

        private double temp;

        private double pres;

        private String date;

        public Value(double temperature, double pressure, String date) {
            this.temp = temperature;
            this.pres = pressure;
            this.date = date;
        }

        public double getTemp() {
            return temp;
        }

        public double getPres() {
            return pres;
        }

        public String getDate() {
            return date;
        }
    }
}
