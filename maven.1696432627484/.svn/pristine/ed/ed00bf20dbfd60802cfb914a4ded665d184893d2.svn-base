package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "t_farmer_cbbo_mngmt")
public class FarmerCbboMngmt extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9177340826258603625L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cbbo_mngmt_id", nullable = false)
    private Long id;


    @Column(name = "cbbo_mngmt_name")
    private String cbboMngmtName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private Gender gender;


    @Column(name = "age")
    private int age;


    @Column(name = "qualification")
    private String education;


    @Column(name = "adhar_no")
    private String aadharNo;


    @Column(name = "din")
    private String dinNo;


    @Column(name = "pin")
    private String pinNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cbbo_id", nullable = false)
    private FarmerCbbo cbbo;

    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "is_trainer")
    private Boolean isTrainer;

    @Column(name = "is_user_creation")
    private Boolean isUserCreation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;


}