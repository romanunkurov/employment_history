package ru.august.history.employment_history.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import ru.august.history.employment_history.model.Employee;
import ru.august.history.employment_history.service.EmploymentHistoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmploymentHistoryControllerTest {

    private EmploymentHistoryService service;
    private EmploymentHistoryController controller;

    @Before
    public void setUp() {
        service = mock(EmploymentHistoryService.class);
        controller = new EmploymentHistoryController(service);
    }

    @Test
    public void getAllEmployees_positiveTest() {
        List<Employee> persons = new ArrayList<>();
        persons.add(new Employee());

        when(service.getAllEmployees()).thenReturn(persons);
        ResponseEntity<List<Employee>> listResponseEntity = controller.getAllEmployees();

        assertNotNull(listResponseEntity.getBody());
    }

    @Test(expected = RuntimeException.class)
    public void getAllEmployees_negativeTest() {
        when(service.getAllEmployees()).thenThrow(new RuntimeException());
        controller.getAllEmployees();
    }

    @Test
    public void getEmployeeById_positiveTest() {
        Employee expectedEmployee = new Employee();
        when(service.getEmployeeById(anyLong())).thenReturn(expectedEmployee);
        ResponseEntity<Employee> responseEntity = controller.getEmployeeById(1L);
        assertNotNull(responseEntity.getBody());
    }

    @Test(expected = RuntimeException.class)
    public void getEmployeeById_negativeTest() {
        when(service.getEmployeeById(anyLong())).thenThrow(new RuntimeException());
        controller.getEmployeeById(2L);
    }
}
