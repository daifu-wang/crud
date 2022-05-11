package com.wdf.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdf.crud.domianobject.Employee;
import com.wdf.crud.service.EmployeeService;
import com.wdf.crud.transfer.EmployeeTransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    Employee employee1 = new Employee(1L,"Daifu","Wang",18);
    Employee employee2 = new Employee(2L,"Daifu2","Wang2",20);
    Employee employee3 = new Employee(3L,"Daifu3","Wang3",36);
    Employee employee4 = new Employee(4L,"Daifu4","Wang4",31);
    Employee employee5 = new Employee(null,"Daifu4","Wang4",31);
    Employee employee6 = new Employee(1L,"Daifu6","Wang6",66);

    @Test
    void save() throws Exception {

        Mockito.when(employeeService.saveEmployee(employee5)).thenReturn(employee4);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(employee5));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", equalTo("Daifu4")))
                .andExpect(jsonPath("$.id", equalTo(4)));


    }

    @Test
    void findAll() throws Exception {

        List<Employee> employeeList = List.of(this.employee1, employee2, employee3);

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].firstName", equalTo("Daifu2")));
    }

    @Test
    void getById() throws Exception {

        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee1));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Daifu")))
                .andExpect(jsonPath("$.lastName", equalTo("Wang")))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.age", equalTo(18)));
    }

    @Test
    void update() throws Exception {

        Mockito.when(employeeService.getEmployeeById(employee1.getId())).thenReturn(Optional.of(employee1));
        Mockito.when(employeeService.saveEmployee(employee6)).thenReturn(employee6);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(EmployeeTransfer.makeEmployeeDto(employee6)));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", equalTo("Daifu6")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void deleteById() throws Exception {

        Mockito.when(employeeService.deleteEmployeeById(employee1.getId())).thenReturn("delete successfully,id:1");
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("delete successfully,id:1")));
    }
}