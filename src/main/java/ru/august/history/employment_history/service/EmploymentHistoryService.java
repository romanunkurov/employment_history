package ru.august.history.employment_history.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.august.history.employment_history.dto.PersonDTO;
import ru.august.history.employment_history.dto.WorkDTO;
import ru.august.history.employment_history.exceptions.ResourceNotFoundException;
import ru.august.history.employment_history.model.Person;
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

    public List<Person> getAllEmployees() {
        return repository.findAll();
    }

    public Person getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return repository.getById(employeeId);

    }

    public Person createEmployee(PersonDTO dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setMiddleName(dto.getMiddleName());
        person.setEmail(dto.getEmail());
        person.setInn(dto.getInn());
        person.setPassportSeries(dto.getPassportSeries());
        person.setPassportNumber(dto.getPassportNumber());
        person.setSnils(dto.getSnils());

        String correctNumber = dto.getPhoneNumber();

        String phone = dto.getPhoneNumber();
        if (phone.startsWith("+7")) {
            correctNumber = phone.substring(2);
        } else if (phone.startsWith("8")) {
            correctNumber = phone.substring(1);
        }

        person.setPhoneNumber(correctNumber);

        return repository.save(person);
    }

    public Person updateEmployeeData(Long employeeId, PersonDTO personDetails) {
        Person employee = repository.getById(employeeId);

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
        Person employee = repository.getById(employeeId);
        repository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    public Work getEmployeeWorkingHistory(Long employeeId, Long workId) {
        Person employee = repository.getById(employeeId);

        Work workWeNeed = null;

        for ( Work work : employee.getWorkingList()) {
            if (work.getId().equals(workId)) {
                workWeNeed = work;
                return workWeNeed;
            }
        }

        return workWeNeed;
    }

    public Work addNewWork(Long employeeId, WorkDTO dto) {
        Person employee = repository.getById(employeeId);

        Work work = new Work();

        work.setPerson(employee);
        work.setInn(dto.getInn());
        work.setEndWork(dto.getEndWork());
        work.setStartWork(dto.getStartWork());
        work.setPosition(dto.getPosition());
        work.setCompanyName(dto.getCompanyName());

        employee.getWorkingList().add(work);

        Person updatedPerson = repository.save(employee);

        //TODO: refactoring
        return updatedPerson.getWorkingList().get(updatedPerson.getWorkingList().size() - 1);

        //return work;
    }


    public List<Work> getEmployeeWorkingHistory(Long employeeId) {
        Person employee = repository.getById(employeeId);

        return employee.getWorkingList();
    }

    public Work updateWorkingHistory(Long employeeId, Long workId, WorkDTO dto) {
        return null;
    }

    public Map<String, Boolean> deleteEmployeeWorkingHistory(Long employeeId, Long workId) {
        return null;
    }
}
