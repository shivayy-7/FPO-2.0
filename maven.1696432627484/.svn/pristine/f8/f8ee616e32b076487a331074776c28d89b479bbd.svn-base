package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_map_activity")
public class MapActivity extends Auditable<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_code", referencedColumnName = "act_code")
    private Activity actCode;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "head_code", referencedColumnName = "head_code")
//    private FunctionalHead headCode;

    @Column(name = "is_active")
    private Boolean isActive = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmp_code", referencedColumnName = "cmp_code")
    private Component component;


}