package com.fpo.web.vos;

import java.util.List;

import lombok.Data;

@Data
public class MasterVO {
	
	private Long childId;
	
	private String childName;
	
	private String childCode;

	private String childDescription;

	private String childUnit;

	private String cmpCode;
	
	private String actCode;
	
	private String[] parentCode;
	
	private List<?> parentList;

	private List<?> childUnitList;
	
	private List<?> childList;

	public MasterVO() {}

	public MasterVO(Long parentId, String parentName, String parentCode, String childCode, List<?> childList,
			String masterCall, List<?> parentList) {

		this.childId = parentId;
		this.childName = parentName;
		this.childCode = childCode;
		this.childList = childList;
		this.parentList = parentList;
	}


}
