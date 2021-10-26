package ru.august.history.employment_history;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {

    private Long id;
    private String company_name;
    private String inn;
    private Long person_id;
    private Date startWork;
    private Date endWork;
    private String position;

    public Work() {

    }

    public Work(Long id, String company_name, String inn, Long person_id, Date startWork, Date endWork, String position) {
        this.id = id;
        this.company_name = company_name;
        this.inn = inn;
        this.person_id = person_id;
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

    @Column(name = "person_id", nullable = false)
    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
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
}
