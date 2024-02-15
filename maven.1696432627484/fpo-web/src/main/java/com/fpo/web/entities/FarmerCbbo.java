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
@Table(name = "t_farmer_cbbo")
public class FarmerCbbo extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5045873515576313544L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cbbo_id", nullable = false)
    private Long id;


    @Column(name = "cbbo_name")
    private String cbboName;


    @Column(name = "cbbo_category")
    private String cbboCategory;


    @Column(name = "cbbo_code")
    private String cbboCode;

    @Column(name = "pin")
    private Long pin;


    @Column(name = "address")
    private String address;


    @Column(name = "landmark")
    private String landmark;


    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheme_id")
    private Scheme scheme;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_branch_id")
    private BankBranch bankBranch;
    
    @Column(name="account_no")
    private Long accountNo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

}