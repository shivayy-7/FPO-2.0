package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "t_mst_sub_activity")
@Data
public class SubActivity extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 179358019883322316L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_act_id")
    private Long id;

    @Column(name = "sub_act_name_en")
    private String subActNameEn;


    @Column(name = "sub_act_name_hi")
    private String subActNameHi;

    @Column(name = "sub_act_code")
    private String subActCode;

    @Column(name = "sub_act_description")
    private String subActDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physical_unit", referencedColumnName = "unit_code")
    private PhysicalUnit physicalUnit;

    @Column(name = "is_active")
    private Boolean isActive = false;

//    @Transient
//    private String childMapData;
//    
//    @Transient
//    private String childCmpData;

}