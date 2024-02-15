package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_map_sub_activity")
public class MapSubActivity extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7684790150238129400L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_code", referencedColumnName = "act_code")
    private Activity actCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_act_code", referencedColumnName = "sub_act_code")
    private SubActivity subActCode;

    @Column(name = "is_active")
    private Boolean isActive = false;

}