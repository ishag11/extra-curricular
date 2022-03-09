package com.project.extracurricularservice.service;

import com.project.extracurricularservice.exception.ExtracurricularException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.project.extracurricularservice.entity.Extracurricular;
import com.project.extracurricularservice.repository.ExtracurricularRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExtracurricularService {

    @Autowired
    private ExtracurricularRepository extracurricularRepository;

    @Autowired
    public EntityManager entityManager;

    public Extracurricular saveExtracurricular(Extracurricular extracurricular) {
        return extracurricularRepository.save(extracurricular);
    }

    public String addExtracurricular(Extracurricular extracurricular) {
        if(isExtracurricularPresent(extracurricular.getClubName()))
            throw new ExtracurricularException("EXTRACURRICULARID ALREADY PRESENT !");
        else {
            Extracurricular updatedExtracurricular = (Extracurricular) extracurricularRepository.save(extracurricular);
            return updatedExtracurricular.getExtracurricularId();
        }
    }


    //    @Query ("select count(c.clubName) from Candidate c where c.clubName = :clubName")
//    boolean isExtracurricularPresent(String clubName);
    public boolean isExtracurricularPresent(String clubName) {
        return (Long)
                entityManager
                        .createQuery( "select count(c.clubName) from Extracurricular c where c.clubName = :clubName")
                        .setParameter("clubName",clubName)
                        .getSingleResult() == 1 ? true : false;
    }

    public Extracurricular findExtracurricularById(String extracurricularId) throws ExtracurricularException {
        Extracurricular b;
        if (!(extracurricularRepository.findById(extracurricularId).isPresent())) {

            throw new ExtracurricularException();
        } else {
            b = extracurricularRepository.findByExtracurricularId(extracurricularId);
        }
        return b;
    }



    public Extracurricular findExtracurricularByClubName(String clubName) throws ExtracurricularException{
        Extracurricular b;
        Extracurricular c;
        c = extracurricularRepository.findByClubName(clubName);
        if (c == null){
            throw new ExtracurricularException();
        } else {
            b = extracurricularRepository.findByClubName(clubName);
        }
        return b;
    }

    public List<Extracurricular> getExtracurricularDetails(){
        return extracurricularRepository.findAll();
    }

//    public List<Extracurricular> getExtracurricularByDate(String startDate){
//        return extracurricularRepository.getExtracurricularByDate(startDate);
//    }

    public List<Extracurricular> getExtracurricularByStartDate(Date startDate) throws ExtracurricularException{
        List<Extracurricular> b;
        List<Extracurricular> c;
        b = extracurricularRepository.findByStartDate(startDate);
        System.out.println(b);
        if (b.isEmpty()){
            throw new ExtracurricularException();
        } else {
            b = extracurricularRepository.findByStartDate(startDate);
        }
        return b;
        //return extracurricularRepository.findByStartDate(startDate);
    }

    public List<Extracurricular> getExtracurricularByClubIncharge(String clubIncharge) throws ExtracurricularException{
        List<Extracurricular> b;
        List<Extracurricular> c;
        b = extracurricularRepository.findByClubIncharge(clubIncharge);
        System.out.println(b);
        if (b.isEmpty()){
            throw new ExtracurricularException();
        } else {
            b = extracurricularRepository.findByClubIncharge(clubIncharge);
        }
        return b;
        //return extracurricularRepository.findByStartDate(startDate);
    }


//    public Extracurricular getExtracurricularByStartDate(Date startDate) throws ExtracurricularException{
//        Extracurricular b;
//        Extracurricular c;
//        c = extracurricularRepository.findByStartDate(
//                startDate
//        );
//        System.out.println(c);
//        if (c==null){
//            throw new ExtracurricularException();
//        } else {
//            b = extracurricularRepository.findByStartDate(startDate);
//        }
//        return b;
//        //return extracurricularRepository.findByStartDate(startDate);
//    }
}
