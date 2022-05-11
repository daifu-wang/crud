package com.wdf.crud.controller;

import com.wdf.crud.domianobject.Employee;
import com.wdf.crud.dto.EmployeeDto;
import com.wdf.crud.service.EmployeeService;
import com.wdf.crud.transfer.EmployeeTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public EmployeeDto save(@RequestBody @Valid EmployeeDto employeeDto){

        Employee employee = employeeService.saveEmployee( EmployeeTransfer.makeEmployee(employeeDto));
        return EmployeeTransfer.makeEmployeeDto(employee);
    }

    @GetMapping()
    public List<EmployeeDto> FindAll(){
        List<Employee> employeesList = employeeService.getAllEmployees();
        return employeesList.stream().map(EmployeeTransfer::makeEmployeeDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable("id") Long id){
        Optional<Employee> optional = employeeService.getEmployeeById(id);
        if (optional.isEmpty()){
            return null;
        }
        return EmployeeTransfer.makeEmployeeDto(optional.get());
    }

    @PutMapping
    public EmployeeDto update(@RequestBody @Valid EmployeeDto employeeDto){
        Employee employee = employeeService.updateEmployee(EmployeeTransfer.makeEmployee(employeeDto));
        return EmployeeTransfer.makeEmployeeDto(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return employeeService.deleteEmployeeById(id);
    }

}
