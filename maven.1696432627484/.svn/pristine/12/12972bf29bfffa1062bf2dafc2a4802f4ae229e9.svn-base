package com.fpo.web.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.LocaleSpecificSorter;
import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.Activity;
import com.fpo.web.entities.Category;
import com.fpo.web.entities.Component;
import com.fpo.web.entities.FunctionalHead;
import com.fpo.web.entities.MapActivity;
import com.fpo.web.entities.MapFunctionalHead;
import com.fpo.web.entities.MapSubActivity;
import com.fpo.web.entities.PhysicalUnit;
import com.fpo.web.entities.Scheme;
import com.fpo.web.entities.SubActivity;
import com.fpo.web.repositories.ActivityRepository;
import com.fpo.web.repositories.CategoryRepository;
import com.fpo.web.repositories.ComponentRepository;
import com.fpo.web.repositories.FunctionalHeadRepository;
import com.fpo.web.repositories.MapActivityRepository;
import com.fpo.web.repositories.MapFunctionalHeadRepository;
import com.fpo.web.repositories.MapSubActivityRepository;
import com.fpo.web.repositories.PhysicalUnitRepository;
import com.fpo.web.repositories.SchemeRepository;
import com.fpo.web.repositories.SubActivityRepository;
import com.fpo.web.utils.RandomNumberGenerator;
import com.fpo.web.vos.MasterVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleMasterServiceImpl implements ArticleMasterService, MessageSourceAware {

	final static Logger logger = Logger.getLogger(ArticleMasterServiceImpl.class);
	
	@Autowired
	private FunctionalHeadRepository functionalHeadRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private SchemeRepository schemeRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private SubActivityRepository  subActivityRepository ;

	@Autowired
	private  MapActivityRepository  mapActivityRepository;

	@Autowired
	private MapSubActivityRepository mapSubActivityRepository;

	@Autowired
	private PhysicalUnitRepository physicalUnitRepository;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MapFunctionalHeadRepository mapFunctionalHeadRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	@Override
	public ServiceOutcome<MasterVO> getMasterData(MasterVO masterVO, String childCode,   String masterCall, String methodCall) {
		ServiceOutcome<MasterVO> masterResponse = new ServiceOutcome<>();
		try{
			String responseCall="";
			switch (masterCall) {
				case "FUNCTIONAL-HEAD":
					switch (methodCall) {
						case "GET":
							List<FunctionalHead> fHeadList=functionalHeadRepository.findAll();
							masterVO.setParentList(componentRepository.findAll());
							if(fHeadList.size()>0){

								fHeadList.stream().map(d->{
									List<MapFunctionalHead> mapsHeadList=mapFunctionalHeadRepository.findByHeadCodeHeadCodeAndIsActiveTrue(d.getHeadCode());
									if(mapsHeadList.size()>0){
										d.setChildMapData(mapsHeadList.stream().map(data->data.getCmpCode().getCmpNameEn()).collect(Collectors.joining(",")));
									}
									return d;
								}).collect(Collectors.toList());

								masterVO.setChildList(fHeadList);

								if(Optional.ofNullable(childCode).isPresent()) {
									FunctionalHead headData=functionalHeadRepository.findByHeadCodeAndIsActiveTrue(childCode);
									masterVO.setChildId(headData.getId());
									masterVO.setChildName(headData.getHeadNameEn());
									masterVO.setChildCode(headData.getHeadCode());
									masterVO.setChildDescription(headData.getHeadDescription());

									List<MapFunctionalHead> mapsfHeadList=mapFunctionalHeadRepository.findByHeadCodeHeadCodeAndIsActiveTrue(childCode);
									if(mapsfHeadList.size()>0){
										Object[]  rowData=mapsfHeadList.stream().map(data->data.getCmpCode().getCmpCode()).collect(Collectors.toList()).toArray();
										masterVO.setParentCode(asStrings(rowData));
									}
								}
							}
							break;
						case "POST":
							
							FunctionalHead fHead = null;
							if(Optional.ofNullable(masterVO.getChildId()).isPresent()) {
								fHead = functionalHeadRepository.findById(masterVO.getChildId()).get();
							}else {
								fHead = new FunctionalHead();
								fHead.setHeadCode("F-HEAD-"+RandomStringUtils.random(6, true, true).toUpperCase());
							}
							fHead.setHeadNameEn(masterVO.getChildName());
							fHead.setHeadNameHi(masterVO.getChildName());
							fHead.setHeadDescription(masterVO.getChildDescription());
							fHead.setIsActive(true);
							functionalHeadRepository.save(fHead);
							List<MapFunctionalHead> mapFnList = mapFunctionalHeadRepository.findByHeadCodeHeadCodeAndIsActiveTrue(fHead.getHeadCode());
							List<String> mapFnCode = mapFnList.stream().map(c -> c.getCmpCode().getCmpCode()).collect(Collectors.toList());
							for(String parentCode : masterVO.getParentCode()) {
								MapFunctionalHead MfHead = mapFunctionalHeadRepository.findByHeadCodeHeadCodeAndCmpCodeCmpCode(fHead.getHeadCode(),parentCode);
								if(Optional.ofNullable(MfHead).isPresent()) {
									MfHead.setCmpCode(componentRepository.findByCmpCodeAndIsActiveTrue(parentCode));
									MfHead.setHeadCode(fHead);
									MfHead.setIsActive(true);
									mapFunctionalHeadRepository.save(MfHead);
									mapFnCode.remove(parentCode);
								}else {
									MfHead = new MapFunctionalHead();
									MfHead.setCmpCode(componentRepository.findByCmpCodeAndIsActiveTrue(parentCode));
									MfHead.setHeadCode(fHead);
									MfHead.setIsActive(true);
									mapFunctionalHeadRepository.save(MfHead);
								}
							}

							mapFnCode.forEach(data -> {
								MapFunctionalHead duplicateFnMap = mapFunctionalHeadRepository.findByHeadCodeHeadCodeAndCmpCodeCmpCode(masterVO.getChildCode(), data);
                				if (Optional.ofNullable(duplicateFnMap).isPresent()) {
                					duplicateFnMap.setIsActive(false);
                					mapFunctionalHeadRepository.save(duplicateFnMap);
                				}
                			});
							
							responseCall = masterCall.equals("FUNCTIONAL-HEAD") ? "Functional Head Saved Successfully" : "" ;
							masterResponse.setOutcome(true);
							break;
					}
					break;

				case "ACTIVITY":
					switch (methodCall) {
						case "GET":
							List<Activity> actList= activityRepository.findAllByIsActiveTrue();
							masterVO.setParentList(componentRepository.findAllByIsActiveTrue());
							if(actList.size()>0){

								actList.stream().map(d->{
									List<MapActivity> mapsActList=mapActivityRepository.findByActCodeActCodeAndIsActiveTrue(d.getActCode());
									if(mapsActList.size()>0){
										d.setChildMapData(mapsActList.stream().map(data -> data.getComponent().getCmpNameEn()).collect(Collectors.joining(",")));
									}
									return d;
								}).collect(Collectors.toList());

								masterVO.setChildList(actList);

								if(Optional.ofNullable(childCode).isPresent()) {
									Activity actData=activityRepository.findByActCodeAndIsActiveTrue(childCode);
									masterVO.setChildId(actData.getId());
									masterVO.setChildName(actData.getActNameEn());
									masterVO.setChildCode(actData.getActCode());
									masterVO.setChildDescription(actData.getActDescription());

									List<MapActivity> mapsActList=mapActivityRepository.findByActCodeActCodeAndIsActiveTrue(childCode);
									if(mapsActList.size()>0){
										Object[] row = mapsActList.stream().map(data -> data.getComponent().getCmpCode()).collect(Collectors.toList()).toArray();
										masterVO.setParentCode(asStrings(row));
									}
								}
							}

							break;

						case "POST":
							Activity act = null;
							if(Optional.ofNullable(masterVO.getChildId()).isPresent()) {
								act = activityRepository.findById(masterVO.getChildId()).get();
							}else {
								act = new Activity();
								act.setActCode("ACT-"+RandomStringUtils.random(6, true, true).toUpperCase());
							}
							act.setActNameEn(masterVO.getChildName().trim());
							act.setActNameHi(masterVO.getChildName().trim());
							act.setActDescription(masterVO.getChildDescription().trim());
							act.setIsActive(true);
							activityRepository.save(act);

							List<MapActivity> mapActList = mapActivityRepository.findByActCodeActCodeAndIsActiveTrue(act.getActCode());
							List<String> mapActCodeList = mapActList.stream().map(c -> c.getComponent().getCmpCode()).collect(Collectors.toList());
							for(String parentCode : masterVO.getParentCode()) {
								MapActivity mact = mapActivityRepository.findByActCodeActCodeAndComponentCmpCode(act.getActCode(), parentCode);
								if(Optional.ofNullable(mact).isPresent()) {
									mact.setActCode(act);
									mact.setComponent(componentRepository.findByCmpCodeAndIsActiveTrue(parentCode));
									mact.setIsActive(true);
									mapActivityRepository.save(mact);
									mapActCodeList.remove(parentCode);
								}else {
									mact = new MapActivity();
									mact.setActCode(act);
									mact.setComponent(componentRepository.findByCmpCodeAndIsActiveTrue(parentCode));
									mact.setIsActive(true);
									mapActivityRepository.save(mact);
								}
							}

							mapActCodeList.forEach(data -> {
								MapActivity duplicateFnAct = mapActivityRepository.findByActCodeActCodeAndComponentCmpCode(masterVO.getChildCode(), data);
                				if (Optional.ofNullable(duplicateFnAct).isPresent()) {
                					duplicateFnAct.setIsActive(false);
                					mapActivityRepository.save(duplicateFnAct);
                				}
                			});
							
							responseCall = masterCall.equals("ACTIVITY") ? "Activity Saved Successfully" : "" ;
							masterResponse.setOutcome(true);
							break;
					}

					break;

				case "SUB-ACTIVITY":
					switch (methodCall) {
						case "GET":
							List<SubActivity> subActList= subActivityRepository.findAllByIsActiveTrue();
							masterVO.setParentList(activityRepository.findAllByIsActiveTrue());
							List<PhysicalUnit> phyUnitList=physicalUnitRepository.findAllByIsActiveTrue();
							masterVO.setChildUnitList(phyUnitList);

							if(subActList.size()>0){

								subActList.stream().map(d->{
									List<MapSubActivity> mapsSubActList=mapSubActivityRepository.findBySubActCodeSubActCodeAndIsActiveTrue(d.getSubActCode());
									if(mapsSubActList.size()>0){
										d.setChildMapData(mapsSubActList.stream().map(data->data.getActCode().getActNameEn()).collect(Collectors.joining(",")));
									}
									return d;
								}).collect(Collectors.toList());
								
								subActList.stream().map(d->{
									List<MapSubActivity> mapsSubActList=mapSubActivityRepository.findBySubActCodeSubActCodeAndIsActiveTrue(d.getSubActCode());
									List<MapActivity> mapsActList = new ArrayList<>();
									if(mapsSubActList.size() > 0) {
										for(MapSubActivity mapSub : mapsSubActList) {
											mapsActList=mapActivityRepository.findByActCodeActCode(mapSub.getActCode().getActCode());
											mapsActList.addAll(mapsActList);
										}
										if(mapsActList.size() > 0) {
											d.setChildCmpData(mapsActList.stream().map(data->data.getComponent().getCmpNameEn()).distinct().collect(Collectors.joining(",")));
										}
										
									}
									return d;
								}).collect(Collectors.toList());

								masterVO.setChildList(subActList);


								if(Optional.ofNullable(childCode).isPresent()) {
									SubActivity subActData=subActivityRepository.findBySubActCodeAndIsActiveTrue(childCode);
									masterVO.setChildId(subActData.getId());
									masterVO.setChildName(subActData.getSubActNameEn());
									masterVO.setChildCode(subActData.getSubActCode());
									masterVO.setChildUnit(subActData.getPhysicalUnit().getUnitCode());
									masterVO.setChildDescription(subActData.getSubActDescription());

									List<MapSubActivity> mapsSubActList=mapSubActivityRepository.findBySubActCodeSubActCodeAndIsActiveTrue(childCode);
									if(mapsSubActList.size()>0){
										Object[] row=mapsSubActList.stream().map(data->data.getActCode().getActCode()).collect(Collectors.toList()).toArray();
										masterVO.setParentCode(asStrings(row));
										
										for(MapSubActivity mapSub : mapsSubActList) {
											List<MapActivity> mapActList = mapActivityRepository.findByActCodeActCodeAndIsActiveTrue(mapSub.getActCode().getActCode());
											if(mapActList.size() > 0) {
												for(MapActivity mapAct : mapActList) {
													masterVO.setCmpCode(mapAct.getComponent().getCmpCode());
													masterVO.setActCode(mapAct.getActCode().getActCode());
												}
											}
										}
									}
									
									

								}
							}
							break;

						case "POST":
							SubActivity sact = null;
							if(Optional.ofNullable(masterVO.getChildId()).isPresent()) {
								sact = subActivityRepository.findById(masterVO.getChildId()).get();
							}else {
								sact = new SubActivity();
								sact.setSubActCode("SUBACT-"+RandomStringUtils.random(6, true, true).toUpperCase());
							}
							sact.setSubActNameEn(masterVO.getChildName().trim());
							sact.setSubActNameHi(masterVO.getChildName().trim());
							sact.setPhysicalUnit(physicalUnitRepository.findByUnitCode(masterVO.getChildUnit().trim()));
							sact.setSubActDescription(masterVO.getChildDescription().trim());
							sact.setIsActive(true);
							subActivityRepository.save(sact);

							List<MapSubActivity> subMapList = mapSubActivityRepository.findBySubActCodeSubActCodeAndIsActiveTrue(sact.getSubActCode());
							List<String> subMapCodeList =  subMapList.stream().map(c -> c.getActCode().getActCode()).collect(Collectors.toList());
							for(String parentCode : masterVO.getParentCode()) {
								MapSubActivity msact = mapSubActivityRepository.findBySubActCodeSubActCodeAndActCodeActCode(sact.getSubActCode(),parentCode);
								if(Optional.ofNullable(msact).isPresent()){	
									msact.setSubActCode(sact);
									msact.setActCode(activityRepository.findByActCodeAndIsActiveTrue(parentCode));
									msact.setIsActive(true);
									mapSubActivityRepository.save(msact);
									subMapCodeList.remove(parentCode);
								}else {
									msact = new MapSubActivity();
									msact.setSubActCode(sact);
									msact.setActCode(activityRepository.findByActCodeAndIsActiveTrue(parentCode));
									msact.setIsActive(true);
									mapSubActivityRepository.save(msact);
								}
							}

							subMapCodeList.forEach(data -> {
								MapSubActivity duplicateFnSubAct = mapSubActivityRepository.findBySubActCodeSubActCodeAndActCodeActCode(masterVO.getChildCode(),data);
                				if (Optional.ofNullable(duplicateFnSubAct).isPresent()) {
                					duplicateFnSubAct.setIsActive(false);
                					mapSubActivityRepository.save(duplicateFnSubAct);
                				}
                			});
							
							responseCall = masterCall.equals("SUB-ACTIVITY") ? "Sub Activity Saved Successfully" : "" ;
							masterResponse.setOutcome(true);
							break;
					}


			}

			masterResponse.setData(masterVO);
			masterResponse.setMessage(responseCall);

		}catch (Exception e) {
			log.error("Error in ArticleMasterServiceImpl.getMasterData() : ", e);
			masterResponse.setData(masterVO);
			masterResponse.setOutcome(false);
			masterResponse.setMessage("Something Went Wrong");
		}
		return masterResponse;
	}


	public  String[] asStrings(Object... objArray) {
		String[] strArray = new String[objArray.length];
		for (int i = 0; i < objArray.length; i++)
			strArray[i] = String.valueOf(objArray[i]);
		return strArray;
	}
	
	


	@Override
	public ServiceOutcome<List<Component>> listComponent(boolean includeDeleted) {
		ServiceOutcome<List<Component>> svcOutcome = new ServiceOutcome<>();
		List<Component> lstComponent = null;
		try {
			if(includeDeleted) {
				lstComponent = componentRepository.findAll();
			}else {
				lstComponent = componentRepository.findByIsActive(true);
			}


			svcOutcome.setData(lstComponent);
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Component> saveAndupdateComponent(Component component, RedirectAttributes attributes, Object CmpCode) {
		ServiceOutcome<Component> svcOutcome = new ServiceOutcome<>();
		User user = SecurityHelper.getCurrentUser();
		Component data = null;
		String msg = "msg.error";
		StringBuilder ComponentCode= new StringBuilder();
		char code;
		try {
			String componentName=component.getCmpNameEn();
			String[] cmp=componentName.split(" ");
			for(String code1: cmp ) {
				code= code1.charAt(0);
				ComponentCode.append(code);
				//fundCode[i]=code;

			}
			if (component.getCmpId() != null) {
				Component	updcomponent = componentRepository.getOne(component.getCmpId());
				updcomponent.setCmpNameEn( component.getCmpNameEn());
				updcomponent.setCmpCode(CmpCode.toString());
				updcomponent.setCmpCode(component.getCmpCode()) ;
				updcomponent.setCmpDescription(component.getCmpDescription());
				updcomponent.setIsActive(true);
				updcomponent.setLastUpdatedOn(new Date());
				updcomponent.setLastUpdatedBy(user);
//				updscheme.setSubFundType(component.getSubFundType());
				component = componentRepository.save(updcomponent);
				data = component;
				msg = "msg.success";
			} else {
				ComponentCode.append(RandomNumberGenerator.getRandomNumber());
				Component dupcomponent = componentRepository.findByCmpCode(CmpCode.toString());
				if(dupcomponent!= null) {
					svcOutcome.setData(data);
					svcOutcome.setOutcome(true);
					msg="msg.duplicateCode";
					svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
					return svcOutcome;
				}else {
					ComponentCode.append(RandomNumberGenerator.getRandomNumber());
					component.setCreatedBy(user);
					component.setCreatedOn(new Date());
					component.setCmpNameEn( component.getCmpNameEn());
					component.setCmpCode(CmpCode.toString());
					component.setCmpDescription(component.getCmpDescription());
					component.setIsActive(true);
					component = componentRepository.save(component);
					data = component;
					msg = "msg.success";
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
		}
		svcOutcome.setData(data);
		svcOutcome.setOutcome(true);
		svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Component> getComponent(Long componentId) {
		ServiceOutcome<Component> svcOutcome = new ServiceOutcome<>();
		try {
			Component component = componentRepository.findById(componentId).get();
			svcOutcome.setData(component);
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Component> changeComponentStatus(String actionType, Long componentId) {
		ServiceOutcome<Component> svcOutcome = new ServiceOutcome<>();
		User user = SecurityHelper.getCurrentUser();
		Component data = null;
		String msg = "msg.error";
		try {
			Component component = componentRepository.getOne(componentId);
			if(actionType.equals("INACTIVE")) {
				component.setIsActive(false);
			}
			if(actionType.equals("ACTIVE")) {
				component.setIsActive(true);
			}
			component.setLastUpdatedOn(new Date());
			component.setLastUpdatedBy(user);
			component = componentRepository.save(component);
			data = component;
			msg = "msg.success";
		}catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
		}
		svcOutcome.setData(data);
		svcOutcome.setOutcome(true);
		svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
		return svcOutcome;

	}

	@Override
	public ServiceOutcome<Component> saveComponent(Component component) {
		ServiceOutcome<Component> svcOutcome = new ServiceOutcome<>();
		User user = SecurityHelper.getCurrentUser();
		try {
			if (component.getCmpId() != null) {
				Component prvCmp = componentRepository.findById(component.getCmpId()).get();
				prvCmp.setCmpNameEn(component.getCmpNameEn().trim());
				prvCmp.setCmpNameHi(component.getCmpNameEn().trim());
				prvCmp.setSchemeCode(schemeRepository.findBySchemeCode(component.getSchemeCode().getSchemeCode()));
				prvCmp.setCmpDescription(component.getCmpDescription().trim());
//				prvCmp.setCmpDuration(component.getCmpDuration());
				prvCmp.setLastUpdatedOn(new Date());
				prvCmp.setLastUpdatedBy(user);
				componentRepository.save(prvCmp);
				svcOutcome.setMessage(messageSource.getMessage("msg.success.compUpdt", null, LocaleContextHolder.getLocale()));
			} else {
				component.setCreatedBy(user);
				component.setCreatedOn(new Date());
				component.setIsActive(true);
				component.setCmpCode("COMP-"+RandomStringUtils.random(6, true, true).toUpperCase());
				component.setCmpNameEn(component.getCmpNameEn().trim());
				component.setCmpNameHi(component.getCmpNameEn().trim());
				component.setSchemeCode(schemeRepository.findBySchemeCode(component.getSchemeCode().getSchemeCode()));
//				component.setCmpDuration(component.getCmpDuration());
				component.setCmpDescription(component.getCmpDescription().trim());
				componentRepository.save(component);
				svcOutcome.setMessage(messageSource.getMessage("msg.success.comp", null, LocaleContextHolder.getLocale()));
			}

		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}


	@Override
	public ServiceOutcome<List<Component>> componentCodeList() {
		ServiceOutcome<List<Component>> comp = new ServiceOutcome<>();
		List<Component> listOutName=new ArrayList<>();
		try {
//			listOutName=componentRepository.findByIsActiveTrueOrderByCmpNameEn();
			// get component list from category group by component

			List<Category> list=categoryRepository.findAllByIsActiveTrue();
			listOutName=list.stream().map(Category::getCmpCode).distinct().collect(Collectors.toList());
			comp.setData(listOutName);
		}catch(Exception e) {
			logger.error(e);
			comp.setData(null);
			comp.setOutcome(false);
			comp.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}
		return comp;
	}


	@Override
	public ServiceOutcome<MasterVO> mstActiveInActive(MasterVO masterVO, String masterCall, Boolean isActive) {
		ServiceOutcome<MasterVO> servc = new ServiceOutcome<>();
		try {
			switch(masterCall) {
			/*
			 * case "FUNCTIONAL-HEAD" : FunctionalHead fnHead =
			 * functionalHeadRepository.findByHeadCode(masterVO.getChildCode()); if(fnHead
			 * != null) { fnHead.setIsActive(isActive);
			 * functionalHeadRepository.save(fnHead); }
			 * servc.setMessage(messageSource.getMessage(isActive.equals(false) ?
			 * "msg.success.inActFnHead" : "msg.success.actFnHead", null,
			 * LocaleContextHolder.getLocale())); break;
			 */
				
				case "ACTIVITY" :
//					List<Aap> data = aapRepository.findAllByActCodeActCode(masterVO.getChildCode());
//					if(data.size() > 0) {
//						servc.setMessage(messageSource.getMessage(isActive.equals(false) ? "msg.alert.inActAct" : "msg.alert.actAct", null, LocaleContextHolder.getLocale()));
//					}else {
						Activity actv = activityRepository.findByActCode(masterVO.getChildCode());
						List<MapActivity> actMap = mapActivityRepository.findByActCodeActCode(masterVO.getChildCode());
						if(actv != null && actMap.size() > 0) {
							actv.setIsActive(isActive);
							activityRepository.save(actv);
							actMap.forEach(d ->{
								d.setIsActive(isActive);
								mapActivityRepository.save(d);
							});
						}
						servc.setMessage(messageSource.getMessage(isActive.equals(false) ? "msg.success.inActAct" : "msg.success.actAct", null, LocaleContextHolder.getLocale()));
//					}

				break;

				case "SUB-ACTIVITY":
//					List<Aap> dataSubAct = aapRepository.findAllBySubActCodeSubActCode(masterVO.getChildCode());
//					if(dataSubAct.size() > 0) {
//						servc.setMessage(messageSource.getMessage(isActive.equals(false) ? "msg.alert.inSubActAct" : "msg.alert.actSubAct", null, LocaleContextHolder.getLocale()));
//					}else {
						SubActivity subActv = subActivityRepository.findBySubActCode(masterVO.getChildCode());
						List<MapSubActivity> subActctMap = mapSubActivityRepository.findBySubActCodeSubActCode(masterVO.getChildCode());
						if(subActv != null && subActctMap.size() > 0) {
							subActv.setIsActive(isActive);
							subActivityRepository.save(subActv);
							subActctMap.forEach(d ->{
								d.setIsActive(isActive);
								mapSubActivityRepository.save(d);
							});
						}
						servc.setMessage(messageSource.getMessage(isActive.equals(false) ? "msg.success.inActSubAct" : "msg.success.actSubAct", null, LocaleContextHolder.getLocale()));
//					}
					break;
			}
		} catch (Exception e) {
			logger.error(e);
			servc.setData(null);
			servc.setOutcome(false);
			servc.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return servc;
	}


	@Override
	public ServiceOutcome<List<Scheme>> listSchemeName() {
		ServiceOutcome<List<Scheme>> scheme = new ServiceOutcome<>();
		try {
			List<Scheme> listOut = schemeRepository.findByIsActiveTrueOrderBySchemeNameEn();
			scheme.setData(listOut);
		}catch(Exception e) {
			logger.error(e);
			scheme.setData(null);
			scheme.setOutcome(false);
			scheme.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}
		return scheme;
	}

	//AgencyType

	
	
}	
		