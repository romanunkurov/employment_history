package ru.august.history.employment_history.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "inn", nullable = false)
    private String inn;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_work", nullable = false)
    private Date startWork;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "end_work", nullable = false)
    private Date endWork;
    @Column(name = "position", nullable = false)
    private String position;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnore
    private Person person;

    public Work() {

    }

    public Work(Long id, String companyName, String inn, Date startWork, Date endWork, String position) {
        this.id = id;
        this.companyName = companyName;
        this.inn = inn;
        this.startWork = startWork;
        this.endWork = endWork;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company_name) {
        this.companyName = company_name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    public Date getEndWork() {
        return endWork;
    }

    public void setEndWork(Date endWork) {
        this.endWork = endWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
