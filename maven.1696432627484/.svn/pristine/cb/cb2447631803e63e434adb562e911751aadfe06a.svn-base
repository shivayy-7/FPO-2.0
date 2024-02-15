package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_mst_physical_unit")
@Data
public class PhysicalUnit extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_code")
    private String unitCode;

    @Column(name = "is_active")
    private Boolean isActive = false;
    
    @Column(name = "unit_desc")
    private String unitDescription;

}