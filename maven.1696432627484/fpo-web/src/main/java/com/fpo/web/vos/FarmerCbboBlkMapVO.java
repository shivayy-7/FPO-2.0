package com.fpo.web.vos;

import com.fpo.web.entities.Block;
import com.fpo.web.entities.FarmerCbbo;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.fpo.web.entities.FarmerCbboBlkMap}
 */
@Data
public class FarmerCbboBlkMapVO implements Serializable {
    Long id;
    FarmerCbbo cbbo;
    Block block;
    Boolean isActive;
}