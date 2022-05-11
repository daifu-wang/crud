package com.wdf.crud.transfer;

import com.wdf.crud.domianobject.Employee;
import com.wdf.crud.dto.EmployeeDto;

public class EmployeeTransfer {

    public static Employee makeEmployee(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setAge(dto.getAge());
        return employee;
    }

    public static EmployeeDto makeEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employee.getAge());
        return employeeDto;
    }
}
