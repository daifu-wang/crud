package com.wdf.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    @NotNull(message = "firstName can not be null!")
    private String firstName;

    @NotNull(message = "lastName can not be null!")
    private String lastName;

    @NotNull(message = "age can not be null!")
    private Integer age;
}
