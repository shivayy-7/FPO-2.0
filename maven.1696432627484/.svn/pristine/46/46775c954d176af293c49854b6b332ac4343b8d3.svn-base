
package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.util.RandomString;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.MilestoneCollectorParent;
import com.fpo.web.entities.StageForwardedRule;
import com.fpo.web.entities.WorkFlowHistory;
import com.fpo.web.repositories.MilestoneCollectorParentRepository;
import com.fpo.web.repositories.StageForwardedRuleRepository;
import com.fpo.web.repositories.WorkFlowHistoryRepository;
import com.fpo.web.utils.UploadFile;
import com.fpo.web.utils.YamlAppProperties;
import com.fpo.web.vos.WorkFlowHistoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import com.fpo.web.utils.ApplicationConstants;

@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService, MessageSourceAware {

	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MilestoneCollectorParentRepository milestoneCollectorParentRepository;
	

	
	@Autowired
	private StageForwardedRuleRepository stageForwardedRuleRepository;
	
	@Autowired
	private WorkFlowHistoryRepository workFlowHistoryRepository;

	@Autowired
	private YamlAppProperties yamlAppProperties;

	

	//public static ResourceBundle rs = ResourceBundle.getBundle("application");
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	@Override
	public ServiceOutcome<String> actionByAutority(Long formId, String formEntity, String actionTaken , String remarks, MultipartFile rejectionAtchmnt) {
		ServiceOutcome<String> svc= new ServiceOutcome<>();
		String message="";
		try {
			User user=SecurityHelper.getCurrentUser();
			WorkFlowHistory workFlowHistory=new WorkFlowHistory();
			if(formEntity.equalsIgnoreCase("INSTALLMENT")) {
				MilestoneCollectorParent pp=milestoneCollectorParentRepository.findByIdAndIsActive(formId,true);
					if(Optional.ofNullable(pp).isPresent()) {
						workFlowHistory.setEntityId(formId);
						workFlowHistory.setAppModule(ApplicationConstants.APPL_MODULE_INSTALLMENT);
						workFlowHistory.setModuleForm(ApplicationConstants.MODULE_FORM_INSTALLMENT);
						workFlowHistory.setFromStage(pp.getStageForwardedRule());
						workFlowHistory.setRemarks(remarks);
						workFlowHistory.setAttachments(Optional.ofNullable(rejectionAtchmnt).isPresent() ?  UploadFile.saveUploadedMultipartFile(rejectionAtchmnt, yamlAppProperties.getPath(), new RandomString(8, ThreadLocalRandom.current()).nextString()) : null);
						pp.setStageForwardedRule(stageForwardedRuleRepository.findByModuleAndRoleIdAndStatus(user.getPrimaryRole().getRoleId(),ApplicationConstants.APPL_MODULE_INSTALLMENT,actionTaken));
						workFlowHistory.setToStage(pp.getStageForwardedRule());
						milestoneCollectorParentRepository.save(pp);
						if(Optional.ofNullable(pp.getStageForwardedRule()).isPresent()) {
							message="Request Submitted Successfully";
//									pp.getStageForwardedRule().getUpdatedStatus().getStatus().equalsIgnoreCase("DRAFT") ?
//									"pp.installment.draft" :
//										pp.getStageForwardedRule().getUpdatedStatus().getStatus().equalsIgnoreCase("PENDING") ?
//												user.getPrimaryRole().getRoleCode().equalsIgnoreCase("BASE.ACCESS.ROLE") ? "pp.installment.send.verification" : "pp.installment.verification" :
//													pp.getStageForwardedRule().getUpdatedStatus().getStatus().equalsIgnoreCase("REVERT") ?
//															"pp.pani_panchayat.revert" :	"pp.pani_panchayat.approved";
						}
					//	message=messageSource.getMessage(message,null,LocaleContextHolder.getLocale());
					}
			}

			workFlowHistoryRepository.save(workFlowHistory);
			svc.setData(message);
		} catch (Exception e) {
			log.error("error in actionByAuthority ==>"+e);		
			}
		return svc;
	}

	@Override
	public ServiceOutcome<List<StageForwardedRule>> getButtonsByRole(Long ppId, String moduleForm, String applModule) {
		ServiceOutcome<List<StageForwardedRule>> svc= new ServiceOutcome<>();
		List<StageForwardedRule> stageButtons=new ArrayList<>();
		try {
			User user=SecurityHelper.getCurrentUser();
			Boolean statusCheck=false;
			switch (applModule) {
				case "INSTALLMENT":
					switch (moduleForm) {
						case "INSTALLMENT":
							MilestoneCollectorParent pp=milestoneCollectorParentRepository.findByIdAndIsActive(ppId,true);
							statusCheck= !Optional.ofNullable(pp).isPresent() || !pp.getStageForwardedRule().getUpdatedStatus().getStatus().equalsIgnoreCase(ApplicationConstants.APPROVED);
							if(statusCheck) {
								stageButtons= Optional.ofNullable(pp).isPresent() ?
										user.getPrimaryRole().getRoleCode().equals(pp.getStageForwardedRule().getToStageMaster().getRoleCode())
												?  stageForwardedRuleRepository.getButtons(pp.getStageForwardedRule().getToStageMaster().getRoleCode(),pp.getStageForwardedRule().getRouteType(),pp.getStageForwardedRule().getAppModule())
												: new ArrayList<>()
										: stageForwardedRuleRepository.getButtons(user.getPrimaryRole().getRoleCode(), ApplicationConstants.INSTALLMENT_ROUT_TYPE,  ApplicationConstants.APPL_MODULE_INSTALLMENT) ;
							}
							break;

					}
			}

			svc.setData(stageButtons);
		} catch (Exception e) {
			log.error("error in getButtonsByRole ==>"+e);		}
		return svc;
	}

	@Override
	public ServiceOutcome<List<WorkFlowHistoryDto>> getWorkFlowByModule(Long formId, String moduleForm, String statusFinder) {
		ServiceOutcome<List<WorkFlowHistoryDto>> svc= new ServiceOutcome<>();
		List<WorkFlowHistoryDto> workFlow=new ArrayList<>();
		try {
			List<WorkFlowHistory> workFlowHistory=Optional.ofNullable(statusFinder).isPresent() ?
					workFlowHistoryRepository.findByEntityIdAndModuleFormAndToStageUpdatedStatusStatusIgnoreCaseOrderByHistoryIdAsc(formId,moduleForm,statusFinder)
					: workFlowHistoryRepository.findByEntityIdAndModuleFormOrderByHistoryIdAsc(formId,moduleForm);
			if(workFlowHistory !=null && workFlowHistory.size()>0 ) {
				workFlowHistory.forEach(data->{
					WorkFlowHistoryDto wsh=new WorkFlowHistoryDto();
					wsh.setHistoryId(data.getHistoryId());
					wsh.setAppModule(data.getAppModule());
					wsh.setEntityId(data.getEntityId());
					wsh.setFromStage(Optional.ofNullable(data.getFromStage()).isPresent() ? data.getFromStage().getUpdatedStatus().getStatusDescription() : "-");
					wsh.setToStage(data.getToStage().getUpdatedStatus().getStatusDescription());
					wsh.setReportedBy(data.getCreatedBy().getUserName());
					wsh.setReportedOn(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data.getCreatedOn()));
					wsh.setRemarks(Optional.ofNullable(data.getRemarks()).isPresent() ? data.getRemarks() : "NA");
					wsh.setAttachment(Optional.ofNullable(data.getAttachments()).isPresent() ? data.getAttachments() : "NA");
					workFlow.add(wsh);
				});
			}
			svc.setData(workFlow);
		} catch (Exception e) {
			log.error("error in getWorkFlowByModule ==>"+e);
		}
		return svc;
	}

	@Override
	public String getDocument(String downloadCode, Long docId) {
		String doc="";
		try {
			switch (downloadCode) {
				case "REVERT":
					WorkFlowHistory wfh=workFlowHistoryRepository.findById(docId).get();
					if(Optional.ofNullable(wfh).isPresent()) {
						if(wfh.getAppModule().equalsIgnoreCase(ApplicationConstants.APPL_MODULE_INSTALLMENT)) {
							doc=yamlAppProperties.getPath()+File.separator+wfh.getAttachments();
						}
					}
					break;


			}


		} catch (Exception e) {
			log.error("message", e.getMessage());

		}
		return doc;
	}




}
