package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.FarmerInstallment;
import com.fpo.web.entities.MilestoneCollectorParent;
import com.fpo.web.entities.Milstone;
import com.fpo.web.vos.MilestoneCollectorParentVO;

import java.util.List;

public interface InstallmentService {
    List<FarmerInstallment> getAllInstallmentDetails();

    ServiceOutcome<List<Milstone>> fetchMilestone(Long instId);

    ServiceOutcome<Boolean> manageMilestone(MilestoneCollectorParentVO mcpVO);

    ServiceOutcome<List<MilestoneCollectorParent>> getAllMilestoneCollector();

    ServiceOutcome<MilestoneCollectorParentVO> fetchInstData(Long instId,String msStatus);
}
