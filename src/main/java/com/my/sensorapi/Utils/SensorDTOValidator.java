package com.my.sensorapi.Utils;

import com.my.sensorapi.dto.SensorDTO;
import com.my.sensorapi.service.SensorService;
import com.my.sensorapi.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SensorDTOValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;

        try {
            sensorService.findByName(sensor.getName());
            errors.rejectValue("name", "FORBIDDEN_NAME_OF_SENSOR",
                    "The sensor with this name has already been registered!");

        } catch (EntityNotFoundException exception) {
            //do nothing because there is no sensor with the same name in system
        }
    }
}
