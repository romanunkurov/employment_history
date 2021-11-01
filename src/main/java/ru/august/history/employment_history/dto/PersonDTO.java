package ru.august.history.employment_history.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PersonDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String middleName;

    @Pattern(regexp = "([0-9]{2} [0-9]{2})")
    private String passportSeries;

    @Pattern(regexp = "([0-9]{3} [0-9]{3})")
    private String passportNumber;

    @Pattern(regexp = "([0-9]{3}-[0-9]{3}-[0-9]{3}-[0-9]{2})")
    private String snils;

    private String phoneNumber;

    @Pattern(regexp = "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$")
    private String email;

    @Pattern(regexp = "[0-9]{12}")
    private String inn;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
