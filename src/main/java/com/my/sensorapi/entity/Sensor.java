package com.my.sensorapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "sensors")
@Data
public class Sensor implements Serializable { //TODO why sensor should be serializable?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 symbols")
    private String name;
}
