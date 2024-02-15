package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name  ="t_275_status")
public class Status extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 2103766162724046657L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "status")
	private String status;

	@Column(name = "status_code")
	private String statusCode;
	
	@Column(name = "status_description")
	private String statusDescription;


}
