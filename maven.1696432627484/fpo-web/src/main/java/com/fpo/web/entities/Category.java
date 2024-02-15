package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_275_category")
@Data
public class Category  extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;


    @Column(name = "category_code", length = 48)
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmp_code", referencedColumnName = "cmp_code")
    private Component cmpCode;


    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "is_display")
    private Long isDisplay;

    @Column(name = "is_dist_applicable")
    private Boolean isDistApplicable;

    @Column(name = "is_state_applicable")
    private Boolean isStateApplicable;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoryCode")
//    private List<SubCategory> subCategories;


}