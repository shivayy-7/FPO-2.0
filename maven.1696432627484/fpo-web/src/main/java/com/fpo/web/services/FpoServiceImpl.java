package com.fpo.web.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.fpo.web.utils.UploadFile;
import com.fpo.web.utils.UserAdminstrativeUtil;
import com.fpo.web.utils.YamlAppProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.velocity.runtime.directive.Parse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.Role;
import com.aashdit.umt.model.RoleLevelMap;
import com.aashdit.umt.model.User;
import com.aashdit.umt.repository.RoleLevelMapRepository;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.service.RoleService;
import com.aashdit.umt.service.UserService;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.Activity;
import com.fpo.web.entities.Block;
import com.fpo.web.entities.Component;
import com.fpo.web.entities.Designation;
import com.fpo.web.entities.Document;
import com.fpo.web.entities.FarmerDocument;
import com.fpo.web.entities.Fpo;
import com.fpo.web.entities.FpoDemographyMap;
import com.fpo.web.entities.FpoManagement;
import com.fpo.web.entities.FpoSchemeMap;
import com.fpo.web.entities.FpoSubactivityMap;
import com.fpo.web.entities.Gender;
import com.fpo.web.entities.MapActivity;
import com.fpo.web.entities.MapSubActivity;
import com.fpo.web.entities.Scheme;
import com.fpo.web.entities.SubActivity;
import com.fpo.web.entities.Training;
import com.fpo.web.entities.TrainingFpoMap;
import com.fpo.web.repositories.ActivityRepository;
import com.fpo.web.repositories.BlockRepository;
import com.fpo.web.repositories.CasteRepository;
import com.fpo.web.repositories.DesignationRepository;
import com.fpo.web.repositories.DistrictRepository;
import com.fpo.web.repositories.DocumentRepository;
import com.fpo.web.repositories.FarmerCbboMngmtRepository;
import com.fpo.web.repositories.FarmerDocumentRepository;
import com.fpo.web.repositories.FpoDemographyMapRepository;
import com.fpo.web.repositories.FpoManagementRepository;
import com.fpo.web.repositories.FpoRepository;
import com.fpo.web.repositories.FpoSchemeMapRepository;
import com.fpo.web.repositories.FpoSubactivityMapRepository;
import com.fpo.web.repositories.GenderRepository;
import com.fpo.web.repositories.GrampanchayatRpository;
import com.fpo.web.repositories.MapActivityRepository;
import com.fpo.web.repositories.MapSubActivityRepository;
import com.fpo.web.repositories.SchemeRepository;
import com.fpo.web.repositories.SubActivityRepository;
import com.fpo.web.repositories.TrainingFpoMapRepository;
import com.fpo.web.repositories.TrainingRepository;
import com.fpo.web.repositories.VillageRepository;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.utils.CommonUploadFile;
import com.fpo.web.vos.FpoDemographyVO;
import com.fpo.web.vos.FpoDocumentVO;
import com.fpo.web.vos.FpoDtls;
import com.fpo.web.vos.FpoManagementVo;
import com.fpo.web.vos.FpoSchemeMapVO;
import com.fpo.web.vos.FpoVo;
import com.fpo.web.vos.TrainingVO;
import com.ibm.icu.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Service
@Slf4j
public class FpoServiceImpl implements FpoService {
	
	@Autowired private FpoRepository fpoRepository;
	
	@Autowired private FpoManagementRepository fpoMngmtRepository;
	
	@Autowired private ModelMapper modelMapper;
	
	@Autowired private FpoSubactivityMapRepository fpoSubActMapRepository;
	
	@Autowired private SubActivityRepository subActivityRepository;
	
	@Autowired private DesignationRepository designationRepository;
	
	@Autowired private GenderRepository genderRepository;
	
	@Autowired private MapSubActivityRepository mapSubActivityRepository;
	
	@Autowired private FpoSubactivityMapRepository fpoSubactivityMapRepository;

	@Autowired private MapActivityRepository mapActRepository;
	
	@Autowired private ActivityRepository actRepository;
	
	@Autowired private SchemeRepository schemeRepository;
	
	@Autowired private TrainingRepository trainingRepository;
	
	@Autowired private FarmerCbboMngmtRepository farmerCbboMngmtRepository;
	
	@Autowired private RoleService roleService;
    
    @Autowired private UserService userService;
    
    @Autowired private RoleLevelMapRepository roleLevelMapRepository;
    
