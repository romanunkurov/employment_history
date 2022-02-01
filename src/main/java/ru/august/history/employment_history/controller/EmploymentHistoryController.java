package ru.august.history.employment_history.controller;

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
@RequestMapping("/api/v1/employees")
public class EmploymentHistoryController {

    private final EmploymentHistoryService service;

    public EmploymentHistoryController(EmploymentHistoryService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllEmployees() {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.getEmployeeById(employeeId));
    }

    @PutMapping("{id}")
    public ResponseEntity<Person> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                 @Valid @RequestBody PersonDTO employeeDetails) {
        Person employee = service.updateEmployeeData(employeeId, employeeDetails);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createEmployee(@RequestBody @Valid PersonDTO dto) {
        return ResponseEntity.ok().body(service.createEmployee(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.deleteEmployee(employeeId));
    }

    @GetMapping("{id}/work/{work_id}")
    public ResponseEntity<Work> getEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                          @PathVariable(value = "work_id") Long workId) {
        return ResponseEntity.ok().body(service.selectEmployeeWork(employeeId, workId));
    }

    @PostMapping("/{id}/work")
    public ResponseEntity<Work> addNewWorkToWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                           @Valid @RequestBody WorkDTO dto) {
        return ResponseEntity.ok().body(service.addNewWork(employeeId, dto));
    }

    @GetMapping("/{id}/work")
    public ResponseEntity<List<Work>> getEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok().body(service.selectEmployeeWork(employeeId));
    }

    @PostMapping("/{id}/work/{work_id}")
    public ResponseEntity<Work> addNewWorkToWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                           @PathVariable(value = "work_id") Long workId,
                                           @Valid @RequestBody WorkDTO dto) {
        return ResponseEntity.ok().body(service.updateWorkingHistory(employeeId, workId, dto));
    }

    @DeleteMapping("/{id}/work/{work_id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeWorkingHistory(@PathVariable(value = "id") Long employeeId,
                                                                             @PathVariable(value = "work_id") Long workId) {
        return ResponseEntity.ok().body(service.deleteEmployeeWorkingHistory(employeeId, workId));
    }
}
