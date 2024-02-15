package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.*;
import com.fpo.web.repositories.FarmerInstallmentRepository;
import com.fpo.web.repositories.MilestoneCollectorChildRepository;
import com.fpo.web.repositories.MilestoneCollectorParentRepository;
import com.fpo.web.repositories.MilstoneRepository;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.utils.RandomString;
import com.fpo.web.utils.UploadFile;
import com.fpo.web.utils.YamlAppProperties;
import com.fpo.web.vos.MilestoneCollectorChildVO;
import com.fpo.web.vos.MilestoneCollectorParentVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class InstallmentServiceImpl implements  InstallmentService{
    @Autowired
    private MilestoneCollectorChildRepository milestoneCollectorChildRepository;
    @Autowired
    private MilestoneCollectorParentRepository milestoneCollectorParentRepository;

    @Autowired
    private FarmerInstallmentRepository farmerInstallmentRepository;

    @Autowired
    private MilstoneRepository milstoneRepository;

    @Autowired
    private YamlAppProperties YamlAppProperties;

    @Autowired
    private WorkFlowService workFlowService;



    @Autowired private ModelMapper modelMapper;

    @Override
    public List<FarmerInstallment> getAllInstallmentDetails() {
        List<FarmerInstallment> instList=new ArrayList<>();
        try{
            instList=farmerInstallmentRepository.findAllByIsActive(true);
        }catch (Exception e){
            log.error("Error While Fetch Installment List",e.getMessage());
        }
        return instList;
    }

    @Override
    public ServiceOutcome<List<Milstone>> fetchMilestone(Long instId) {
        List<Milstone> ms=new ArrayList<>();
        ServiceOutcome<List<Milstone>> svc = new ServiceOutcome<>();
        try {
            ms=milstoneRepository.findAllByInstallmentId(instId);
            svc.setData(ms);
            svc.setOutcome(true);
        }catch (Exception e){
            log.error("Exception Occur While fetchMilestone",e.getMessage());
            svc.setOutcome(false);
        }
        return svc;
    }

    @Override
    public ServiceOutcome<Boolean> manageMilestone(MilestoneCollectorParentVO mcpVO) {
        ServiceOutcome<Boolean> svc=new ServiceOutcome<>();
        Boolean milstone=false;

        try{
            User user = SecurityHelper.getCurrentUser();
            MilestoneCollectorParent mcp= Optional.ofNullable(mcpVO.getId())
                    .flatMap(milestoneCollectorParentRepository::findById) .orElseGet(MilestoneCollectorParent::new);
            if(user.getPrimaryRole().getRoleCode().equals(ApplicationConstants.ROLE_FPO)){
                mcp.setMcpCode("MCP-"+RandomStringUtils.random(6, true, true).toUpperCase());
                mcp.setInst(farmerInstallmentRepository.findById(mcpVO.getInst().getId()).get());
                mcp.setIsActive(true);
                milestoneCollectorParentRepository.save(mcp);
                for (Milstone d: mcpVO.getMilstone()) {
                    MilestoneCollectorChild  mcc=Optional.ofNullable(d.getMccVO().getId())
                            .flatMap(milestoneCollectorChildRepository::findById) .orElseGet(MilestoneCollectorChild::new);
                    mcc.setMccCode("MCC-"+RandomStringUtils.random(6, true, true).toUpperCase());
                    mcc.setMcp(mcp);
                    mcc.setMilstone(milstoneRepository.findById(d.getMilstoneId()).get());
                    try {
                        if (!d.getMccVO().getMcpMultipartFile().getOriginalFilename().equals("")) {
                            mcc.setMcpValue(UploadFile.uploadForArticle(null,d.getMccVO().getMcpMultipartFile(), YamlAppProperties.getPath(),mcc.getMccCode()));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    mcc.setIsActive(true);
                    milestoneCollectorChildRepository.save(mcc);
                }
            }

            workFlowService.actionByAutority(mcp.getId(), mcpVO.getFormEntity(), mcpVO.getActionTaken(),"",null).getData();
            svc.setData(true);
            svc.setOutcome(true);
            svc.setMessage("Installment Process Successfully");
        }catch(Exception e){
            log.error("Exception occurs while manageMilestone ====>",e.getMessage());
            svc.setData(false);
            svc.setOutcome(false);
            svc.setMessage("Unable to manage Installement");

        }
        return svc;
    }

	@Override
	public ServiceOutcome<List<MilestoneCollectorParent>> getAllMilestoneCollector() {
		ServiceOutcome<List<MilestoneCollectorParent>> soc = new ServiceOutcome<List<MilestoneCollectorParent>>();
		try {
			List<MilestoneCollectorParent> milestoneCollectorParentList = milestoneCollectorParentRepository.findAllByIsActiveTrue();
			soc.setData(milestoneCollectorParentList);
		} catch (Exception e) {
			 log.error("Exception occurs while getAllMilestoneCollector ====>",e.getMessage());
			 soc.setData(null);
			 soc.setOutcome(false);
			 soc.setMessage("Unable to manage Installement");
		}
		return soc;
	}

    @Override
    public ServiceOutcome<MilestoneCollectorParentVO> fetchInstData(Long instId,String msStatus) {
        ServiceOutcome<MilestoneCollectorParentVO> svc=new ServiceOutcome<>();
        MilestoneCollectorParentVO instData=new MilestoneCollectorParentVO();
        List<Milstone> childData=new ArrayList<>();
        MilestoneCollectorParent fetchInstData= null;
        try {
                if(msStatus.equals("PARENT")){
                      fetchInstData=milestoneCollectorParentRepository.getInstallmentData(instId);
                    if(!Optional.ofNullable(fetchInstData).isEmpty()){
                        modelMapper.map(fetchInstData, instData);
                    }
                }


                childData=fetchMilestone(msStatus.equals("PARENT") ?fetchInstData.getInst().getId() : instId).getData();
                    for (Milstone ms:childData) {

                        ms.setMccVO(!Optional.ofNullable(fetchInstData).isEmpty() ?
                                fetchInstData.getMileStoneChild().stream().filter(d->d.getMilstone().getMilstoneId().equals(ms.getMilstoneId())).findAny().orElse(null) : null);

                    }
                instData.setMilstone(childData);



            svc.setData(instData);
            svc.setOutcome(true);
        }catch(Exception e){
            log.error("Exception occurs while fetchInstData ====>",e.getMessage());
            svc.setData(null);
            svc.setOutcome(false);
        }
        return svc;
    }


}
