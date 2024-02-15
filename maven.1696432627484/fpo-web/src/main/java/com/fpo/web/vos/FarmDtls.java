package com.fpo.web.vos;

import java.util.ArrayList;
import java.util.List;

import com.fpo.web.entities.Farm;

import lombok.Data;

@Data
public class FarmDtls {
	
	private FarmVO farmVO;
	
	private List<Farm> farmList;
	
//	 public FarmDtls() {
//	        this.farmVO = new FarmVO(); 
//	        this.farmList = new ArrayList<>(); 
//	    }
	

}
