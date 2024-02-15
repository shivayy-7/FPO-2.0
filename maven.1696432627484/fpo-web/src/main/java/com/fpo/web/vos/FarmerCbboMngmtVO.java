package com.fpo.web.vos;

import com.fpo.web.entities.Designation;
import com.fpo.web.entities.FarmerCbbo;
import com.fpo.web.entities.Gender;
import lombok.Data;
import lombok.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.fpo.web.entities.FarmerCbboMngmt}
 */
@Data
public class FarmerCbboMngmtVO implements Serializable {
    Long id;
    String cbboMngmtName;
    Gender gender;
    int age;
    String education;
    String aadharNo;
    String dinNo;
    String pinNo;
    Designation designation;
    FarmerCbbo cbbo;
    Boolean isActive;
//    Boolean isTrainer;
//    Boolean isUserCreation;
}