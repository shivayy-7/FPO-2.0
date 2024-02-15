package com.fpo.web.entities;

import com.aashdit.framework.core.annotation.Sortable;
import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;

import lombok.Data;

import org.springframework.context.i18n.LocaleContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

@Entity
@Table(name = "t_mst_gender", schema = "public")
@Data
public class Gender extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 832436664155432699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gender_id")
	private Long genderId;

	@Column(name = "gender_code")
	private String genderCode;

	@Column(name = "gender_name_en")
	@Sortable(lang = "en")
	private String genderNameEN;

	@Column(name = "gender_name_hi")
	@Sortable(lang = "hi")
	private String genderNameHI;

	@Transient
	private String genderName;

	@Column(name = "is_active")
	private Boolean isActive;


}
