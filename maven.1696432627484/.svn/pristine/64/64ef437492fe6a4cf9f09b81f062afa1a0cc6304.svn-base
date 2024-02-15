package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.RoleLevelMap;
import com.aashdit.umt.model.User;
import com.aashdit.umt.repository.RoleLevelMapRepository;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.service.RoleService;
import com.aashdit.umt.service.UserService;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.*;
import com.fpo.web.repositories.*;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.vos.CbboVO;
import com.fpo.web.vos.FarmerCbboBlkMapVO;
import com.fpo.web.vos.FarmerCbboMngmtVO;
import com.fpo.web.vos.FarmerCbboVO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class CBBOServicesImpl implements CBBOServices{

    @Autowired
    private FarmerCbboRepository farmerCbboRepository;

    @Autowired
    private FarmerCbboMngmtRepository farmerCbboMngmtRepository;

    @Autowired
    private FarmerCbboBlkMapRepository farmerCbboBlkMapRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private BlockRepository blockRepository;
    
    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private DistrictRepository distRepository;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleLevelMapRepository roleLevelMapRepository;
    
    @Autowired
    private AccessService accessService;
    
    @Autowired
    private CommonService commonService;




    @Override
    @Transactional
    public ServiceOutcome<Boolean> manageCBBO(CbboVO cbboVO) {
        ServiceOutcome<Boolean> svc = new ServiceOutcome<>();
        Boolean cbboFlag=false;
        List<FarmerCbboMngmt> cbboMngmtList= new ArrayList<>();
        Random rand = new Random();
        Long[] isPrimary=new Long[] {1l};
        try {
        	User user = SecurityHelper.getCurrentUser();
        	FarmerCbbo fpo = Optional.ofNullable(cbboVO.getFarmerCbboVO().getId())
      			  					 .flatMap(farmerCbboRepository::findById)
      			  					 .orElseGet(FarmerCbbo::new);
        	String existingCbboCode = fpo.getCbboCode();
        	BeanUtils.copyProperties(cbboVO.getFarmerCbboVO(),fpo);
            fpo.setScheme(schemeRepository.findBySchemeId(cbboVO.getFarmerCbboVO().getScheme().getSchemeId()));
        	
            if(!Optional.ofNullable(cbboVO.getFarmerCbboVO().getId()).isPresent()){
//                FarmerCbbo fCbbo=new FarmerCbbo();
    			    String cbboCode = "CBBO-" + rand.ints(48, 100)
    			        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
    			        .limit(6)
    			        .mapToObj(c -> (char) c)
    			        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
    			        .toString();
    			    fpo.setCbboCode(cbboCode);
    			    fpo.setCreatedBy(user);
    			    fpo.setCreatedOn(new Date());
            }else {
            	fpo.setCbboCode(existingCbboCode);
			    fpo.setLastUpdatedBy(user);
			    fpo.setLastUpdatedOn(new Date());
            }
            fpo.setIsActive(true);
            FarmerCbbo saveCbbo = farmerCbboRepository.save(fpo);
            System.out.println(saveCbbo);
            
//           if(Optional.ofNullable(cbboVO.getFarmerCbboBlkMapVO().getBlock().getBlockId()).isPresent()){
//                Block block = blockRepository.findById(cbboVO.getFarmerCbboBlkMapVO().getBlock().getBlockId()).get();
//                FarmerCbboBlkMap fcbm = new FarmerCbboBlkMap();
//                fcbm.setCbbo(saveCbbo);
//                fcbm.setBlock(block);
//
//                if(Optional.ofNullable(cbboVO.getFarmerCbboVO().getId()).isPresent()){
//                    fcbm.setLastUpdatedBy(user);
//                    fcbm.setLastUpdatedOn(new Date());
//                }else{
//                    fcbm.setCreatedBy(user);
//                    fcbm.setCreatedOn(new Date());
//                }
//                fcbm.setIsActive(true);
//               farmerCbboBlkMapRepository.save(fcbm);
//               System.out.println(fcbm);
//            }
//            List<Item> itemsOnlyInList2 = (List<Item>) CollectionUtils.subtract(list2, list1); for comparing
            List<Long> saveCbbboBlkList = new ArrayList<>();
            Long[] blockIds = cbboVO.getBlockId();
            if (Optional.ofNullable(cbboVO.getBlockId()).isPresent()) {

                List<FarmerCbboBlkMap> findByCbboId = farmerCbboBlkMapRepository.findByCbboId(saveCbbo.getId());
                if(findByCbboId.size()>0) {
                	findByCbboId.forEach(existingCbboBlk->{
                		
                		Arrays.stream(blockIds)
                		.forEach(blockId -> {
                			Block block = blockRepository.findByBlockId(blockId);
                			if (block != null) {
                				System.out.println("Found Block: " + block);
                				existingCbboBlk.setCbbo(saveCbbo);
                				existingCbboBlk.setBlock(block);
                				
                				existingCbboBlk.setCreatedBy(user);
                				existingCbboBlk.setCreatedOn(new Date());
                				existingCbboBlk.setIsActive(true);
                				FarmerCbboBlkMap saveCbbboBlk = farmerCbboBlkMapRepository.save(existingCbboBlk);
                				saveCbbboBlkList.add(saveCbbboBlk.getBlock().getBlockId());
                				System.out.println("FarmerCbboBlkMap->"+ existingCbboBlk);
                			}
                		});
                	});
                }else {
                	Arrays.stream(blockIds)
                    .forEach(blockId -> {
                        Block block = blockRepository.findByBlockId(blockId);
							if (block != null) {
								System.out.println("Found Block: " + block);
								FarmerCbboBlkMap fcbm = new FarmerCbboBlkMap();
								fcbm.setCbbo(saveCbbo);
								fcbm.setBlock(block);

								fcbm.setLastUpdatedBy(user);
								fcbm.setLastUpdatedOn(new Date());
								
								fcbm.setIsActive(true);
								FarmerCbboBlkMap saveCbbboBlk = farmerCbboBlkMapRepository.save(fcbm);
								saveCbbboBlkList.add(saveCbbboBlk.getBlock().getBlockId());
								System.out.println("FarmerCbboBlkMap->"+ fcbm);
							}
                    });
                }
                
                
            }

			  if(Optional.ofNullable(saveCbbo).isPresent()){
			  cbboVO.getFarmerCbboMngmtVO().forEach(d->{

                  FarmerCbboMngmt cbboMngmt = Optional.ofNullable(d.getId())
                          .flatMap(farmerCbboMngmtRepository::findById)
                          .orElseGet(FarmerCbboMngmt::new);

                  if(Optional.ofNullable(cbboMngmt.getId()).isPresent()){
                      BeanUtils.copyProperties(d,cbboMngmt);
                      cbboMngmt.setGender(genderRepository.findByGenderId(d.getGender().getGenderId()));
                      cbboMngmt.setDesignation(designationRepository.findByDesignationId(d.getDesignation().getDesignationId()));
                      cbboMngmt.setLastUpdatedBy(user);
                      cbboMngmt.setLastUpdatedOn(new Date());
                      cbboMngmt.setIsActive(true);
                      cbboMngmt.setCbbo(saveCbbo);
                  }else{
//                      FarmerCbboMngmt cbboMngmt=new FarmerCbboMngmt();
                      BeanUtils.copyProperties(d,cbboMngmt);
                      cbboMngmt.setGender(genderRepository.findByGenderId(d.getGender().getGenderId()));
                      cbboMngmt.setDesignation(designationRepository.findByDesignationId(d.getDesignation().getDesignationId()));
                      cbboMngmt.setCreatedBy(user);
                      cbboMngmt.setCreatedOn(new Date());
                      cbboMngmt.setIsActive(true);
//                      cbboMngmt.setIsUserCreation(true);
                      cbboMngmt.setCbbo(saveCbbo);
                  }
                  cbboMngmtList.add(cbboMngmt);
              });

			  }
			  if(cbboMngmtList.size()>0){
			  List<FarmerCbboMngmt> saveAllfarmerCbboMngmt = farmerCbboMngmtRepository.saveAll(cbboMngmtList);
//			  saveAllfarmerCbboMngmt.forEach(mngmntUser->{
//				  if(mngmntUser.getIsTrainer() && cbboVO.getFarmerCbboVO().getStatus().equalsIgnoreCase("REGISTERED")) {
//					  ServiceOutcome<Role> role = roleService.getRoleByCode(ApplicationConstants.ROLE_TRAINER);
//					  Long[] roleId=new Long[] {role.getData().getRoleId()};
//					  ServiceOutcome<User> newUser = userService.addUser(mngmntUser.getCbboMngmtName(),mngmntUser.getCbboMngmtName(),"",new Date(),"NA" ,"NA",roleId,isPrimary,role.getData().getRoleCode());
//					  List<RoleLevelMap> rolelevelMapList=roleLevelMapRepository.findByRoleId(roleId[0]);
//					  saveCbbboBlkList.forEach(blkId->{
//						  ServiceOutcome<String> svcOutcome = accessService.saveConfig(newUser.getData().getUserId(), roleId[0], rolelevelMapList.get(0).getLevelId(), blkId);
//					  });
//					  mngmntUser.setUserId(newUser.getData());
//					  mngmntUser.setIsUserCreation(true);
//					  farmerCbboMngmtRepository.save(mngmntUser);
//					  System.out.println(mngmntUser);
//				  }
//			  });
			  }
			  
//			  commonService.userCreation();
				if (/* !Optional.ofNullable(cbboVO.getFarmerCbboVO().getId()).isPresent() && */ cbboVO.getFarmerCbboVO().getStatus().equalsIgnoreCase("REGISTERED")) {
				  
				  ServiceOutcome<Role> role = roleService.getRoleByCode(ApplicationConstants.ROLE_CBBO);
				  Long[] roleId=new Long[] {role.getData().getRoleId()};
				  ServiceOutcome<User> newUser = userService.addUser(saveCbbo.getCbboCode(),saveCbbo.getCbboName(),"",new Date(),"NA" ,"NA",roleId,isPrimary,role.getData().getRoleCode());
				  List<RoleLevelMap> rolelevelMapList=roleLevelMapRepository.findByRoleId(roleId[0]);
				  Arrays.stream(blockIds)
				  .forEach(blockId->{
					  ServiceOutcome<String> svcOutcome = accessService.saveConfig(newUser.getData().getUserId(), roleId[0], rolelevelMapList.get(0).getLevelId(), blockId);
				  });
				  saveCbbo.setUserId(newUser.getData());
				  farmerCbboRepository.save(saveCbbo);
				  System.out.println(saveCbbo);
			  }
			 
			  cbboFlag=true;
            svc.setData(cbboFlag);
            svc.setOutcome(true);
            svc.setMessage(Optional.ofNullable(cbboVO.getFarmerCbboVO().getId()).isPresent()? "Cbbo Updated Succesfull" : "Cbbo Register Succesfull");
        }catch (Exception e){
            e.printStackTrace();
            svc.setData(cbboFlag);
            svc.setOutcome(false);
            svc.setMessage("FAILURE");
        }

        return svc;
    }

    @Override
    public ServiceOutcome<CbboVO> getCbboByCbboCode(String cbboCode) {
        ServiceOutcome<CbboVO> soc = new ServiceOutcome<>();
        CbboVO cbboVO = new CbboVO();
        FarmerCbboVO farmerCbboVO = new FarmerCbboVO();
        List<FarmerCbboMngmtVO> farmerCbboMngmtVOList = new ArrayList<>();
        FarmerCbboBlkMapVO farmerCbboBlkMapVO = new FarmerCbboBlkMapVO();
        Long[] blockArr = new Long[25];
        try {

            if(Optional.ofNullable(cbboCode).isPresent()){
                FarmerCbbo existingCbbo = farmerCbboRepository.findByCbboCode(cbboCode);
                BeanUtils.copyProperties(existingCbbo, farmerCbboVO);
                cbboVO.setFarmerCbboVO(farmerCbboVO);
                cbboVO.setBankName(farmerCbboVO.getBankBranch().getBankName());
                cbboVO.setBranchName(farmerCbboVO.getBankBranch().getBranchName());
                cbboVO.setIfsc(farmerCbboVO.getBankBranch().getBankBranchId());

                List<FarmerCbboMngmt> farmerCbboMngmt = farmerCbboMngmtRepository.findByCbboId(existingCbbo.getId());
                farmerCbboMngmt.forEach(mngmnt->{
                    FarmerCbboMngmtVO farmerCbboMngmtVO = new FarmerCbboMngmtVO();
                    BeanUtils.copyProperties(mngmnt, farmerCbboMngmtVO);
                    farmerCbboMngmtVOList.add(farmerCbboMngmtVO);
                });
                cbboVO.setFarmerCbboMngmtVO(farmerCbboMngmtVOList);

                List<FarmerCbboBlkMap> fcbm = farmerCbboBlkMapRepository.findByCbboId(existingCbbo.getId());
                AtomicInteger count = new AtomicInteger(0);
                fcbm.forEach(cbboBlk -> {
                    int currentCount = count.incrementAndGet();
                    System.out.println("Block-" + cbboBlk.getBlock().getBlockId());
                    Block block = blockRepository.findByBlockId(cbboBlk.getBlock().getBlockId());
                    if (currentCount == 1) {
                    	District district = distRepository.findByDistrictId(block.getDistrict().getDistrictId());
                        cbboVO.setDistId(district.getDistrictId());

                        State state = stateRepository.findByStateId(district.getState().getStateId());
                        cbboVO.setStateId(state.getStateId());
                    }
                    
                    blockArr[currentCount] = block.getBlockId();
                });
                System.out.println(blockArr);

                
                BeanUtils.copyProperties(fcbm, farmerCbboBlkMapVO);
                cbboVO.setFarmerCbboBlkMapVO(farmerCbboBlkMapVO);
                

                soc.setData(cbboVO);
                soc.setOutcome(true);
                soc.setMessage("Success");
            }

        }catch(Exception e){
            e.printStackTrace();
            soc.setData(null);
            soc.setOutcome(false);
            soc.setMessage("FAILURE");
        }
        return soc;
    }
}
