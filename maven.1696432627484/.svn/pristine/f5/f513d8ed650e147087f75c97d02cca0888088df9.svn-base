package com.fpo.web.services;

import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.Component;
import com.fpo.web.entities.Scheme;
import com.fpo.web.vos.MasterVO;


public interface ArticleMasterService {

	ServiceOutcome<MasterVO> getMasterData(MasterVO masterVO, String childCode, String masterCall, String string);

	ServiceOutcome<List<Component>> componentCodeList();

	ServiceOutcome<MasterVO> mstActiveInActive(MasterVO masterVO, String masterCall, Boolean isActive);

	ServiceOutcome<List<Component>> listComponent(boolean b);

	ServiceOutcome<List<Scheme>> listSchemeName();

	ServiceOutcome<Component> saveComponent(Component component);

	ServiceOutcome<Component> getComponent(Long cmpId);

	ServiceOutcome<Component> changeComponentStatus(String actionType, Long componentId);

	ServiceOutcome<Component> saveAndupdateComponent(Component component, RedirectAttributes attributes,
			Object CmpCode);
   
}
