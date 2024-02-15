package com.fpo.web.repositories;



import com.fpo.web.entities.StageForwardedRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageForwardedRuleRepository extends JpaRepository<StageForwardedRule, Long> {

	@Query("FROM StageForwardedRule where fromStageMaster.roleId=?1 and appModule=?2 and fwdRuleCode=?3")
	StageForwardedRule findByModuleAndRoleIdAndStatus( Long roleId,String applModulePaniPanchayat,String actionTaken);

	@Query("FROM StageForwardedRule where fromStageMaster.roleCode=?1 and routeType=?2 and appModule=?3 order by actionType.actionTypeId")
	List<StageForwardedRule> getButtons(String roleCode, String routeType, String appModule);
}
