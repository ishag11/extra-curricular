package com.project.extracurricularservice.controller;

import com.project.extracurricularservice.dto.*;
import com.project.extracurricularservice.entity.Auditable;
import com.project.extracurricularservice.entity.Extracurricular;
import com.project.extracurricularservice.errorhandler.RestExceptionHandler;
import com.project.extracurricularservice.exception.ExtracurricularException;
import com.project.extracurricularservice.repository.ExtracurricularRepository;
import com.project.extracurricularservice.service.ExtracurricularService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@CrossOrigin
@RestController
@RequestMapping("/extracurriculardetails")
public class ExtracurricularController {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ExtracurricularRepository erepo;

    @Autowired
    private ExtracurricularService extracurricularService;

    @PostMapping("/")
    public ExtraRegisterStatus saveExtracurricular(@RequestBody @Valid Extracurricular extracurricular) throws EntityNotFoundException {

        //public ResponseEntity<String> saveExtracurricular(@RequestBody @Valid Extracurricular extracurricular) throws EntityNotFoundException {
//        extracurricularService.saveExtracurricular(extracurricular);
//        return ResponseEntity.ok("Extracurricular data is valid");
//        ExtraRegisterStatus extraStatus = new ExtraRegisterStatus();
        try {
            ExtraRegisterStatus extraStatus = new ExtraRegisterStatus();
            String extracurricularId = extracurricularService.addExtracurricular(extracurricular);
            extraStatus.setStatus(Status.StatusType.SUCCESS);
            extraStatus.setMessage("DATA SAVED SUCCESSFULLY");
            extraStatus.setExtracurricularId(extracurricularId);
            extraStatus.setHttpStatus(HttpStatus.OK);
//            extraStatus.setCreatedAt(extracurricular.getCreatedAt());
            return extraStatus;

        } catch (ExtracurricularException extracurricularException) {
            ExtraRegisterStatus extraStatus = new ExtraRegisterStatus();
            extraStatus.setHttpStatus(HttpStatus.CONFLICT);
            extraStatus.setStatus(Status.StatusType.FAILED);
            extraStatus.setMessage("CLUB ALREADY PRESENT CHECK AGAIN");
            return extraStatus;
        }
    }

    //@GetMapping(value="{extracurricularId}")
    @GetMapping
    //public ExtraStatus getExtracurricular(@PathVariable (value=""extracurricularId") Long extracurricularId ){
    public ResponseEntity getExtracurricular(@RequestParam ("extracurricularId") String extracurricularId ) {
//        return extracurricularService.findExtracurricularById(extracurricularId);
//        if (extracurricularService.findExtracurricularById(extracurricularId).isEmpty()) {
//            throw new BlogNotFoundException();
//        } else {
//            blog = blogRepository.findById(id).get();
//        }

//        @GetMapping("blog/{id}")
//        public ResponseEntity getBlogById(@PathVariable("id") int id)  {
        ExtraStatus extraStatus = new ExtraStatus();
        ResponseEntity e;ResponseEntity f;
            try{
                extraStatus.setStatus(Status.StatusType.SUCCESS);
                extraStatus.setMessage("Club ID Present");
                extraStatus.setExtracurricularId(extracurricularService.findExtracurricularById(extracurricularId).getExtracurricularId());
                extraStatus.setClubName(extracurricularService.findExtracurricularById(extracurricularId).getClubName());
                extraStatus.setClubIncharge(extracurricularService.findExtracurricularById(extracurricularId).getClubIncharge());
                extraStatus.setHttpStatus(HttpStatus.OK);
                return new ResponseEntity(extraStatus, HttpStatus.OK);
//                extraStatus.setStatus(Status.StatusType.SUCCESS);
//                extraStatus.setMessage("Club Present");

            }
            catch(ExtracurricularException extracurricularException){
                    extraStatus.setStatus(Status.StatusType.FAILED);
                    extraStatus.setMessage("Club ID Not Present");
                    extraStatus.setHttpStatus(HttpStatus.CONFLICT);
                return new ResponseEntity(extraStatus, HttpStatus.CONFLICT);

//                return new ResponseEntity("Extracurricular ID NOT FOUND", HttpStatus.CONFLICT);
//                extraStatus.setStatus(Status.StatusType.FAILED);
//                extraStatus.setMessage("Club Not Present");

            }
//        ExtraStatus extraStatus = new ExtraStatus();
//        try {
//            Extracurricular ex = extracurricularService.findExtracurricularById(extracurricularId);
//
//            extraStatus.setStatus(Status.StatusType.SUCCESS);
//            extraStatus.setMessage("Club Present");
//            extraStatus.setExtracurricularId(extracurricularId);
//
//        }
//        catch(ExtracurricularException e) {
//            if(ex.getExtracurricularId().equals(null) ){
//                extraStatus.setStatus(Status.StatusType.FAILED);
//                extraStatus.setMessage("Club Not Present");
//                extraStatus.setExtracurricularId(ex.getExtracurricularId());
//            }
//            else{
//                extraStatus.setStatus(Status.StatusType.SUCCESS);
//                extraStatus.setMessage("Club Present");
//            }
//            extraStatus.setStatus(Status.StatusType.FAILED);
//            extraStatus.setMessage("Club Not Present");
//
//        }
//        return extraStatus;
    }

