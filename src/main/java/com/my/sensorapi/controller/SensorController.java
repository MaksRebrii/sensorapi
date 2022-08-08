package com.my.sensorapi.controller;

import com.my.sensorapi.Utils.SensorDTOValidator;
import com.my.sensorapi.dto.SensorDTO;
import com.my.sensorapi.entity.Sensor;
import com.my.sensorapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final SensorDTOValidator sensorDTOValidator;
    private final ModelMapper modelMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(sensorDTOValidator);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSensor(@RequestBody @Valid SensorDTO sensorDTO) {
        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorService.save(sensorToAdd);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
