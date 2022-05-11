package com.wdf.crud.service;

import com.wdf.crud.domianobject.Employee;
import com.wdf.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.ELException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    private String deletedMsg = "delete successfully,id:";

    public Employee saveEmployee(Employee employee){
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return repository.findById(id);
    }

    public String deleteEmployeeById(Long id){
        repository.deleteById(id);
        return deletedMsg+id;
    }

    public Employee updateEmployee(Employee employee){

        Employee oldEmployee = repository.findById(employee.getId()).orElseThrow();
        return repository.save(resetValue(employee,oldEmployee));
    }

    private Employee resetValue(Employee newEmployee,Employee oldEmployee){
        oldEmployee.setFirstName(newEmployee.getFirstName());
        oldEmployee.setLastName(newEmployee.getLastName());
        oldEmployee.setAge(newEmployee.getAge());
        return oldEmployee;
    }
}
