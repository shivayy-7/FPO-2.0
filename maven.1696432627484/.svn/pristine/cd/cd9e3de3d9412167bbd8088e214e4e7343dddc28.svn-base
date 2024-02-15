package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "t_mst_functional_head")
@Data
@Deprecated
public class FunctionalHead extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "head_id")
    private Long id;

    @Column(name = "head_name_en")
    private String headNameEn;


    @Column(name = "head_name_hi")
    private String headNameHi;

    @Column(name = "head_code")
    private String headCode;

    @Column(name = "head_description")
    private String headDescription;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Transient
    private String childMapData;


}