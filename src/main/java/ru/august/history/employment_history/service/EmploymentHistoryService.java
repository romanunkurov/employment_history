package ru.august.history.employment_history.service;

import org.springframework.stereotype.Service;
import ru.august.history.employment_history.dto.EmployeeDTO;
import ru.august.history.employment_history.dto.WorkDTO;
import ru.august.history.employment_history.model.Employee;
import ru.august.history.employment_history.model.Work;
import ru.august.history.employment_history.repository.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmploymentHistoryService {

    private PersonRepository repository;

    public EmploymentHistoryService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return repository.getById(employeeId);
    }

    public Employee createEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setMiddleName(dto.getMiddleName());
        employee.setEmail(dto.getEmail());
        employee.setInn(dto.getInn());
        employee.setPassportSeries(dto.getPassportSeries());
        employee.setPassportNumber(dto.getPassportNumber());
        employee.setSnils(dto.getSnils());

        String correctNumber = dto.getPhoneNumber();

        String phone = dto.getPhoneNumber();
        if (phone.startsWith("+7")) {
            correctNumber = phone.substring(2);
        } else if (phone.startsWith("8")) {
            correctNumber = phone.substring(1);
        }

        employee.setPhoneNumber(correctNumber);

        return repository.save(employee);
    }

    public Employee updateEmployeeData(Long employeeId, EmployeeDTO personDetails) {
        Employee employee = repository.getById(employeeId);

        employee.setFirstName(personDetails.getFirstName());
        employee.setLastName(personDetails.getLastName());
        employee.setMiddleName(personDetails.getMiddleName());
        employee.setEmail(personDetails.getEmail());
        employee.setInn(personDetails.getInn());
        employee.setPassportSeries(personDetails.getPassportSeries());
        employee.setPassportNumber(personDetails.getPassportNumber());
        employee.setSnils(personDetails.getSnils());

        String correctNumber = personDetails.getPhoneNumber();

        String phone = personDetails.getPhoneNumber();
        if (phone.startsWith("+7")) {
            correctNumber = phone.substring(2);
        } else if (phone.startsWith("8")) {
            correctNumber = phone.substring(1);
        }

        employee.setPhoneNumber(correctNumber);

        return repository.save(employee);
    }

    public Map<String, Boolean> deleteEmployee(Long employeeId) {
        Employee employee = repository.getById(employeeId);
        repository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    public Work selectEmployeeWork(Long employeeId, Long workId) {
        Employee employee = repository.getById(employeeId);

        Work selectedWork = null;

        for (Work work : employee.getWorkingList()) {
            if (work.getId().equals(workId)) {
                selectedWork = work;
                return selectedWork;
            }
        }

        return selectedWork;
    }

    public Work addNewWork(Long employeeId, WorkDTO dto) {
        Employee employee = repository.getById(employeeId);

        Work work = new Work();

        work.setPerson(employee);
        work.setInn(dto.getInn());
        work.setEndWork(dto.getEndWork());
        work.setStartWork(dto.getStartWork());
        work.setPosition(dto.getPosition());
        work.setCompanyName(dto.getCompanyName());

        employee.getWorkingList().add(work);

        Employee updatedPerson = repository.save(employee);

        return updatedPerson.getWorkingList()
                .stream()
                .filter(work1 -> work1.getCompanyName().equals(work.getCompanyName()))
                .filter(work1 -> work1.getInn().equals(work.getInn()))
                .filter(work1 -> work1.getPosition().equals(work.getPosition()))
                .findFirst().get();
    }

    public List<Work> selectEmployeeWork(Long employeeId) {
        Employee employee = repository.getById(employeeId);

        return employee.getWorkingList();
    }

    public Work updateWorkingHistory(Long employeeId, Long workId, WorkDTO dto) {
        Employee employee = repository.getById(employeeId);

        Work updatedWork = employee.getWorkingList().get(Math.toIntExact(workId));

        updatedWork.setInn(dto.getInn());
        updatedWork.setStartWork(dto.getStartWork());
        updatedWork.setEndWork(dto.getEndWork());
        updatedWork.setCompanyName(dto.getCompanyName());
        updatedWork.setPosition(dto.getPosition());

        return updatedWork;
    }

    public Map<String, Boolean> deleteEmployeeWorkingHistory(Long employeeId, Long workId) {

        Employee employee = repository.getById(employeeId);
        employee.getWorkingList().remove(workId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("Deleted", Boolean.TRUE);

        return map;
    }
}