    @Autowired private AccessService accessService;

	@Autowired private YamlAppProperties YamlAppProperties;
    
    @Autowired private DocumentRepository documentRepository;
    
    @Autowired private FarmerDocumentRepository farmerDocumentRepository;
    
    @Autowired private BlockRepository blockRepository;
    
    @Autowired private TrainingFpoMapRepository trainingFpoMapRepository;
    
    @Autowired private CommonService commonService;
	
	@Autowired private UserAdminstrativeUtil userAdminstrative;
	
	@Autowired private CasteRepository casteRepository;
	
	@Autowired private FpoSchemeMapRepository fpoSchemeMapRepository;
	
	@Autowired private FpoDemographyMapRepository fpoDemographyMapRepository;
	
	@Autowired private DistrictRepository districtRepository;
	
	@Autowired private GrampanchayatRpository grampanchayatRpository;
	
	@Autowired private VillageRepository villageRepository;
	
	
	@Override
//	@Transactional
	public ServiceOutcome<Boolean> saveFpoDtls(FpoDtls fpoDtls) {
		ServiceOutcome<Boolean> soc = new ServiceOutcome<>();
		Random rand = new Random();
		User user = SecurityHelper.getCurrentUser();
		Long[] isPrimary=new Long[] {1l};
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			//For Fpo 
			  Fpo existingFpo = Optional.ofNullable(fpoDtls.getFpoVo().getFpoId())
					  					.flatMap(fpoRepository::findById)
					  					.map(existing->{
					  						BeanUtils.copyProperties(fpoDtls.getFpoVo(), existing);
					  						existing.setFpoCode(fpoDtls.getFpoVo().getFpoCode());
					  						existing.setLastUpdatedBy(user);
					  						existing.setLastUpdatedOn(new Date());
					  						return existing;
					  					})
					  					.orElseGet(()->{
					  						Fpo newFpo = new Fpo();
					  						BeanUtils.copyProperties(fpoDtls.getFpoVo(), newFpo);
					  						String fpoCode = "FPO_" + rand.ints(48, 100)
					  				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
					  				        .limit(6)
					  				        .mapToObj(c -> (char) c)
					  				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
					  				        .toString();
					  						newFpo.setFpoCode(fpoCode);
					  						newFpo.setCreatedBy(user);
					  						newFpo.setCreatedOn(new Date());
						  				    return newFpo;
					  					});
			
			existingFpo.setDateOfReg(adjustedFormat.parse(fpoDtls.getFpoVo().getDateOfReg().trim()));
			existingFpo.setDateOfIncorporation(adjustedFormat.parse(fpoDtls.getFpoVo().getDateOfIncorporation().trim()));
			existingFpo.setActive(true);
			existingFpo.setUserId(null);
			Fpo savedFpo = fpoRepository.save(existingFpo);

			//for Fpo Demography
			FpoDemographyMap fpoDemographymap = Optional.ofNullable(fpoDtls.getFpoDemographyVO().getDemographyId())
												        .flatMap(fpoDemographyMapRepository::findById)
												        .map(existingDemography->{
												        	BeanUtils.copyProperties(fpoDtls.getFpoDemographyVO(), existingDemography);
												        	existingDemography.setLastUpdatedBy(user);
												        	existingDemography.setLastUpdatedOn(new Date());
															return existingDemography;
												        })
												        .orElseGet(()->{
												        	FpoDemographyMap newDemographyMap = new FpoDemographyMap();
												            BeanUtils.copyProperties(fpoDtls.getFpoDemographyVO(), newDemographyMap);
												            newDemographyMap.setCreatedBy(user);
												            newDemographyMap.setCreatedOn(new Date());
												            return newDemographyMap;
												        });
			fpoDemographymap.setDistId(Optional.ofNullable(fpoDtls.getFpoDemographyVO().getDistId().getDistrictId()).isPresent()?districtRepository.findByDistrictId(fpoDtls.getFpoDemographyVO().getDistId().getDistrictId()) : null);
			fpoDemographymap.setBlockId(Optional.ofNullable(fpoDtls.getFpoDemographyVO().getBlockId().getBlockId()).isPresent()?blockRepository.findByBlockId(fpoDtls.getFpoDemographyVO().getBlockId().getBlockId()) : null);
//			fpoDemographymap.setGpId(Optional.ofNullable(fpoDtls.getFpoDemographyVO().getGpId().getGpId()).isPresent()?grampanchayatRpository.findByGpId(fpoDtls.getFpoDemographyVO().getGpId().getGpId()) : null);
//			fpoDemographymap.setVillageId(Optional.ofNullable(fpoDtls.getFpoDemographyVO().getVillageId().getVillageId()).isPresent()?villageRepository.findByVillageId(fpoDtls.getFpoDemographyVO().getVillageId().getVillageId()) : null);
			fpoDemographymap.setActive(true);
			fpoDemographymap.setFpoId(savedFpo);
			FpoDemographyMap saveFpoDemoMap = fpoDemographyMapRepository.save(fpoDemographymap);
			
			
			//for all FpoSubActivities Delete on update time
			if(Optional.ofNullable(fpoDtls.getFpoVo().getFpoId()).isPresent()) {
				List<FpoSubactivityMap> findByFpoFpoId = fpoSubActMapRepository.findByFpoFpoId(fpoDtls.getFpoVo().getFpoId());
				fpoSubActMapRepository.deleteAll(findByFpoFpoId);
			}
			
			// Add new FpoSubActivities
			System.out.println(fpoDtls.getFpoVo().getSubActivity());
			fpoDtls.getFpoVo().getSubActivity().forEach(subAct -> {
			    FpoSubactivityMap existingSubActMap = fpoSubActMapRepository.findByFpoAndSubActivityId(savedFpo, subActivityRepository.findBySubActCodeAndIsActiveTrue(subAct));

			    if (existingSubActMap != null) {
			    	existingSubActMap.setSubActivityId(subActivityRepository.findBySubActCodeAndIsActiveTrue(subAct));
			        existingSubActMap.setActive(true);
			        existingSubActMap.setLastUpdatedBy(user);
			        existingSubActMap.setLastUpdatedOn(new Date());
			        fpoSubActMapRepository.save(existingSubActMap);
			    } else {
			        FpoSubactivityMap newFpoSubActMap = new FpoSubactivityMap();
			        newFpoSubActMap.setActive(true);
			        newFpoSubActMap.setFpo(savedFpo);
			        newFpoSubActMap.setSubActivityId(subActivityRepository.findBySubActCodeAndIsActiveTrue(subAct));
			        newFpoSubActMap.setCreatedBy(user);
			        newFpoSubActMap.setCreatedOn(new Date());
			        fpoSubActMapRepository.save(newFpoSubActMap);
			    }
			});

			//For FpoManagement
			List<Long> fpoMngmntIds = fpoMngmtRepository.findByFpoIdFpoIdAndIsActive(savedFpo.getFpoId(), true)
													    .stream()
													    .map(FpoManagement::getDirectorId)
													    .collect(Collectors.toList());
			
			List<Long> existingFpoMngmntIds = new ArrayList<>();
			fpoDtls.getFpoMngmtVo().forEach(obj->{
//				Objects.requireNonNull(obj);
				if (obj.getName() == null) {
			        return;
			    }
				if (fpoMngmntIds.contains(obj.getDirectorId())) {
						FpoManagement findByDirectorId = fpoMngmtRepository.findByDirectorId(obj.getDirectorId());
						if(findByDirectorId != null) {
							BeanUtils.copyProperties(obj, findByDirectorId);
							findByDirectorId.setFpoId(savedFpo);
							findByDirectorId.setLastUpdatedBy(user);
							findByDirectorId.setLastUpdatedOn(new Date());
							try {
								findByDirectorId.setAppointmentDate(adjustedFormat.parse(obj.getAppointmentDate().trim()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							findByDirectorId.setGender(Optional.ofNullable(obj.getGender().getGenderId()).isPresent()? genderRepository.findById(obj.getGender().getGenderId()).get() : null  );
							findByDirectorId.setDesignation(Optional.ofNullable(obj.getDesignation().getDesignationId()).isPresent()? designationRepository.findById(obj.getDesignation().getDesignationId()).get() : null);
							findByDirectorId.setCaste(Optional.ofNullable(obj.getCaste().getCasteId()).isPresent()? casteRepository.findById(obj.getCaste().getCasteId()).get() : null);
							findByDirectorId.setActive(true);
							fpoMngmtRepository.save(findByDirectorId);
							existingFpoMngmntIds.add(obj.getDirectorId());
						}else {
							FpoManagement fpoManage = new FpoManagement();
							BeanUtils.copyProperties(obj, fpoManage);
							fpoManage.setCreatedBy(fpoManage.getCreatedBy());
							fpoManage.setCreatedOn(fpoManage.getCreatedOn());
							fpoManage.setFpoId(savedFpo);
							fpoManage.setLastUpdatedBy(user);
							fpoManage.setLastUpdatedOn(new Date());
							try {
								fpoManage.setAppointmentDate(adjustedFormat.parse(obj.getAppointmentDate().trim()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							fpoManage.setGender(Optional.ofNullable(obj.getGender().getGenderId()).isPresent()? genderRepository.findById(obj.getGender().getGenderId()).get() : null  );
							fpoManage.setDesignation(Optional.ofNullable(obj.getDesignation().getDesignationId()).isPresent()? designationRepository.findById(obj.getDesignation().getDesignationId()).get() : null);
							fpoManage.setCaste(Optional.ofNullable(obj.getCaste().getCasteId()).isPresent()? casteRepository.findById(obj.getCaste().getCasteId()).get() : null);
							fpoManage.setActive(true);
							fpoMngmtRepository.save(fpoManage);
						}
				}else {
					FpoManagement fpoManage = new FpoManagement();
					BeanUtils.copyProperties(obj, fpoManage);
					fpoManage.setCreatedBy(fpoManage.getCreatedBy());
					fpoManage.setCreatedOn(fpoManage.getCreatedOn());
					fpoManage.setFpoId(savedFpo);
					fpoManage.setLastUpdatedBy(user);
					fpoManage.setLastUpdatedOn(new Date());
					try {
						fpoManage.setAppointmentDate(adjustedFormat.parse(obj.getAppointmentDate().trim()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					fpoManage.setGender(Optional.ofNullable(obj.getGender().getGenderId()).isPresent()? genderRepository.findById(obj.getGender().getGenderId()).get() : null  );
					fpoManage.setDesignation(Optional.ofNullable(obj.getDesignation().getDesignationId()).isPresent()? designationRepository.findById(obj.getDesignation().getDesignationId()).get() : null);
					fpoManage.setCaste(Optional.ofNullable(obj.getCaste().getCasteId()).isPresent()? casteRepository.findById(obj.getCaste().getCasteId()).get() : null);
					fpoManage.setActive(true);
					fpoMngmtRepository.save(fpoManage);
				}
			});
			fpoMngmntIds.removeAll(existingFpoMngmntIds);
			
			fpoMngmntIds.forEach(existingMngmntId->{
				FpoManagement findByDirectorId = fpoMngmtRepository.findByDirectorId(existingMngmntId);
				findByDirectorId.setActive(false);
				fpoMngmtRepository.save(findByDirectorId);
			});
			
			
			// For Fpo Scheme Map
//			List<FpoSchemeMap> FpoSchemeMapList = new ArrayList<FpoSchemeMap>();
//			fpoDtls.getFpoSchemeMapVO().forEach(fpoScheme->{
//				
//				FpoSchemeMap fpoSchememap = Optional.ofNullable(fpoScheme.getSchemeMngmntId())
//											        .flatMap(fpoSchemeMapRepository::findById)
//											        .orElseGet(FpoSchemeMap::new);
//				
//				if(Optional.ofNullable(fpoSchememap.getSchemeMngmntId()).isPresent()) {
//					BeanUtils.copyProperties(fpoScheme, fpoSchememap);
//					fpoSchememap.setLastUpdatedBy(user);
//					fpoSchememap.setLastUpdatedOn(new Date());
//				}else {
//					BeanUtils.copyProperties(fpoScheme, fpoSchememap);
//					fpoSchememap.setCreatedBy(user);
//					fpoSchememap.setCreatedOn(new Date());
//				}
//				 try {
//					 fpoSchememap.setDateOfParticipation(adjustedFormat.parse(fpoScheme.getDateOfParticipation().trim()));
//				 } catch (ParseException e) {
//					 e.printStackTrace();
//				 }
//				 Scheme scheme=schemeRepository.getBySchemeCode(fpoScheme.getSchemee().getSchemeCode());
//				fpoSchememap.setSchemee(scheme);
//				fpoSchememap.setFpoId(savedFpo);
//				fpoSchememap.setActive(true);
//				this.fpoSchemeMapRepository.save(fpoSchememap);
//			});
			
			List<Long> fpoSchemeMapIds = fpoSchemeMapRepository.findByFpoIdFpoIdAndIsActive(savedFpo.getFpoId(), true).stream()
															   .map(FpoSchemeMap::getSchemeMngmntId)
															   .collect(Collectors.toList());
			
			List<Long> existingFpoSchemeMapIds = new ArrayList<>();
			fpoDtls.getFpoSchemeMapVO().forEach(obj->{
				if (obj.getType() == null) {
					// Skip null items and continue to the next iteration
					return;
				}
				if (fpoSchemeMapIds.contains(obj.getSchemeMngmntId())) {
					
					FpoSchemeMap existingFpoSchemeMap = fpoSchemeMapRepository.findById(obj.getSchemeMngmntId()).get();
					if(existingFpoSchemeMap != null) {
						BeanUtils.copyProperties(obj, existingFpoSchemeMap);
						existingFpoSchemeMap.setLastUpdatedBy(user);
						existingFpoSchemeMap.setLastUpdatedOn(new Date());
						try {
							existingFpoSchemeMap.setDateOfParticipation(adjustedFormat.parse(obj.getDateOfParticipation().trim()));
						 } catch (ParseException e) {
							 e.printStackTrace();
						 }
							Scheme scheme=schemeRepository.getBySchemeCode(obj.getSchemee().getSchemeCode());
							existingFpoSchemeMap.setSchemee(scheme);
							existingFpoSchemeMap.setFpoId(savedFpo);
							existingFpoSchemeMap.setActive(true);
							this.fpoSchemeMapRepository.save(existingFpoSchemeMap);
							existingFpoSchemeMapIds.add(obj.getSchemeMngmntId());
					}else {
						FpoSchemeMap fpoSchemeMap = new FpoSchemeMap();
						BeanUtils.copyProperties(obj, fpoSchemeMap);
						fpoSchemeMap.setCreatedBy(user);
						fpoSchemeMap.setCreatedOn(new Date());
						try {
							 fpoSchemeMap.setDateOfParticipation(adjustedFormat.parse(obj.getDateOfParticipation().trim()));
						 } catch (ParseException e) {
							 e.printStackTrace();
						 }
							Scheme scheme=schemeRepository.getBySchemeCode(obj.getSchemee().getSchemeCode());
							fpoSchemeMap.setSchemee(scheme);
							fpoSchemeMap.setFpoId(savedFpo);
							fpoSchemeMap.setActive(true);
							this.fpoSchemeMapRepository.save(fpoSchemeMap);
					}
				}else {
					FpoSchemeMap newFpoSchemeMap = new FpoSchemeMap();
					BeanUtils.copyProperties(obj, newFpoSchemeMap);
					newFpoSchemeMap.setCreatedBy(user);
					newFpoSchemeMap.setCreatedOn(new Date());
					try {
						newFpoSchemeMap.setDateOfParticipation(adjustedFormat.parse(obj.getDateOfParticipation().trim()));
					 } catch (ParseException e) {
						 e.printStackTrace();
					 }
						Scheme scheme=schemeRepository.getBySchemeCode(obj.getSchemee().getSchemeCode());
						newFpoSchemeMap.setSchemee(scheme);
						newFpoSchemeMap.setFpoId(savedFpo);
						newFpoSchemeMap.setActive(true);
						this.fpoSchemeMapRepository.save(newFpoSchemeMap);
				}
			});
			fpoSchemeMapIds.removeAll(existingFpoSchemeMapIds);
			
			fpoSchemeMapIds.forEach(existingId->{
				FpoSchemeMap existingFpoSchemeMap = fpoSchemeMapRepository.findById(existingId).get();
				existingFpoSchemeMap.setActive(false);
				fpoSchemeMapRepository.save(existingFpoSchemeMap);
			});
			
			//List<FpoSchemeMap> saveAllFpoSchemeMap = fpoSchemeMapRepository.saveAll(FpoSchemeMapList);
			
//			List<FarmerDocument> farmerDocList = new ArrayList<>();
//
//			if (Optional.ofNullable(fpoDtls.getFpoDocVo().getIncorporationCertificate()).isPresent()) {
//			    FarmerDocument farmerDoc = new FarmerDocument();
//			    farmerDoc.setDocId(documentRepository.findByDocCode(ApplicationConstants.INCORPORATION));
//			    farmerDoc.setPath(UploadFile.uploadForArticle(null, fpoDtls.getFpoDocVo().getIncorporationCertificate(), YamlAppProperties.getPath(), RandomStringUtils.random(6, true, true).toUpperCase()));
//			    farmerDoc.setCreatedBy(user);
//			    farmerDocList.add(farmerDoc);
//			} if (Optional.ofNullable(fpoDtls.getFpoDocVo().getPan()).isPresent()) {
//			    FarmerDocument farmerDoc = new FarmerDocument();
//			    farmerDoc.setDocId(documentRepository.findByDocCode(ApplicationConstants.PAN));
//			    farmerDoc.setPath(UploadFile.uploadForArticle(null, fpoDtls.getFpoDocVo().getPan(), YamlAppProperties.getPath(), RandomStringUtils.random(6, true, true).toUpperCase()));
//			    farmerDoc.setCreatedBy(user);
//			    farmerDocList.add(farmerDoc);
//			} if (Optional.ofNullable(fpoDtls.getFpoDocVo().getMsmeCertificate()).isPresent()) {
//			    FarmerDocument farmerDoc = new FarmerDocument();
//			    farmerDoc.setDocId(documentRepository.findByDocCode(ApplicationConstants.UDYOG_MSME));
//			    farmerDoc.setPath(UploadFile.uploadForArticle(null, fpoDtls.getFpoDocVo().getMsmeCertificate(), YamlAppProperties.getPath(), RandomStringUtils.random(6, true, true).toUpperCase()));
//			    farmerDoc.setCreatedBy(user);
//			    farmerDocList.add(farmerDoc);
//			} if (Optional.ofNullable(fpoDtls.getFpoDocVo().getOthers()).isPresent()) {
//			    FarmerDocument farmerDoc = new FarmerDocument();
//			    farmerDoc.setDocId(documentRepository.findByDocCode(ApplicationConstants.OTHERS));
//			    farmerDoc.setPath(UploadFile.uploadForArticle(null, fpoDtls.getFpoDocVo().getOthers(), YamlAppProperties.getPath(), RandomStringUtils.random(6, true, true).toUpperCase()));
//			    farmerDocList.add(farmerDoc);
//			}
//
//			if (farmerDocList.size() > 0) {
//			    System.out.println(farmerDocList);
//			    farmerDocList.forEach(doc -> {
//			        doc.setCreatedBy(user);
//			        doc.setCreatedOn(new Date());
//			        doc.setActive(true);
//			        doc.setFpoId(savedFpo);
//			        farmerDocumentRepository.save(doc);
//			    });
//			}

			
			//FPO user creation
			if(fpoDtls.getFpoVo().getStatus().equalsIgnoreCase("SAVE")) {
				
				ServiceOutcome<Role> role = roleService.getRoleByCode(ApplicationConstants.ROLE_FPO);
				Long[] roleId=new Long[] {role.getData().getRoleId()};
				ServiceOutcome<User> newUser = userService.addUser(savedFpo.getFpoCode(),savedFpo.getFpoName(),"",new Date(),"NA" ,"NA",roleId,isPrimary,role.getData().getRoleCode());
				List<RoleLevelMap> rolelevelMapList=roleLevelMapRepository.findByRoleId(roleId[0]);
				ServiceOutcome<String> svcOutcome = accessService.saveConfig(newUser.getData().getUserId(), roleId[0], rolelevelMapList.get(0).getLevelId(), saveFpoDemoMap.getBlockId().getBlockId());
				savedFpo.setUserId(newUser.getData());
				fpoRepository.save(savedFpo);
			}

			soc.setOutcome(true);
			soc.setMessage(fpoDtls.getFpoVo().getFpoId() == null ? "Fpo Register Sucessfull..." : "Fpo Updated Successfull");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in saveFpoDtls() -> FpoServiceImpl"+ e.getMessage());
			soc.setOutcome(false);
			soc.setMessage("Unable to Process, PleaseTry after some time");
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return soc;
	}


	@Override
	public ServiceOutcome<FpoDtls> getFpoByCode(String fpoCode) {
		ServiceOutcome<FpoDtls> soc = new ServiceOutcome<>();
		FpoDtls fpoDtls = new FpoDtls();
		User user = SecurityHelper.getCurrentUser();
		FpoVo fpoVo = new FpoVo();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Optional<Fpo> fpo = fpoRepository.findByFpoCode(fpoCode);
			
			if(fpo.isPresent()) {
				BeanUtils.copyProperties(fpo.get(), fpoVo);

				List<Long> allAdminstrativeId = userAdminstrative.getAllAdminstrativeId(user);
				ServiceOutcome<List<?>> blockNameList = commonService.getAdminstartiveNameByAdminstrativeId(allAdminstrativeId,null, "BLOCK");
				fpoDtls.setBlockList((List<Block>)blockNameList.getData());

				List<FpoSubactivityMap> subActivityList = fpoSubActMapRepository.findByFpoFpoId(fpo.get().getFpoId());
				List<String> subActList = new ArrayList<>();
				subActivityList.forEach(subAct -> {
				    Long id = subAct.getSubActivityId().getId();
				    subActList.add(subAct.getSubActivityId().getSubActCode());
				    System.out.println("SubActivity -"+id);
				});
				fpoVo.setSubActivity(subActList);
				
				for (String subAct : subActList) {
					SubActivity subActivity = subActivityRepository.findBySubActCode(subAct);
					List<MapSubActivity> mapSubActivity = mapSubActivityRepository.findBySubActCodeSubActCode(subActivity.getSubActCode());
					mapSubActivity.forEach(mapSub->{
						String actCode = mapSub.getActCode().getActCode();
						fpoVo.setActivity(actCode);
						System.out.println("Activity -"+actCode);
						
						Activity activity = actRepository.findByActCode(actCode);
						System.out.println(activity.getId());
						MapActivity mapActivity = mapActRepository.findById(activity.getId()).get();
						System.out.println(mapActivity);
						fpoVo.setComponent( mapActivity.getComponent().getCmpCode());
					});
				}
				
				
		        Date dateOfReg = dateTimeFormat.parse(fpo.get().getDateOfReg().toString());
				Date dateOfIncorporation = dateTimeFormat.parse(fpo.get().getDateOfIncorporation().toString());

		        // Extract only the year, month, and day
		        String formattedDateOfReg = dateFormat.format(dateOfReg);
		        fpoVo.setDateOfReg(formattedDateOfReg);
		        String formatDateOfIncorporation = dateFormat.format(dateOfIncorporation);
		        fpoVo.setDateOfIncorporation(formatDateOfIncorporation);
		        
				fpoDtls.setFpoVo(fpoVo);// set FPOVO in FPODTLS
				
				/*FpoManagement Details*/
				List<FpoManagement> fpoManagementList = fpoMngmtRepository.findByFpoIdFpoIdAndIsActive(fpo.get().getFpoId(), true);
				List<FpoManagementVo> mngmntArray = new ArrayList<>();

				fpoManagementList.forEach(mngmntDtls->{
					FpoManagementVo fpomngmnt = new FpoManagementVo();
					BeanUtils.copyProperties(mngmntDtls, fpomngmnt);
					try {
						Date parseAppointmentDate = dateTimeFormat.parse(mngmntDtls.getAppointmentDate().toString());
						fpomngmnt.setAppointmentDate(dateFormat.format(dateOfIncorporation));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					mngmntArray.add(fpomngmnt);
				});
				fpoDtls.setFpoMngmtVo(mngmntArray);
				
//				List<FarmerDocument> farmerDocuments = farmerDocumentRepository.findByFpoIdFpoId(fpo.get().getFpoId());
//				FpoDocumentVO documentVo = new FpoDocumentVO();
//				documentVo.setIncorporationCertificateId(farmerDocumentRepository.findByDocIdDocCodeAndFpoIdFpoId(ApplicationConstants.INCORPORATION, fpo.get().getFpoId()).getFarmerDocId());
//				documentVo.setPanId(farmerDocumentRepository.findByDocIdDocCodeAndFpoIdFpoId(ApplicationConstants.PAN, fpo.get().getFpoId()).getFarmerDocId());
//				documentVo.setMsmeCertificateId(farmerDocumentRepository.findByDocIdDocCodeAndFpoIdFpoId(ApplicationConstants.UDYOG_MSME, fpo.get().getFpoId()).getFarmerDocId());
//				documentVo.setOthersId(farmerDocumentRepository.findByDocIdDocCodeAndFpoIdFpoId(ApplicationConstants.OTHERS, fpo.get().getFpoId()).getFarmerDocId());
//				fpoDtls.setFpoDocVo(documentVo);
				
				List<FpoSchemeMap> existingFpoSchemeMapList = fpoSchemeMapRepository.findByFpoIdFpoIdAndIsActive(fpo.get().getFpoId(), true);
				List<FpoSchemeMapVO> fpoSchemeMapList = new ArrayList<>();
				existingFpoSchemeMapList.forEach(existingFpoScheme->{
					FpoSchemeMapVO fpoSchemeMap = new FpoSchemeMapVO();
					BeanUtils.copyProperties(existingFpoScheme, fpoSchemeMap);
					try {
						Date parseDateOfPart = dateTimeFormat.parse(existingFpoScheme.getDateOfParticipation().toString());
						fpoSchemeMap.setDateOfParticipation(dateFormat.format(parseDateOfPart));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					fpoSchemeMapList.add(fpoSchemeMap);
				});
				fpoDtls.setFpoSchemeMapVO(fpoSchemeMapList);
				
				FpoDemographyMap exiFpoDemoMap = fpoDemographyMapRepository.findByFpoIdFpoId(fpo.get().getFpoId());
				FpoDemographyVO fpoDemograpgyVo = new FpoDemographyVO();
				BeanUtils.copyProperties(exiFpoDemoMap, fpoDemograpgyVo);
				fpoDtls.setFpoDemographyVO(fpoDemograpgyVo);
				
			}
			soc.setData(fpoDtls);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getFpoByCode() -> FpoServiceImpl"+ e.getMessage());
			soc.setOutcome(false);
			soc.setMessage("error");
		}
		return soc;
	}


	@Override
	public ServiceOutcome<FpoDtls> isEventAvailable(User user) {
		ServiceOutcome<FpoDtls> soc = new ServiceOutcome<>();
		FpoDtls fpoDtls = new FpoDtls();
		List<TrainingFpoMap> trainingFpoMapList = new ArrayList<>();
		try {
			
			Optional<Fpo> fpoUser = fpoRepository.findByUserIdUserId(user.getUserId());
			if(fpoUser.isPresent()) {
				List<TrainingFpoMap> findByFpoIdFpoId = trainingFpoMapRepository.findByFpoIdFpoId(fpoUser.get().getFpoId());
				List<Training> trainingList = new ArrayList<>();
				if(findByFpoIdFpoId.size()>0) {
					findByFpoIdFpoId.forEach(fpoId->{
						List<TrainingFpoMap> findByFpoIdFpoId2 = trainingFpoMapRepository.findByFpoIdFpoId(fpoId.getFpoId().getFpoId());
						findByFpoIdFpoId2.forEach(training->{
							Training trainingId = training.getTrainingId();
							trainingList.add(trainingId);
							trainingFpoMapList.add(training);
						});
					});
					 fpoDtls.setTrainingList(trainingList);
					 fpoDtls.setTrainingFpoMap(trainingFpoMapList);
				}
			}
			soc.setData(fpoDtls);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in isEventAvailable() -> FpoServiceImpl"+ e.getMessage());
			soc.setOutcome(false);
			soc.setMessage("error");
		}
		
		return soc;
	}


	@Override
	public ServiceOutcome<List<TrainingFpoMap>> setEventAcceptanceValue(String eventCode, String status) {
		ServiceOutcome<List<TrainingFpoMap>> soc = new ServiceOutcome<>();
		List<TrainingFpoMap> trainingFpoMapList = new ArrayList<TrainingFpoMap>();
		try {
			User user = SecurityHelper.getCurrentUser();
			List<TrainingFpoMap> findByTrainingIdTrainingCode = trainingFpoMapRepository.findByTrainingIdTrainingCode(eventCode);
			findByTrainingIdTrainingCode.forEach(findFpoId->{
				
				if (findFpoId.getFpoId().getUserId() != null) {
				    // Access the user's properties safely here
				    if (findFpoId.getFpoId().getUserId().getUserId().equals(user.getUserId())) {
				    	findFpoId.setTrainingAcceptance(status.equals("ACCEPT") ? true : false);
						TrainingFpoMap saveTrainingFpo = trainingFpoMapRepository.save(findFpoId);
						trainingFpoMapList.add(saveTrainingFpo);
				    }
				} else {
				}
			});
			
			soc.setData(trainingFpoMapList);
			soc.setMessage(status.equals("ACCEPT")?"Event Accepted":"EVENT REJECTED");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in setEventAcceptanceValue() -> FpoServiceImpl"+ e.getMessage());
			soc.setOutcome(false);
			soc.setMessage("Unavailable to progress...");
		}
		return soc;
	}
	
	
   


}