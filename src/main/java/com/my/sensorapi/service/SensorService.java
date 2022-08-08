package com.my.sensorapi.service;

import com.my.sensorapi.entity.Sensor;

public interface SensorService {

    Sensor findByName(String name);

    void save(Sensor sensor);
}
