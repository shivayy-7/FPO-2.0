package com.fpo.web.services;


import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.StageForwardedRule;
import com.fpo.web.vos.WorkFlowHistoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkFlowService {

	ServiceOutcome<String> actionByAutority(Long formId, String formEntity, String actionTaken, String remarks, MultipartFile rejectionAtchmnt);

	ServiceOutcome<List<StageForwardedRule>> getButtonsByRole(Long ppId, String moduleForm, String applModule);

	ServiceOutcome<List<WorkFlowHistoryDto>> getWorkFlowByModule(Long formId, String moduleForm, String statusFinder);

	String getDocument(String downloadCode,Long docId);

}
 