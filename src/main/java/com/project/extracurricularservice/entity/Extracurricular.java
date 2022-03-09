package com.project.extracurricularservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_extracurricular")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Extracurricular  extends Auditable<String> implements Serializable{

    @Id
//    @GeneratedValue
//    private Long extracurricularId;
    @GenericGenerator(name = "sequence_extra_id", strategy = "com.project.extracurricularservice.generator.ExtracurricularIdGenerator")
    @GeneratedValue(generator = "sequence_extra_id")
    @Column(name="extracurricular_id")
    private String extracurricularId;


    @NonNull
    @NotEmpty(message="Club name is required")
    @Size(min = 3, message = "Clubname should have 3 characters.")
    private String clubName;

    @NonNull
    @NotEmpty(message="Incharge name is required")
    @Length(min = 3, max = 15, message="Incharge name should have min 3 and max 15 characters.")
    private String clubIncharge;

//    @NonNull
//    private ArrayList<@NotEmpty String> activities;

    @NonNull
    @NotEmpty(message="Activity name is required")
    @Size(min = 3, message = "Activity should have 3 characters.")
    private String activity1;

    @NonNull
    @NotEmpty(message="Activity name is required")
    @Size(min = 3, message = "Activity should have 3 characters.")
    private String activity2;

    @NonNull
    @NotEmpty(message="Activity name is required")
    @Size(min = 3, message = "Activity should have 3 characters.")
    private String activity3;


    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
//
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate createdAt ;
//
//    @NonNull
//    @NotEmpty(message="Created By Name is required")
//    private String createdBy;
//
//    @NonNull
//    @NotEmpty(message="Modified By Name is required")
//    private String modifiedBy;
//
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime modifiedAt = LocalDateTime.now();


//    public Long getExtracurricularId() {
//        return extracurricularId;
//    }
//
//    public void setExtracurricularId(Long extracurricularId) {
//        this.extracurricularId = extracurricularId;
//    }
//
//    public ArrayList<String> getActivities() {
//        return activities;
//    }
//
//    public void setActivities(ArrayList<String> activities) {
//        this.activities = activities;
//    }

}

//}