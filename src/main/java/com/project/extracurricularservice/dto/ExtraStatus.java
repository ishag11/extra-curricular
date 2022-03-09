package com.project.extracurricularservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;

public class ExtraStatus extends Status{

    private HttpStatus httpStatus;

    private String extracurricularId;

    private String clubName;

    private String clubIncharge;

    public String getExtracurricularId() {
        return extracurricularId;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setExtracurricularId(String extracurricularId) {
        this.extracurricularId = extracurricularId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubIncharge() {
        return clubIncharge;
    }

    public void setClubIncharge(String clubIncharge) {
        this.clubIncharge = clubIncharge;
    }

}
