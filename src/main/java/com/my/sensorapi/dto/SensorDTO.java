package com.my.sensorapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SensorDTO {

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 symbols")
    private String name;
}
