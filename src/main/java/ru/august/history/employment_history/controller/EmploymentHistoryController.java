package ru.august.history.employment_history.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.august.history.employment_history.dto.PersonDTO;
import ru.august.history.employment_history.repository.PersonRepository;
import ru.august.history.employment_history.model.Person;
import ru.august.history.employment_history.model.Work;
import ru.august.history.employment_history.exceptions.ResourceNotFoundException;
import ru.august.history.employment_history.service.EmploymentHistoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mycrudapp")
public class EmploymentHistoryController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private EmploymentHistoryService service;

    @GetMapping
    public List<Person> getAllEmployees() {
        return repository.findAll();
    }


    //get employee by id with his/her job history
    @GetMapping("employees/{id}")
    public ResponseEntity<Person> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Person employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                    @RequestBody Person employeeDetails) throws ResourceNotFoundException {
        Person employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Person updatedEmployee = repository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //create and add new employee
    @PostMapping("/employees")
    public Person createEmployee(@RequestBody PersonDTO dto) {
        return service.createEmployee(dto);
    }

    //delete existing employee
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Person employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        repository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("employees/{id}/work/{work_id}")
    public ResponseEntity<Work> getEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                          @PathVariable(value = "work_id") Long workId)
            throws ResourceNotFoundException {
        Person employee = repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        Work work =  employee.getWorkingList().get(Math.toIntExact(workId));

        return ResponseEntity.ok().body(work);
    }





}
