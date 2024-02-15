package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name  ="t_farmer_history")
public class WorkFlowHistory extends Auditable<User> implements Serializable {
	
	private static final long serialVersionUID = -141673075942952521L;
	
	
	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name ="history_id")
	private Long historyId;

	@Column(name ="app_module")
	private String appModule;

	@Column(name ="module_form")
	private String moduleForm;
	
	@Column(name ="entity_id")
	private Long entityId;

	@ManyToOne
	@JoinColumn(name ="from_stage")
	private StageForwardedRule fromStage;
	
	@ManyToOne
	@JoinColumn(name ="to_stage")
	private StageForwardedRule toStage;
	
	@Column(name ="remarks")
	private String remarks;
	
	@Column(name ="attachments")
	private String attachments;

}
