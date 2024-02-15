package com.fpo.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "t_util_mail_recipants")
public class MailRecipantDetails implements Serializable {

	private static final long serialVersionUID = -4359119379145174137L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mail")
	private String mail;

	@Column(name = "mail_type")
	private String type;

}
