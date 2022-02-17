package ru.august.history.employment_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.august.history.employment_history.model.Employee;

@Repository
public interface PersonRepository extends JpaRepository<Employee, Long> {

}
