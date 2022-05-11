package com.wdf.crud.repository;

import com.wdf.crud.domianobject.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
