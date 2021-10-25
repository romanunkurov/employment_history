package ru.august.history.employment_history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentHistoryRepository extends JpaRepository<Person, Long> {
}
