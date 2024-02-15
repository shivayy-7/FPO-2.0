package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "t_mst_activity")
@Data
public class Activity extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id")
    private Long id;

    @Column(name = "act_name_en")
    private String actNameEn;


    @Column(name = "act_name_hi")
    private String actNameHi;

    @Column(name = "act_code")
    private String actCode;

    @Column(name = "act_description")
    private String actDescription;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Transient
    private String childMapData;





}