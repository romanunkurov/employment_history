package ru.august.history.employment_history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.august.history.employment_history.dto.PersonDTO;
import ru.august.history.employment_history.dto.WorkDTO;
import ru.august.history.employment_history.model.Person;
import ru.august.history.employment_history.model.Work;
import ru.august.history.employment_history.service.EmploymentHistoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class EmploymentHistoryController {

    @Autowired
    private EmploymentHistoryService service;

    @GetMapping("employees")
    public ResponseEntity<List<Person>> getAllEmployees() {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Person> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.getEmployeeById(employeeId));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                 @Valid @RequestBody PersonDTO employeeDetails) {
        Person employee = service.updateEmployeeData(employeeId, employeeDetails);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees/create")
    public ResponseEntity<Person> createEmployee(@RequestBody @Valid PersonDTO dto) {
        return ResponseEntity.ok().body(service.createEmployee(dto));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.deleteEmployee(employeeId));
    }

    @GetMapping("employees/{id}/work/{work_id}")
    public ResponseEntity<Work> getEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                          @PathVariable(value = "work_id") Long workId) {
        return ResponseEntity.ok().body(service.selectEmployeeWork(employeeId, workId));
    }

    @PostMapping("employees/{id}/work")
    public ResponseEntity<Work> addNewWorkToWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                           @Valid @RequestBody WorkDTO dto) {
        return ResponseEntity.ok().body(service.addNewWork(employeeId, dto));
    }

    @GetMapping("employees/{id}/work")
    public ResponseEntity<List<Work>> getEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.selectEmployeeWork(employeeId));
    }

    @PostMapping("employees/{id}/work/{work_id}")
    public ResponseEntity<Work> addNewWorkToWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                           @PathVariable(value = "work_id") Long workId,
                                           @Valid @RequestBody WorkDTO dto) {
        return ResponseEntity.ok().body(service.updateWorkingHistory(employeeId, workId, dto));
    }

    @DeleteMapping("employees/{id}/work/{work_id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                                             @PathVariable(value = "work_id") Long workId) {
        return ResponseEntity.ok().body(service.deleteEmployeeWorkingHistory(employeeId, workId));
    }
}
