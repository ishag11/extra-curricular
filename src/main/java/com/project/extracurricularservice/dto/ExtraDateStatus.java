package com.project.extracurricularservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.extracurricularservice.entity.Extracurricular;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class ExtraDateStatus extends Status{
    private List<Extracurricular> extracurricularList;

    private HttpStatus httpStatus;



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<Extracurricular> getExtracurricularList() {
        return extracurricularList;
    }

    public void setExtracurricularList(List<Extracurricular> extracurricularList) {
        this.extracurricularList = extracurricularList;
    }
}
