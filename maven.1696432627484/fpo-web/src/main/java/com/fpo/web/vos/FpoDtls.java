package com.fpo.web.vos;

import lombok.Data;
import java.util.List;
import java.util.Map;

import com.fpo.web.entities.Block;
import com.fpo.web.entities.Training;
import com.fpo.web.entities.TrainingFpoMap;

@Data
public class FpoDtls {
	
	private FpoVo fpoVo;
	
	private List<FpoManagementVo> fpoMngmtVo;
	
	private FpoDemographyVO fpoDemographyVO;
	
	private List<FpoSchemeMapVO> fpoSchemeMapVO;
	
	//=========
	
	private TrainingVO trainingVO;
	
	private List<Training> trainingList;
	
	private List<TrainingFpoMap> trainingFpoMap;
	
	private FpoDocumentVO fpoDocVo;
	
	private List<TrainingFpoMapVO> trainingFpoMapVo;
	
	List<Block> blockList;

}