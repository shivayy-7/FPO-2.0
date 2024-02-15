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
@Table(name = "t_farmer_cbbo_blk_map")
public class FarmerCbboBlkMap extends Auditable<User> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8240005943179316823L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cbbo_blk_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cbbo_id")
    private FarmerCbbo cbbo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;

    @Column(name = "is_active")
    private Boolean isActive;

}