    @GetMapping("/details")
    public List<Extracurricular> getExtracurricularDetails(){
        return extracurricularService.getExtracurricularDetails();
    }

    @PutMapping(value="/modify-details",produces = "application/json", consumes="application/json")
    public ExtraUpdatedStatus updateExtracurricular(@Valid @RequestBody Extracurricular extracurricular){
//        return extracurricularService.saveExtracurricular(extracurricular);

            ExtraUpdatedStatus extraStatus = new ExtraUpdatedStatus();
            Auditable auditable= new Auditable() {
                @Override
                public Date getCreatedDate() {
                    return super.getCreatedDate();
                }
            };
        Extracurricular e = extracurricularService.saveExtracurricular(extracurricular);
            extraStatus.setStatus(Status.StatusType.SUCCESS);
            extraStatus.setMessage("DATA SAVED SUCCESSFULLY");
//            extraStatus.setLastModifiedDate(e.getLastModifiedDate());
//            extraStatus.setLastModifiedBy(e.getLastModifiedBy());
            extraStatus.setHttpStatus(HttpStatus.OK);
            //extraStatus.setCreatedDate(auditable.getCreatedDate());
//            extraStatus.setCreatedAt(extracurricular.getCreatedAt());
            return extraStatus;

//        } catch (ExtracurricularException extracurricularException) {
//            ExtraUpdatedStatus extraStatus = new ExtraUpdatedStatus();
//            extraStatus.setHttpStatus(HttpStatus.CONFLICT);
//            extraStatus.setStatus(Status.StatusType.FAILED);
//            extraStatus.setMessage("CLUB ALREADY PRESENT CHECK AGAIN");
//            return extraStatus;
//        }
    }

    @GetMapping("/clubdetails")
    //public ExtraStatus getExtracurricular(@PathVariable (value="extracurricularId") Long extracurricularId ){
//    public List<Extracurricular> getExtracurricularByClubName(@RequestParam ("clubName") String clubName ) {
    public ResponseEntity getExtracurricularByClubName(@RequestParam ("clubName") String clubName ) {
        ExtraStatus extraStatus = new ExtraStatus();
        ResponseEntity e;ResponseEntity f;
        try{
            extraStatus.setStatus(Status.StatusType.SUCCESS);
            extraStatus.setMessage("Club Name Present");
            extraStatus.setClubName(extracurricularService.findExtracurricularByClubName(clubName).getClubName());
            extraStatus.setExtracurricularId(extracurricularService.findExtracurricularByClubName(clubName).getExtracurricularId());
            extraStatus.setClubIncharge(extracurricularService.findExtracurricularByClubName(clubName).getClubIncharge());
            extraStatus.setHttpStatus(HttpStatus.OK);
            return new ResponseEntity(extraStatus, HttpStatus.OK);
        }
        catch(ExtracurricularException extracurricularException){
            extraStatus.setStatus(Status.StatusType.FAILED);
            extraStatus.setMessage("Club Name Not Present");
            extraStatus.setHttpStatus(HttpStatus.CONFLICT);
            return new ResponseEntity(extraStatus, HttpStatus.CONFLICT);
        }
        //return extracurricularService.findExtracurricularByClubName(clubName);
    }


