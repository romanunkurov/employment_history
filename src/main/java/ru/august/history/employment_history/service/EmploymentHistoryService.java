package ru.august.history.employment_history.service;

import org.springframework.stereotype.Service;
import ru.august.history.employment_history.dto.PersonDTO;
import ru.august.history.employment_history.model.Person;
import ru.august.history.employment_history.repository.PersonRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmploymentHistoryService {

    private PersonRepository repository;

    public EmploymentHistoryService(PersonRepository repository) {
        this.repository = repository;
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

    public Person getEmployeeById(PersonDTO dto) {
        Person person = new Person();
        return null;
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

        final Person updatedEmployee = repository.save(employee);
    }

    public Map<String, Boolean> deleteEmployee(Long employeeId) {
        Person employee = repository.getById(employeeId);
        repository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
    }


}
