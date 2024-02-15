package com.fpo.web.entities;


import java.io.Serializable;

import javax.persistence.*;


import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import com.fpo.web.vos.MilestoneCollectorChildVO;
import lombok.Data;

@Data
@Entity
@Table(name="t_mst_milstone")
public class Milstone extends Auditable<User>  implements Serializable{
	

	private static final long serialVersionUID = -2738100257967505606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="milstone_id")
	private Long milstoneId;
	
	@Column(name="milstone_name")
	private String milstoneName;
	
	@Column(name="milstone_code")
	private String milstoneCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inst_id")
	private FarmerInstallment installment;

	@Column(name = "milstone_type")
	private String milstoneType;
	
	@Column(name="is_active")
	private boolean isActive;

	@Transient
	private MilestoneCollectorChild mccVO;


}
