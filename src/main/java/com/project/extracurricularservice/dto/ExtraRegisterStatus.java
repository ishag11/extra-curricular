package com.project.extracurricularservice.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ExtraRegisterStatus extends Status{
    private HttpStatus httpStatus;

    private String extracurricularId;

//    private LocalDate createdAt;

//    public LocalDate getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDate createdAt) {
//        this.createdAt = createdAt;
//    }

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

}
