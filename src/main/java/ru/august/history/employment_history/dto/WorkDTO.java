package ru.august.history.employment_history.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class WorkDTO {

    @NotNull
    private String companyName;

    @NotNull
    @Pattern(regexp = "[0-9]{10}")
    private String inn;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startWork;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endWork;

    @NotNull
    private String position;

    public String getCompanyName() {
        return companyName;
    }

    /*public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }*/

    public String getInn() {
        return inn;
    }

    /*public void setInn(String inn) {
        this.inn = inn;
    }*/

    public Date getStartWork() {
        return startWork;
    }

    /*public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }*//**/

    public Date getEndWork() {
        return endWork;
    }

    /*public void setEndWork(Date endWork) {
        this.endWork = endWork;
    }*/

    public String getPosition() {
        return position;
    }

    /*public void setPosition(String position) {
        this.position = position;
    }*/
}
