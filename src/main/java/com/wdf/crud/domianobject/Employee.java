package com.wdf.crud.domianobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "firstName can not be null!")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "lastName can not be null!")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "age can not be null!")
    private Integer age;

}
