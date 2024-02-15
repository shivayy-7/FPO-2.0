package com.fpo.web.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name  =  "t_275_action_type")
public class ActionType implements Serializable {
private static final long serialVersionUID = -346826469525694L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "action_type_id")
	private Long actionTypeId;

	@NotNull
	@Column(name = "action_name_hi")
	private String actionNameHi;

	@NotNull
	@Column(name = "isactive")
	private Boolean isactive;

	@NotNull
	@Column(name = "action_code")
	private String actionCode;

	@NotNull
	@Column(name = "action_name_en")
	private String actionNameEn;

	@NotNull
	@Column(name = "color")
	private String color;

	
}