package com.project.extracurricularservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

@EntityListeners({AuditingEntityListener.class})
public class ExtraUpdatedStatus extends Status{

    private HttpStatus httpStatus;

//    private String clubName;
//
//    private String clubIncharge;
//
//    private ArrayList<@NotEmpty String> activities;
//
//    private Date startDate;

    private Date lastModifiedDate;

    private String lastModifiedBy;

//    @Column(name = "created_date", updatable = false)
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }

//    public String getClubName() {
//        return clubName;
//    }
//
//    public void setClubName(String clubName) {
//        this.clubName = clubName;
//    }
//
//    public String getClubIncharge() {
//        return clubIncharge;
//    }
//
//    public void setClubIncharge(String clubIncharge) {
//        this.clubIncharge = clubIncharge;
//    }
//
//    public ArrayList<String> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(ArrayList<String> activities) {
//        this.activities = activities;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
