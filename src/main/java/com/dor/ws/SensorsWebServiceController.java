package com.dor.ws;

import com.dor.smarthome.app.sensors.SensorsContainer;
import com.dor.smarthome.app.sensors.inerfaces.Sensor;
import com.dor.smarthome.app.sensors.timers.SensorsHistoryManagement;
import com.dor.ws.jsonobjs.inputs.SensorHistoryInput;
import com.dor.ws.jsonobjs.inputs.SensorValueInput;
import com.dor.ws.jsonobjs.outputs.SensorHistoryOutput;
import com.dor.ws.jsonobjs.outputs.SensorTypesOutput;
import com.dor.ws.jsonobjs.outputs.SensorValueOutput;
import com.dor.ws.urls.WebServiceURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.UnsupportedDataTypeException;
import java.io.IOException;
import java.util.List;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 28.04.2014.
 */
@Controller
@RequestMapping("/smartraspy")
public class SensorsWebServiceController {

    @Autowired
    private SensorsContainer sensorsContainer;

    @Autowired
    private SensorsHistoryManagement sensorsHistoryManagement;

    @RequestMapping(value = WebServiceURLs.GET_SENSORS_TYPES, method = RequestMethod.GET)
    public
    @ResponseBody
    List<SensorTypesOutput> getSensorTypes() throws IOException {
        return SensorTypesOutput.getStaticOutput();
    }


    @RequestMapping(value = WebServiceURLs.GET_SENSOR_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    SensorValueOutput getSensorValue(@RequestBody SensorValueInput input) throws UnsupportedDataTypeException {
        if (input.getSensorType() != null) {
            switch (input.getSensorType()) {
                case DHT11:
                    Sensor dht11Sensor = sensorsContainer.getExistingSensor(input.getSensorType(), input.getSensorIndex());
                    return new SensorValueOutput(dht11Sensor.getResponse());
                default:
                    throw new UnsupportedDataTypeException("Sensor type is undefined!");
            }
        }
        return new SensorValueOutput(null);
    }

    @RequestMapping(value = WebServiceURLs.GET_SENSOR_HISTORY, method = RequestMethod.POST)
    public
    @ResponseBody
    SensorHistoryOutput getSensorHistory(@RequestBody SensorHistoryInput input) {
        return new SensorHistoryOutput(sensorsHistoryManagement.getResponsesHistory(input.getSensorType(), input.getSensorIndex()));
    }
}
