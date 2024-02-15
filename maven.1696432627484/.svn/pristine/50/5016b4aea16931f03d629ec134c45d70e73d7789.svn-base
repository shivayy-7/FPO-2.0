package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name  ="t_farmer_stage_forwarded_rule")
public class StageForwardedRule extends Auditable<User> implements Serializable {
private static final long serialVersionUID = -331707737408578L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name ="forwarded_id")
	private Long forwardedId;

	@NotNull
	@ManyToOne
	@JoinColumn(name ="to_stage_id")
	private Role toStageMaster;

	@NotNull
	@ManyToOne	
	@JoinColumn(name ="action_type_id")
	private ActionType actionType;

	@NotNull
	@ManyToOne
	@JoinColumn(name ="from_stage_id")
	private Role fromStageMaster;

	@NotNull
	@Column(name = "is_first_stage")
	private Boolean isFirstStage;

	@NotNull
	@Column(name = "fwd_rule_code")
	private String fwdRuleCode;

	@NotNull
	@Column(name = "route_type")
	private String routeType;

	@NotNull
	@Column(name = "app_module")
	private String appModule;

	@NotNull
	@Column(name = "is_last_stage")
	private Boolean isLastStage;

	@NotNull
	@ManyToOne	
	@JoinColumn(name ="updated_status")
	private Status updatedStatus;

	@Column(name = "show_in_page")
	private Boolean showInPage;
	
	@Column(name="entity_lebel_id")
	private Long entityLebelId;
	
	@Column(name="grant_level")
	private String grantLevel;

}

