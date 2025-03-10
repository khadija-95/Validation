package com.example.event2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "your id should be not empty")
    @Size(min = 3)
    private String id;

    @NotEmpty(message = "description should be not empty")
    @Size(min = 16)
    private String description;

    @NotNull(message = "capacity should be not empty")
    @Size(min = 25)
    @Digits(integer = 10,fraction = 0, message = "Should be number")
    private int capacity;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