    @RequestMapping("/clubinchargedetails/{clubIncharge}")
//public List<Extracurricular> getExtracurricularByDate(@RequestParam ("startDate") LocalDate startDate){
//    public List<Extracurricular> getExtracurricularByDate(@PathVariable (value="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate){
//public ResponseEntity getExtracurricularByDate(@RequestParam (value="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate){
    //return extracurricularService.getExtracurricularByStartDate(startDate);
    public ResponseEntity getExtracurricularByInchargeName(@PathVariable (value="clubIncharge") String clubIncharge){
        //return extracurricularService.getExtracurricularByStartDate(startDate);
        //ExtraStatus extraStatus = new ExtraStatus();
        ExtraDateStatus extraStatus = new ExtraDateStatus();
        try{
            extraStatus.setStatus(Status.StatusType.SUCCESS);
            extraStatus.setMessage("Club Incharge Present");
            List<Extracurricular> x=extracurricularService.getExtracurricularByClubIncharge(clubIncharge);
            //Extracurricular x=extracurricularService.getExtracurricularByStartDate(startDate);
            //System.out.println(x.toArray());
            extraStatus.setExtracurricularList(x);

            extraStatus.setExtracurricularList(x);
//            extraStatus.setClubIncharge(extracurricularService.getExtracurricularByStartDate(startDate).getClubIncharge());
            extraStatus.setHttpStatus(HttpStatus.OK);
            //return new ResponseEntity(x.toArray(), HttpStatus.OK);
            return new ResponseEntity(extraStatus, HttpStatus.OK);
        }
        catch(ExtracurricularException extracurricularException){
            extraStatus.setStatus(Status.StatusType.FAILED);
            extraStatus.setMessage("Club Incharge Not Present");
            extraStatus.setHttpStatus(HttpStatus.CONFLICT);
            return new ResponseEntity(extraStatus, HttpStatus.CONFLICT);
        }
    }

    //@GetMapping("/dates")
//    @GetMapping(value="{startDate}")
@RequestMapping("/dates/{startDate}")
//public List<Extracurricular> getExtracurricularByDate(@RequestParam ("startDate") LocalDate startDate){
//    public List<Extracurricular> getExtracurricularByDate(@PathVariable (value="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate){
//public ResponseEntity getExtracurricularByDate(@RequestParam (value="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate){
    //return extracurricularService.getExtracurricularByStartDate(startDate);
    public ResponseEntity getExtracurricularByDate(@PathVariable (value="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate){
        //return extracurricularService.getExtracurricularByStartDate(startDate);
        //ExtraStatus extraStatus = new ExtraStatus();
        ExtraDateStatus extraStatus = new ExtraDateStatus();
        try{
            extraStatus.setStatus(Status.StatusType.SUCCESS);
            extraStatus.setMessage("Start Date Present");
            List<Extracurricular> x=extracurricularService.getExtracurricularByStartDate(startDate);
            //Extracurricular x=extracurricularService.getExtracurricularByStartDate(startDate);
            //System.out.println(x.toArray());
            extraStatus.setExtracurricularList(x);

            extraStatus.setExtracurricularList(x);
//            extraStatus.setClubIncharge(extracurricularService.getExtracurricularByStartDate(startDate).getClubIncharge());
            extraStatus.setHttpStatus(HttpStatus.OK);
            //return new ResponseEntity(x.toArray(), HttpStatus.OK);
            return new ResponseEntity(extraStatus, HttpStatus.OK);
        }
        catch(ExtracurricularException extracurricularException){
            extraStatus.setStatus(Status.StatusType.FAILED);
            extraStatus.setMessage("Start Date Not Present");
            extraStatus.setHttpStatus(HttpStatus.CONFLICT);
            return new ResponseEntity(extraStatus, HttpStatus.CONFLICT);
        }
    }

//    @RequestMapping("/dates")
//    @GetMapping(value="{startDate}")
//    public List<Extracurricular> getExtracurricularByDate(@PathVariable (value="startDate")String startDate){
//        List<Extracurricular> a;
//        a = searchByDate(startDate);
//        System.out.println("aaa"+a.toString());
//        return  a;
//    }
//
//    public List<Extracurricular> searchByDate(String startDate) {
//        List<Extracurricular> s=  (List<Extracurricular>) entityManager.createQuery("SELECT e from Extracurricular e WHERE e.startDate=:startDate ")
//                .setParameter("startDate", startDate).getResultList();
//        System.out.println("sss"+s);
//        return s;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//
//        return errors;
//    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getConstraintViolations().forEach(cv -> {
//            errors.put("message", cv.getMessage());
//            errors.put("path", (cv.getPropertyPath()).toString());
//        });
//
//        return errors;
//    }
}


