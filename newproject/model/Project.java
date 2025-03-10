package com.example.newproject.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message =" your ID should be not empty")
    @Size(min = 3)
    private String ID;

    @NotEmpty(message = "title should be not empty")
    @Size(min = 9)
    private String title;

    @NotEmpty(message = "description should be not empty")
    @Size(min = 16)
    private String description;

    @NotEmpty(message = "status should be not empty")
    @Pattern(regexp = "Not Started|In Prosegess|Completed")
    private String status;

    @NotEmpty(message = "company name should be not empty")
    @Size(min = 7)
    private String companyName;
}
