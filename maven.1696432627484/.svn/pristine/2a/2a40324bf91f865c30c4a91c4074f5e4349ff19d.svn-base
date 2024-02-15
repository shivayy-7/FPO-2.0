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
@Table(name = "t_mst_installment")
public class FarmerInstallment extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inst_id", nullable = false)
    private Long id;


    @Column(name = "inst_name")
    private String instName;


    @Column(name = "inst_code")
    private String instCode;


    @Column(name = "fst_inst_amount")
    private String instAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheme_id")
    private Scheme scheme;


    @Column(name = "is_active")
    private Boolean isActive;

}