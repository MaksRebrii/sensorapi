package com.my.sensorapi.service.impl;

import com.my.sensorapi.entity.Measurement;
import com.my.sensorapi.repository.MeasurementRepository;
import com.my.sensorapi.service.MeasurementService;
import com.my.sensorapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Transactional
    @Override
    public void add(Measurement measurement) {
        enrichMeasurement(measurement);

        measurementRepository.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Long getRainyDayCount() {
        return this.findAll().stream()
                .filter(Measurement::getRaining)
                .count();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setDateTime(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()));
    }
}
