package ru.august.history.employment_history.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {

    private Person person;

    private Long id;
    private String companyName;
    private String inn;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startWork;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endWork;
    private String position;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String company_name) {
        this.companyName = company_name;
    }

    @Column(name = "inn", nullable = false)
    public String getInn() {
        return inn;
    }


    public void setInn(String inn) {
        this.inn = inn;
    }

    @Column(name = "start_work", nullable = false)
    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    @Column(name = "end_work", nullable = false)
    public Date getEndWork() {
        return endWork;
    }

    public void setEndWork(Date endWork) {
        this.endWork = endWork;
    }

    @Column(name = "position", nullable = false)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIgnore
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
