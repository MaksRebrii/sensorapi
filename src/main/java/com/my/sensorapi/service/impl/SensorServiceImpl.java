package com.my.sensorapi.service.impl;

import com.my.sensorapi.entity.Sensor;
import com.my.sensorapi.repository.SensorRepository;
import com.my.sensorapi.service.SensorService;
import com.my.sensorapi.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Override
    public Sensor findByName(String name) {
        return sensorRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cannot find sensor with name " + name));
    }

    @Transactional
    @Override
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
