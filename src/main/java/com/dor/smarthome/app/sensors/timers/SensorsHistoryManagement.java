package com.dor.smarthome.app.sensors.timers;

import com.dor.smarthome.app.sensors.SensorsContainer;
import com.dor.smarthome.app.sensors.types.interfaces.AbstractSensor;
import com.dor.smarthome.app.sensors.inerfaces.SensorTimer;
import com.dor.smarthome.app.sensors.types.interfaces.Valueable;
import com.dor.smarthome.app.sensors.types.SensorType;
import com.dor.smarthome.utils.Pair;
import com.dor.smarthome.utils.props.PropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author: kolpakov.and@gmail.com
 * Created by dor on 04.05.2014.
 */
@Service("sensorsHistoryManagement")
@DependsOn("sensorsBean")
public class SensorsHistoryManagement implements SensorTimer {

    @Autowired
    private SensorsContainer sensorsContainer;

    @Autowired
    private PropertiesManager propertiesManager;

    private ConcurrentHashMap<Pair<Integer, SensorType>, Pair<AbstractSensor, CopyOnWriteArrayList<Valueable>>> map = new ConcurrentHashMap<>();

    private int timerInterval;

    private int historySize;

    private boolean isInited = false;

    @PostConstruct
    public void init() {
        System.out.println("bean inited");
        if (isInited) {
            return;
        }
        historySize = getHistorySize();
        timerInterval = getHistoryInterval();
        for (SensorType type : SensorType.values()) {
            Map<Integer, AbstractSensor> sensorsByType = sensorsContainer.getSensorsByType(type);
            for (Map.Entry<Integer, AbstractSensor> entry : sensorsByType.entrySet()) {
                map.put(new Pair<>(entry.getValue().getIndexNumber(), entry.getValue().getType()), new Pair<>(entry.getValue(), new CopyOnWriteArrayList<Valueable>()));
            }
        }

        Thread thread = new Thread(this::runHistory);
        thread.start();
        isInited = true;
    }

    private void runHistory() {
        while (true) {
            System.out.println("thread...");
            try {
                Thread.sleep(timerInterval);
                for (Pair<AbstractSensor, CopyOnWriteArrayList<Valueable>> value : map.values()) {
                    CopyOnWriteArrayList<Valueable> list = value.getValue();
                    if (list.size() >= historySize) {
                        list.remove(0);
                    }
                    list.add(value.getKey().getResponse());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Valueable> getResponsesHistory(SensorType sensorType, Integer index) {
        return new ArrayList<>(map.get(new Pair<>(index, sensorType)).getValue());
    }

    public int getHistorySize() {
        String size = propertiesManager.getProperty(PropertiesManager.PROPERTY_HISTORY_SIZE);
        return Integer.valueOf(size);
    }

    public int getHistoryInterval() {
        String interval = propertiesManager.getProperty(PropertiesManager.PROPERTY_HISTORY_INREVAL);
        return Integer.valueOf(interval);
    }
}
