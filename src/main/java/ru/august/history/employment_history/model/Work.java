package ru.august.history.employment_history.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {

    private Person person;

    private Long id;
    private String company_name;
    private String inn;
    private Date startWork;
    private Date endWork;
    private String position;

    public Work() {

    }

    public Work(Long id, String company_name, String inn, Date startWork, Date endWork, String position) {
        this.id = id;
        this.company_name = company_name;
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
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
