package com.my.sensorapi.service;

import com.my.sensorapi.entity.Measurement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeasurementService {

    void add(Measurement measurement);

    List<Measurement> findAll();

    Long getRainyDayCount();
}
