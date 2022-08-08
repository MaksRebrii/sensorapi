package com.my.sensorapi.Utils;

import com.my.sensorapi.dto.MeasurementDTO;
import com.my.sensorapi.service.SensorService;
import com.my.sensorapi.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MeasurementDTOValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;


        try {
            sensorService.findByName(measurementDTO.getSensor().getName());
        } catch (EntityNotFoundException exception){
            errors.rejectValue("sensor","FORBIDDEN_NAME_OF_SENSOR",
                    exception.getMessage());
        }
    }
}
