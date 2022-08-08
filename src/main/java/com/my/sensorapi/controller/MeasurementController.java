package com.my.sensorapi.controller;

import com.my.sensorapi.Utils.MeasurementDTOValidator;
import com.my.sensorapi.dto.MeasurementDTO;
import com.my.sensorapi.entity.Measurement;
import com.my.sensorapi.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementDTOValidator measurementDTOValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(measurementDTOValidator);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid MeasurementDTO measurementDTO) {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementService.add(measurementToAdd);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeasurementDTO> findAll() {
        return measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    @ResponseStatus(HttpStatus.OK)
    public Long getRainyDaysCount() {
        return measurementService.getRainyDayCount();
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
