package com.fpo.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.RoleLevelMap;
import com.aashdit.umt.model.RoleRightLevelMaster;
import com.aashdit.umt.model.User;
import com.aashdit.umt.model.UserRoleMap;
import com.aashdit.umt.model.UserRoleRightLevel;
import com.aashdit.umt.repository.RoleLevelMapRepository;
import com.aashdit.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.umt.repository.UserRoleMapRepository;
import com.aashdit.umt.repository.UserRoleRightLevelRepository;

@Component
public class UserAdminstrativeUtil {
	
	@Autowired
	private  UserRoleMapRepository userRoleMapRepository; 
	
	@Autowired
	private  UserRoleRightLevelRepository userRoleRightLevelRepository; 
	
	@Autowired
	private  RoleLevelMapRepository roleLevelMapRepository; 
	
	@Autowired
	private  RoleRightLevelMasterRepository roleRightLevelMasterRepository; 
	
	public  Long getAdminstrativeId(User user) {
		
		Long demographyId = null;
		Role primaryRole = user.getPrimaryRole(); 
		if(primaryRole != null) {
			Long roleId = primaryRole.getRoleId(); 
			UserRoleMap userRoleMap = userRoleMapRepository.findByUserIdAndRoleId(user.getUserId(), roleId); 
			if(userRoleMap != null) {
				Long userRoleId = userRoleMap.getUserRoleId();
				List<UserRoleRightLevel> userRoleRightLevelList = new ArrayList<UserRoleRightLevel>(); 
				List<UserRoleRightLevel> allUserRoleRightLevelList = userRoleRightLevelRepository.findAll(); 
				if(allUserRoleRightLevelList != null && allUserRoleRightLevelList.size() > 0) { 
					for(UserRoleRightLevel userRoleRightLevel:allUserRoleRightLevelList) {
						if(userRoleRightLevel.getUserRoleId().equals(userRoleId) && userRoleRightLevel.getIsActive() == true) { 
							userRoleRightLevelList.add(userRoleRightLevel); 
						}
					}
				}
				if(userRoleRightLevelList != null && userRoleRightLevelList.size() > 0) { 
					UserRoleRightLevel userRoleRightLevel = userRoleRightLevelList.get(0); 
					Long roleLevelId = userRoleRightLevel.getRoleLevelId(); 
					RoleLevelMap roleLevelMap = roleLevelMapRepository.findById(roleLevelId).get(); 
					if(roleLevelMap != null) {
						Long roleRightLevelId = roleLevelMap.getLevelId();
						RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository.findById(roleRightLevelId).get();
						if(roleRightLevelMaster != null) {
							demographyId = userRoleRightLevel.getObjectId(); 
						}
					}
				}
			}
		}
		
		return demographyId; 
	}
	
	public  String getRoleLevelCode(User user) {
		
		String roleLevelCode = null;
		Role primaryRole = user.getPrimaryRole(); 
		if(primaryRole != null) {
			Long roleId = primaryRole.getRoleId(); 
			UserRoleMap userRoleMap = userRoleMapRepository.findByUserIdAndRoleId(user.getUserId(), roleId); 
			if(userRoleMap != null) {
				Long userRoleId = userRoleMap.getUserRoleId();
				List<UserRoleRightLevel> userRoleRightLevelList = new ArrayList<UserRoleRightLevel>(); 
				List<UserRoleRightLevel> allUserRoleRightLevelList = userRoleRightLevelRepository.findAll(); 
				if(allUserRoleRightLevelList != null && allUserRoleRightLevelList.size() > 0) { 
					for(UserRoleRightLevel userRoleRightLevel:allUserRoleRightLevelList) {
						if(userRoleRightLevel.getUserRoleId().equals(userRoleId) && userRoleRightLevel.getIsActive() == true) { 
							userRoleRightLevelList.add(userRoleRightLevel); 
						}
					}
				}
				if(userRoleRightLevelList != null && userRoleRightLevelList.size() > 0) { 
					UserRoleRightLevel userRoleRightLevel = userRoleRightLevelList.get(0); 
					Long roleLevelId = userRoleRightLevel.getRoleLevelId(); 
					RoleLevelMap roleLevelMap = roleLevelMapRepository.findById(roleLevelId).get(); 
					if(roleLevelMap != null) {
						Long roleRightLevelId = roleLevelMap.getLevelId();
						RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository.findById(roleRightLevelId).get();
						if(roleRightLevelMaster != null) {
							roleLevelCode = roleRightLevelMaster.getLevelCode(); 
						}
					}
				}
			}
		}
		
		return roleLevelCode; 
	}
	
	public List<Long> getAllAdminstrativeId(User user) {

//		Long demographyId = null;
		List<Long> demographyList = new ArrayList<>();
		Role primaryRole = user.getPrimaryRole();
		if (primaryRole != null) {
			Long roleId = primaryRole.getRoleId();
			UserRoleMap userRoleMap = userRoleMapRepository.findByUserIdAndRoleId(user.getUserId(), roleId);
			if (userRoleMap != null) {
				Long userRoleId = userRoleMap.getUserRoleId();
				List<UserRoleRightLevel> userRoleRightLevelList = new ArrayList<UserRoleRightLevel>();
				List<UserRoleRightLevel> allUserRoleRightLevelList = userRoleRightLevelRepository.findAll();
				if (allUserRoleRightLevelList != null && allUserRoleRightLevelList.size() > 0) {
					for (UserRoleRightLevel userRoleRightLevel : allUserRoleRightLevelList) {
						if (userRoleRightLevel.getUserRoleId().equals(userRoleId)
								&& userRoleRightLevel.getIsActive() == true) {
							userRoleRightLevelList.add(userRoleRightLevel);
						}
					}
				}
				if (userRoleRightLevelList != null && userRoleRightLevelList.size() > 0) {
					//===============
//					UserRoleRightLevel userRoleRightLevel = userRoleRightLevelList.get(0);
//					Long roleLevelId = userRoleRightLevel.getRoleLevelId();
//					RoleLevelMap roleLevelMap = roleLevelMapRepository.findById(roleLevelId).get();
//					if (roleLevelMap != null) {
//						Long roleRightLevelId = roleLevelMap.getLevelId();
//						RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository
//								.findById(roleRightLevelId).get();
//						if (roleRightLevelMaster != null) {
//							demographyId = userRoleRightLevel.getObjectId();
//						}
//					}
					//============
					userRoleRightLevelList.forEach(rightLevel->{
						Long roleLevelId = rightLevel.getRoleLevelId();
						RoleLevelMap roleLevelMap = roleLevelMapRepository.findById(roleLevelId).get();
						if (roleLevelMap != null) {
							Long roleRightLevelId = roleLevelMap.getLevelId();
							RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository
									.findById(roleRightLevelId).get();
							if (roleRightLevelMaster != null) {
								Long demographyId = rightLevel.getObjectId();
								demographyList.add(demographyId);
							}
						}
					});
				}
			}
		}

		return demographyList;
	}
	
}
	