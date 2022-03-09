package com.project.extracurricularservice.repository;

import com.project.extracurricularservice.entity.Extracurricular;
import com.project.extracurricularservice.exception.ExtracurricularException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ExtracurricularRepository extends JpaRepository<Extracurricular, String> {


    Extracurricular findByExtracurricularId(String extracurricularId) throws ExtracurricularException;

    Extracurricular findByClubName(String clubName) throws ExtracurricularException;

    List<Extracurricular> findByStartDate(Date startDate) throws ExtracurricularException;

    List<Extracurricular> findByClubIncharge(String clubIncharge) throws ExtracurricularException;

//    Extracurricular findByStartDate(Date startDate) throws ExtracurricularException;
//    @Query("SELECT e from Extracurricular e WHERE e.startDate=:startDate")
//    List<Extracurricular> getExtracurricularByDate(@Param("startDate")String startDate);
}
