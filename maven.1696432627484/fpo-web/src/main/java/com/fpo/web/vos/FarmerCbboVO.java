package com.fpo.web.vos;

import com.fpo.web.entities.BankBranch;
import com.fpo.web.entities.Scheme;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fpo.web.entities.FarmerCbbo}
 */
@Data
public class FarmerCbboVO implements Serializable {
    Long id;
    String cbboName;
    String cbboCategory;
    String cbboCode;
    Long pin;
    String address;
    String landmark;
    String status;
    Boolean isActive;
    Scheme scheme;
    BankBranch bankBranch;
    Long accountNo;
}