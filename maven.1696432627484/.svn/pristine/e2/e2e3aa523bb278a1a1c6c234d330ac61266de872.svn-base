package com.fpo.web.vos;

import com.fpo.web.entities.MilestoneCollectorChild;
import com.fpo.web.entities.MilestoneCollectorParent;
import com.fpo.web.entities.Milstone;
import lombok.Data;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link MilestoneCollectorChild}
 */
@Data
public class MilestoneCollectorChildVO implements Serializable {
    private Long id;
    private String mccCode;
    private MilestoneCollectorParent mcp;
    private String mcpValue;
    private MultipartFile mcpMultipartFile;
    private String mcpMultipartFileWeb;
    private Boolean isActive;

}