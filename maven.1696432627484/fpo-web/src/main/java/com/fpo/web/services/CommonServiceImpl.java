package com.fpo.web.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fpo.web.entities.*;
import com.fpo.web.repositories.*;
import com.fpo.web.vos.DuplicateCheckDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import com.aashdit.framework.core.LocaleSpecificSorter;
import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.service.AccessService;
import com.aashdit.umt.util.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommonServiceImpl implements CommonService {
	
	@Autowired private BlockRepository blockRepository;
	
	@Autowired private ComponentRepository componentRepository;
	
	@Autowired private DesignationRepository designationRepository;
	
	@Autowired private MapActivityRepository mapActivityRepository;
	
	@Autowired private MapSubActivityRepository mapSubActivityRepository;

	@Autowired private GenderRepository genderRepository;
	
	@Autowired private FpoRepository fpoRepository;

	@Autowired private StateRepository stateRepository;
	
	@Autowired private SchemeRepository schemeRepository;

	@Autowired private BankBranchRepository bankBranchRepository;

	@Autowired private DistrictRepository districtRepository;

	@Autowired private FarmerCbboRepository farmerCbboRepository;
	
	@Autowired private SubjectRepository subjectRepository;
	
	@Autowired private FarmerCbboMngmtRepository cbboMngmntRepository;
	
	@Autowired private TrainingRepository trainingRepository;
	
	@Autowired private MemberRepository memberRepository;
	
	@Autowired private FarmerRepository farmerRepository;
	
	@Autowired
    private GrampanchayatRpository grampanchayatRpository;
	
	@Autowired
    private VillageRepository villageRepository;
	
	@Autowired
	private MunicipalityRepository municipalityRepository;
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CasteRepository casteRepository;


	
	@Override /* For Only State, Dist, Block/Municipality/Municipality Corporation, Gp, Village/Ward */
	public ServiceOutcome<List<?>> getAllData(String identity){
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
			switch(identity) {
			case "STATE":
				List<State> states = stateRepository.findAllByIsActive(true);
				soc.setData(states);
				break;
			case "DISTRICT":
				List<District> districts = districtRepository.findAllByIsActive(true);
				soc.setData(districts);
				break;
			case "BLOCK":
				List<Block> blocks = blockRepository.findAllByIsActiveTrue();
				soc.setData(blocks);
				break;
			case "BANKBRANCH":
				List<String> bankBranchList = bankBranchRepository.getBankList(true);
				soc.setData(bankBranchList);
				break;
			case "COMPONENT":
				List<Component> componentList = componentRepository.findAllByIsActiveTrue();
				soc.setData(componentList);
				break;
			case "DESIGNATION":
				List<Designation> designationList = designationRepository.findAllByIsActiveTrue();
				soc.setData(designationList);
				break;
			case "GENDER":
				List<Gender> genderList = genderRepository.findAllByIsActiveTrue();
				soc.setData(genderList);
				break;
			case "FPO":
				List<Fpo> fpos = fpoRepository.findAllByIsActiveTrue();
				soc.setData(fpos);
				break;
			case "SCHEME":
				List<Scheme> schemes = schemeRepository.findByIsActiveTrueOrderBySchemeNameEn();
				soc.setData(schemes);
				break;
			case "CBBO":
//				List<FarmerCbbo> cbboList =  farmerCbboRepository.findAllByIsActive(true);
//				soc.setData(cbboList);
				break;
			case "SUBJECT":
				List<Subject> subjectList = subjectRepository.findAllByIsActive(true);
				soc.setData(subjectList);
				break;
			case "CBBO_MNGMNT":
//				List<FarmerCbboMngmt> farmercbboMngmtList = cbboMngmntRepository.findByIsActiveTrueAndIsTrainer(true);
//				soc.setData(farmercbboMngmtList);
				break;
			case "TRAINING_LIST":
				List<Training> trainingList = trainingRepository.findAllByIsActive(true);
				soc.setData(trainingList);
				break;
			case "CASTE":
				List<Caste> casteList = casteRepository.findAllByIsActive(true);
				soc.setData(casteList);
				break;
			case "MEMBER":
				List<Member> findAllByIsActive = memberRepository.findAllByIsActive(true);
				soc.setData(findAllByIsActive);
				break;
			  default:
			    // code block
			}
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in getAllData("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}
	
	
	
	@Override
	public <T> ServiceOutcome<T> getDataByAdministrativeid(Long adminstrativeId, String string) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ServiceOutcome<District> getDistrictByAdminstrativeId(Long adminstrativeId) {
		ServiceOutcome<District> soc = new ServiceOutcome<>();
		try {
			Block findByBlockId = blockRepository.findByBlockId(adminstrativeId);
			soc.setData(findByBlockId.getDistrict());
		} catch (Exception e) {
			log.error("Exception occurred in getDistrictByAdminstrativeId() -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}



	@Override
	public ServiceOutcome<List<?>> getAllAjaxCallDetails(String identity, String id) {
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
		switch(identity) {
			 case "District":
				List<District> lstDistricts = districtRepository.findAllByStateStateIdAndIsActive(Long.parseLong(id), true);
				 soc.setData(lstDistricts);
				 break;
			 case "Block":
				 List<Block> blkList=blockRepository.findAllByDistrictId(Long.parseLong(id), true);
				 soc.setData(blkList);
				 break;
			 case "GP":
					List<Grampanchayat> findAllByBlockBlockIdAndIsActive = grampanchayatRpository.findAllByBlockBlockIdAndIsActive(Long.parseLong(id), true);
					soc.setData(findAllByBlockBlockIdAndIsActive);
					 break;
			 case "VILLAGE":
				 List<Village> findAllByGpIdGpIdAndIsActive = villageRepository.findAllByGpIdGpIdAndIsActive(Long.parseLong(id), true);
				 soc.setData(findAllByGpIdGpIdAndIsActive);
				 break;

			 case "BANKBRANCH":
				List<BankBranch> bankBranch = bankBranchRepository.findAllByBankNameAndIsActiveTrue(id);
				soc.setData(bankBranch);
				break;
			case "BANKBRANCHIFSC":
//				String bankName=id.split("||")[0];
//				String branchName=id.split("||")[1];
				List<BankBranch> bankBranchIfsc = bankBranchRepository.findAllBybankBranchIdAndIsActiveTrue(Long.parseLong(id));
				soc.setData(bankBranchIfsc);
				break;
			  case "Activity":
				  
				  List<MapActivity> activityList = mapActivityRepository.findByComponentCmpCodeAndIsActiveTrue(id);
				  soc.setData(activityList);
			    break;
			  case "SubActivity":
//				  ids.forEach(activityId->{
//					  System.out.println(activityId);
				  String[] splitId = id.split(",");
				  List<MapSubActivity> subActivityList = Arrays.stream(splitId)
										          .flatMap(singleId -> mapSubActivityRepository.findAllByActCodeActCode(singleId).stream())
										          .collect(Collectors.toList());

				  soc.setData(subActivityList);
			  case "FRMBYVILLAGE":
				  List<Farmer> findByVillageIdVillageIdAndIsActive = farmerRepository.findByVillageIdVillageIdAndIsActive(Long.parseLong(id), true);
				  soc.setData(findByVillageIdVillageIdAndIsActive);

//				  });
			    break;
			  case "MEMBER":
				  List<Member> memberDetailsByAadharNo = memberRepository.getMemberDetailsByAadharNo(id);
				  System.out.println(memberDetailsByAadharNo);
				  soc.setData(memberDetailsByAadharNo);

			    break;
			    
			  default:
			    // code block
			}
			 soc.setMessage("success");
			  soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in getAllAjaxCallDetails("+identity+") -> CommonServiceImpl" + e.getMessage());
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<List<District>> findByAllDistrict() {
		ServiceOutcome<List<District>> svcOutcome = new ServiceOutcome<>();
		try {
			List<District> lstDistricts = districtRepository.findAllByOrderByDistrictNameEN();
			svcOutcome.setData(lstDistricts);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return svcOutcome;
	}
	
	@Override
	public ServiceOutcome<District> addNupdateDistrict(District district) {
		ServiceOutcome<District> svcOutcome = new ServiceOutcome<>();
		User user = SecurityHelper.getCurrentUser();
		try {
			if (district.getDistrictId() != null) {
				District prvDistrict = districtRepository.findById(district.getDistrictId()).get();
				prvDistrict.setDistrictCode(district.getDistrictCode());
				prvDistrict.setDistrictNameEN(district.getDistrictNameEN());
				prvDistrict.setDistrictNameHI(district.getDistrictNameHI());
				prvDistrict.setState(stateRepository.findById(district.getState().getStateId()).get());
				prvDistrict.setDistrictLgdCode(district.getDistrictLgdCode());
				prvDistrict.setDistrictCensusCode(district.getDistrictCensusCode());
				prvDistrict.setDistrictTribal(district.getDistrictTribal());
				prvDistrict.setIsActive(district.getIsActive());
				prvDistrict.setLastUpdatedOn(new Date());
				prvDistrict.setLastUpdatedBy(user);
				districtRepository.save(prvDistrict);
				svcOutcome.setMessage(messageSource.getMessage("msg.success.districtUpdt", null, LocaleContextHolder.getLocale()));
			} else {
				district.setCreatedBy(user);
				district.setCreatedOn(new Date());
				districtRepository.save(district);
				svcOutcome.setMessage(messageSource.getMessage("msg.success.district", null, LocaleContextHolder.getLocale()));
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<District> getDistrictByDistrictId(Long districtId) {
		ServiceOutcome<District> svcOutcome = new ServiceOutcome<>();

		try {
			District districts = districtRepository.findById(districtId).get();
			svcOutcome.setData(districts);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}
	
	@Override
	public ServiceOutcome<List<State>> findAllState(boolean isActive) {
	ServiceOutcome<List<State>> svcOutcome = new ServiceOutcome<>();
		try
		{
			List<State> lstStates = stateRepository.findAllByIsActive(isActive);
			lstStates = new LocaleSpecificSorter<State>(State.class).sort(lstStates);
			svcOutcome.setData(lstStates);
			svcOutcome.setOutcome(true);
			svcOutcome.setMessage("SUCCESS");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage());
			
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		
		return svcOutcome;
	}

	 @Override
	    public ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyCode(String code, Long id, String type) {

	        ServiceOutcome<DuplicateCheckDto> svcOutcome = new ServiceOutcome<DuplicateCheckDto>();
	        try {
	            Optional<Long> idData = Optional.ofNullable(id);
	            Long idVal = 0L;
	            if (idData.isPresent()) {
	                idVal = id;
	            }
	            if (type.equalsIgnoreCase("District")) {
	                District district = districtRepository.findByDistrictCode(code);
	                if (district != null && !idVal.equals(district.getDistrictId())) {
	                    DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
	                    duplicateCheckDto.setIsDuplicate("true");
	                    svcOutcome.setData(duplicateCheckDto);
	                } else {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("false");
						svcOutcome.setData(duplicateCheckDto);
					}
				}
				if(type.equalsIgnoreCase("Block")){
					Block block = blockRepository.findByBlockCode(code);
					if(block != null && !idVal.equals(block.getBlockId())) {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("true");
						svcOutcome.setData(duplicateCheckDto);
					}
					else {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("false");
						svcOutcome.setData(duplicateCheckDto);
					}
				}
				if(type.equalsIgnoreCase("Grampanchayat")){
					Grampanchayat gp = grampanchayatRpository.findByGpCode(code);
					if(gp != null && !idVal.equals(gp.getGpId())) {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("true");
						svcOutcome.setData(duplicateCheckDto);
					}
					else {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("false");
						svcOutcome.setData(duplicateCheckDto);
					}
				}
				if(type.equalsIgnoreCase("Village")){
					Village village = villageRepository.findByVillageCode(code);
					if(village != null && !idVal.equals(village.getVillageId())) {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("true");
						svcOutcome.setData(duplicateCheckDto);
					}
					else {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("false");
						svcOutcome.setData(duplicateCheckDto);
					}
				}
				/*if(type.equalsIgnoreCase("SHG")){
					ShgProfileDetails shgProfileDetails = shgProfileDetailsRepository.findByShgCode(code);
					if(shgProfileDetails != null && !idVal.equals(shgProfileDetails.getShgDetailsId())) {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("true");
						svcOutcome.setData(duplicateCheckDto);
					}
					else {
						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
						duplicateCheckDto.setIsDuplicate("false");
						svcOutcome.setData(duplicateCheckDto);
					}
				}*/
			}
			catch(Exception ex)
			{
				log.error(ex.getMessage());
				
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
				
			}
			return svcOutcome;
			
		}
	 
	 @Override
		public ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyName(String name, Long id, String type) {

			ServiceOutcome<DuplicateCheckDto> svcOutcome = new ServiceOutcome<DuplicateCheckDto>();
//			try
//			{
//				Optional<Long> idData = Optional.ofNullable(id);
//				Long idVal = 0L;
//				if(idData.isPresent()) {
//					idVal = id;
//				}
//				if(type.equalsIgnoreCase("District")){
//					District district = districtRepository.checkUniqueDistrictNameEn(name);
//					if(district != null && !idVal.equals(district.getDistrictId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}
//				if(type.equalsIgnoreCase("Block")){
//					Block block = blockRepository.findByBlockNameEn(name);
//					if(block != null && !idVal.equals(block.getBlockId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}
//				if(type.equalsIgnoreCase("Grampanchayat")){
//					Gramapanchayat gp = grampanchayatRpository.findByGpNameEn(name);
//					if(gp != null && !idVal.equals(gp.getGpId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}
//				if(type.equalsIgnoreCase("Village")){
//					Village village = villageRepository.findByVillageNameEn(name);
//					if(village != null && !idVal.equals(village.getVillageId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}
	//
//				/*if(type.equalsIgnoreCase("SHG")){
//					ShgProfileDetails shgProfileDetails = shgProfileDetailsRepository.findByShgName(name);
//					if(shgProfileDetails != null && !idVal.equals(shgProfileDetails.getShgDetailsId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}
//				if(type.equalsIgnoreCase("SHGReg")){
//					ShgProfileDetails shgProfileDetails = shgProfileDetailsRepository.findByShgRegNumber(name);
//					if(shgProfileDetails != null && !idVal.equals(shgProfileDetails.getShgDetailsId())) {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("true");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//					else {
//						DuplicateCheckDto duplicateCheckDto = new DuplicateCheckDto();
//						duplicateCheckDto.setIsDuplicate("false");
//						svcOutcome.setData(duplicateCheckDto);
//					}
//				}*/
//			}
//			catch(Exception ex)
//			{
//				log.error(ex);
	//
//				svcOutcome.setData(null);
//				svcOutcome.setOutcome(false);
//				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
	//
//			}
			return svcOutcome;
		}
	 
	 @Override
		public ServiceOutcome<List<Block>> getAllBlock() {
			ServiceOutcome<List<Block>> svcOutcome = new ServiceOutcome<>();
//			Boolean data = false;
//			String msg = "msg.error";
			List<Block> lstBlocks = new ArrayList<>();
			try {
				lstBlocks = blockRepository.findAllBlock();
//				lstBlocks = new LocaleSpecificSorter<Block>(Block.class).sort(lstBlocks);
//				data = true;
//				msg = "msg.success";
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
			svcOutcome.setData(lstBlocks);
//			svcOutcome.setOutcome(data);
//			svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
			return svcOutcome;
		}

	 @Override
		public ServiceOutcome<Boolean> addBlock(Block block) {
			ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<>();
			User user = SecurityHelper.getCurrentUser();
			Boolean data = false;
			String msg = "msg.block.failure";
			try {
				if (block.getBlockId() != null) {
					Block prvblock = blockRepository.findById(block.getBlockId()).get();
					prvblock.setBlockCode(block.getBlockCode());
					prvblock.setBlockNameEN(block.getBlockNameEN());
					prvblock.setBlockNameHI(block.getBlockNameEN());
					prvblock.setBlockLgdCode(block.getBlockLgdCode());
					prvblock.setBlockCensusCode(block.getBlockCensusCode());
					prvblock.setBlockTribal(block.getBlockTribal());
					prvblock.setIsActive(block.getIsActive());
					prvblock.setLastUpdatedOn(new Date());
					prvblock.setLastUpdatedBy(user);
					prvblock.setDistrict(districtRepository.findById(block.getDistrict().getDistrictId()).get());
					blockRepository.save(prvblock);
					data = true;
					msg = "msg.success.blockUpdt";
				} else {
					block.setCreatedBy(user);
					block.setCreatedOn(new Date());
					block.setBlockNameHI(block.getBlockNameEN());
					blockRepository.save(block);
					
					data = true;
					msg = "msg.success.block";
				}
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
			svcOutcome.setData(data);
			svcOutcome.setOutcome(data);
			svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
			return svcOutcome;
		}

	 @Override
		public ServiceOutcome<Block> editBlock(Long blockId) {
			ServiceOutcome<Block> svcOutcome = new ServiceOutcome<>();
			Block block = null;
			Boolean data = false;
			String msg = "msg.error";
			try {
				block = blockRepository.findById(blockId).get();
				data = true;
				msg = "msg.success";
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
			svcOutcome.setData(block);
			svcOutcome.setOutcome(data);
			svcOutcome.setMessage(messageSource.getMessage(msg, null, LocaleContextHolder.getLocale()));
			return svcOutcome;
		}
	 
	 @Override
		public ServiceOutcome<List<Municipality>> getAllMunicipality() {
			ServiceOutcome<List<Municipality>> svcOutcome = new ServiceOutcome<>();
			try
			{
				List<Municipality> lstMunicipality = municipalityRepository.findAll();
				lstMunicipality = new LocaleSpecificSorter<Municipality>(Municipality.class).sort(lstMunicipality);
				
				svcOutcome.setData(lstMunicipality);
			}
			catch(Exception ex)
			{
				log.error(ex.getMessage());
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}
			
			return svcOutcome;
		}

		@Override
		public ServiceOutcome<Municipality> addNupdateMunicipality(Municipality municipality) {
			  ServiceOutcome<Municipality> svcOutcome = new ServiceOutcome<>();
				User user=SecurityHelper.getCurrentUser();
				try
				{
				if(municipality.getMunicipalityId()!=null) {
					Municipality prvmunicipality=municipalityRepository.findById(municipality.getMunicipalityId()).get();
					prvmunicipality.setMunicipalityCode(municipality.getMunicipalityCode());
					prvmunicipality.setMunicipalityNameEn(municipality.getMunicipalityNameEn());
					prvmunicipality.setMunicipalityNameHi(municipality.getMunicipalityNameHi());
					prvmunicipality.setMuniLgdCode(municipality.getMuniLgdCode());
					prvmunicipality.setMuniCensusCode(municipality.getMuniCensusCode());
					prvmunicipality.setMuniTribal(municipality.getMuniTribal());
					prvmunicipality.setIsActive(municipality.getIsActive());
					prvmunicipality.setLastUpdatedOn(new Date());
					prvmunicipality.setLastUpdatedBy(user);
					prvmunicipality.setDistrict(districtRepository.findById(municipality.getDistrict().getDistrictId()).get());
					municipalityRepository.save(prvmunicipality);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.muniUpdt", null, LocaleContextHolder.getLocale()));
				}else {
					municipality.setCreatedBy(user);
					municipality.setCreatedOn(new Date());
					municipalityRepository.save(municipality);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.muni", null, LocaleContextHolder.getLocale()));
				}
					
				}
				catch(Exception ex)
				{
					log.error(ex.getMessage());
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage(messageSource.getMessage("msg.bloMunicipalityck.failure", null, LocaleContextHolder.getLocale()));
				}
				
				return svcOutcome;
			}

		@Override
		public ServiceOutcome<Municipality> getMuncipalityByMuncipalityId(Long muncipalityId) {
			  ServiceOutcome<Municipality> svcOutcome = new ServiceOutcome<>();
				try
				{
					Municipality municipality =municipalityRepository.findById(muncipalityId).get();
					svcOutcome.setData(municipality);
				}
				catch(Exception ex)
				{
					log.error(ex.getMessage());
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
				}
				
				return svcOutcome;
			}
	 
		@Override
		public ServiceOutcome<List<Grampanchayat>> getAllGrampanchayats() {
			ServiceOutcome<List<Grampanchayat>> svcOutcome = new ServiceOutcome<>();

			try {
				List<Grampanchayat> lstGrampanchayats = grampanchayatRpository.findAllGp();
//				lstGrampanchayats = new LocaleSpecificSorter<Grampanchayat>(Grampanchayat.class).sort(lstGrampanchayats);

				svcOutcome.setData(lstGrampanchayats);
			} catch (Exception ex) {
				log.error(ex.getMessage());

//				svcOutcome.setData(null);
//				svcOutcome.setOutcome(false);
//				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<Grampanchayat> addNupdateGrampanchayat(Grampanchayat grampanchayat) {
			ServiceOutcome<Grampanchayat> svcOutcome = new ServiceOutcome<>();
			User user = SecurityHelper.getCurrentUser();
			try {
				if (grampanchayat.getGpId() != null) {
					Grampanchayat prvgrampanchayat = grampanchayatRpository.findById(grampanchayat.getGpId()).get();
					prvgrampanchayat.setGpCode(grampanchayat.getGpCode());
					prvgrampanchayat.setGpNameHI(grampanchayat.getGpNameEN());
					prvgrampanchayat.setGpNameEN(grampanchayat.getGpNameEN());
//					prvgrampanchayat.setGpLgdCode(grampanchayat.getGpLgdCode());
//					prvgrampanchayat.setGpCensusCode(grampanchayat.getGpCensusCode());
//					prvgrampanchayat.setGpTribal(grampanchayat.getGpTribal());
					prvgrampanchayat.setIsActive(grampanchayat.getIsActive());
					prvgrampanchayat.setLastUpdatedOn(new Date());
					prvgrampanchayat.setLastUpdatedBy(user);
					prvgrampanchayat.setBlock(blockRepository.findById(grampanchayat.getBlock().getBlockId()).get());
					grampanchayatRpository.save(prvgrampanchayat);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.gpUpdt", null, LocaleContextHolder.getLocale()));
				} else {
					grampanchayat.setCreatedBy(user);
					grampanchayat.setCreatedOn(new Date());
					grampanchayatRpository.save(grampanchayat);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.gp", null, LocaleContextHolder.getLocale()));
				}

			} catch (Exception ex) {
				log.error(ex.getMessage());
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.gp.failure", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}

		@Override
		public ServiceOutcome<Grampanchayat> getGrampanchayatByGpId(Long gpId) {
			ServiceOutcome<Grampanchayat> gpServiceOutcome = new ServiceOutcome<>();
			try {
				Grampanchayat gpData = grampanchayatRpository.findById(gpId).get();
				gpServiceOutcome.setData(gpData);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				gpServiceOutcome.setData(null);
				gpServiceOutcome.setOutcome(false);
				gpServiceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return gpServiceOutcome;
		}
		

		@Override
		public ServiceOutcome<List<District>> getDistricts(Boolean isActive) {
			ServiceOutcome<List<District>> svcOutcome = new ServiceOutcome<>();

			try {
				List<District> lstDistricts = districtRepository.findAll(isActive);
				lstDistricts = new LocaleSpecificSorter<District>(District.class).sort(lstDistricts);

				svcOutcome.setData(lstDistricts);
			} catch (Exception ex) {
				log.error(ex.getMessage());

				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public List<Village> findAllVillage() {
//			ServiceOutcome<List<Village>> svcOutcome = new ServiceOutcome<>();
			List<Village> lstVillages=null;
			try {
				lstVillages = villageRepository.findAllVillage();
//				lstVillages = new LocaleSpecificSorter<Village>(Village.class).sort(lstVillages);
//				svcOutcome.setData(lstVillages);
			} catch (Exception ex) {
				log.error(ex.getMessage());
//				svcOutcome.setData(null);
//				svcOutcome.setOutcome(false);
//				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return lstVillages;
		}

		@Override
		public ServiceOutcome<Village> addNupdateVillage(Village village) {
			 ServiceOutcome<Village> svcOutcome = new ServiceOutcome<>();
				User user=SecurityHelper.getCurrentUser();
				try
				{
				if(village.getVillageId()!=null) {
					Village prvVillage=villageRepository.findById(village.getVillageId()).get();
					prvVillage.setVillageCode(village.getVillageCode());
					prvVillage.setVillageNameEn(village.getVillageNameEn());
					prvVillage.setVillageNameHi(village.getVillageNameEn());
//					prvVillage.setVillageLgdCode(village.getVillageLgdCode());
//					prvVillage.setVillageCensusCode(village.getVillageCensusCode());
//					prvVillage.setVillageTribal(village.getVillageTribal());
					prvVillage.setIsActive(village.getIsActive());
					prvVillage.setLastUpdatedBy(user);
					prvVillage.setLastUpdatedOn(new Date());
					prvVillage.setGpId(grampanchayatRpository.findById(village.getGpId().getGpId()).get());
					villageRepository.save(prvVillage);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.vlgUpdt", null, LocaleContextHolder.getLocale()));
				}else {
					village.setCreatedBy(user);
					village.setCreatedOn(new Date());
					villageRepository.save(village);
					svcOutcome.setMessage(messageSource.getMessage("msg.success.vlg", null, LocaleContextHolder.getLocale()));
				}
				}
				catch(Exception ex)
				{
					log.error(ex.getMessage());
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage(messageSource.getMessage("msg.village.failure", null, LocaleContextHolder.getLocale()));
				}
				
				return svcOutcome;
			}

		@Override
		public ServiceOutcome<Village> getVillageByVillageId(Long villageId) {
			   ServiceOutcome<Village> villOutcome=new ServiceOutcome<>();
				try {
					Village villageData = villageRepository.findById(villageId).get();
					villOutcome.setData(villageData);
				} catch (Exception ex) {
					log.error(ex.getMessage());
					villOutcome.setData(null);
					villOutcome.setOutcome(false);
					villOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
				}

				return villOutcome;
			}

		@Override
		public ServiceOutcome<List<District>> getAllActiveDistrictByStateId(Long stateId,Boolean byPassCheck) {
			ServiceOutcome<List<District>> svcOutcome = new ServiceOutcome<>();
			List<District> lstDistricts = new ArrayList<District>();
			try {
				User user=SecurityHelper.getCurrentUser();
				if(Optional.ofNullable(user).isPresent()) {
					 lstDistricts = districtRepository.findAllByStateId(stateId, true);
				}
			
				lstDistricts = new LocaleSpecificSorter<District>(District.class).sort(lstDistricts);
				svcOutcome.setData(lstDistricts);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage("SUCCESS");
			} catch (Exception ex) {
				log.error(ex.getMessage());

				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<List<Block>> getAllActiveBlockByDistrictId(Long districtId,Boolean byPassCheck) {
			ServiceOutcome<List<Block>> svcOutcome = new ServiceOutcome<>();

			try {
				List<Block> lstBlocks = blockRepository.findAllByDistrictId(districtId, true);
				lstBlocks = new LocaleSpecificSorter<Block>(Block.class).sort(lstBlocks);
				svcOutcome.setData(lstBlocks);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage("SUCCESS");
			} catch (Exception ex) {
				log.error(ex.getMessage());

				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<List<Grampanchayat>> getAllActiveGpByBlockId(Long blockId,Boolean byPassCheck) {
			ServiceOutcome<List<Grampanchayat>> svcOutcome = new ServiceOutcome<>();

			try {
				List<Grampanchayat> lstGrampanchayats = grampanchayatRpository.findAllByBlockId(blockId, true);
				lstGrampanchayats = new LocaleSpecificSorter<Grampanchayat>(Grampanchayat.class).sort(lstGrampanchayats);
				svcOutcome.setData(lstGrampanchayats);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage("SUCCESS");
			} catch (Exception ex) {
				log.error(ex.getMessage());

				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<List<Village>> getAllActiveVillageByGpId(Long gpId,Boolean byPassCheck) {
			ServiceOutcome<List<Village>> svcOutcome = new ServiceOutcome<>();

			try {
				List<Village> lstVillages = villageRepository.findAllByBlockId(gpId,true);
				lstVillages = new LocaleSpecificSorter<Village>(Village.class).sort(lstVillages);
				svcOutcome.setData(lstVillages);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage("SUCCESS");
			} catch (Exception ex) {
				log.error(ex.getMessage());
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}

			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<List<Municipality>> getMunicipalityByDistrictId(Long districtId,Boolean byPassCheck) {
			  ServiceOutcome<List<Municipality>> svcOutcome = new ServiceOutcome<>();
			  List<Municipality> municipality = new ArrayList<Municipality>();
				try
				{
					User user=SecurityHelper.getCurrentUser();
					if(Optional.ofNullable(user).isPresent()) {
				if(!byPassCheck) {
					if(user.getPrimaryRole().getRoleCode().equals(messageSource.getMessage("role.check.for.pda", null, LocaleContextHolder.getLocale())) || user.getPrimaryRole().getRoleCode().equals(messageSource.getMessage("role.check.for.govt", null, LocaleContextHolder.getLocale()))) {
						ServiceOutcome<List<Municipality>> svcOutcomeUlbs = accessService.getByRoleLevel(user.getUserId(), user.getPrimaryRole().getRoleId(), "ULB", Municipality.class);
						municipality =	svcOutcomeUlbs.getData().stream().filter(data->data.getDistrict().getDistrictId().equals(districtId)).collect(Collectors.toList());
				}else {
					 municipality =municipalityRepository.findMunicipalityByDistrictId(districtId,true);
				}
			}else {
						 municipality =municipalityRepository.findMunicipalityByDistrictId(districtId,true);
				}
					}else {
						 municipality =municipalityRepository.findMunicipalityByDistrictId(districtId,true);
				}
					
					svcOutcome.setData(municipality);
					svcOutcome.setOutcome(true);
					svcOutcome.setMessage("SUCCESS");
				}
				catch(Exception ex)
				{
					log.error(ex.getMessage());
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
				}
				
				return svcOutcome;
			}
		
		@Override
		public ServiceOutcome<List<Ward>> getWardListByUlb(Long ulbId, Boolean byPassCheck) {
			
			ServiceOutcome<List<Ward>> svcOutcome= new ServiceOutcome<List<Ward>>();
			List<Ward> wardList= new ArrayList<>();
			try {
				wardList=wardRepository.findAllByMunicipalityMunicipalityIdAndIsActiveTrue(ulbId);
			
				svcOutcome.setData(wardList);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} catch (Exception e) {
				
				svcOutcome.setData(wardList);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}
			return svcOutcome;
		}
		
		@Override
		public ServiceOutcome<List<String>> getBankList() {
			ServiceOutcome<List<String>> svcOutcome = new ServiceOutcome<>();
			try {
				List<String> bankBranch = bankBranchRepository.getBankList(true);
				svcOutcome.setData(bankBranch);
			}catch (Exception ex) {
				log.error(ex.getMessage());

				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}
			
			return svcOutcome;

		}

		@Override
		public ServiceOutcome<List<BankBranch>> findByBankName(String bankName) {
			ServiceOutcome<List<BankBranch>> svcOutcome = new ServiceOutcome<List<BankBranch>>();
			try {
				List<BankBranch> bankBranch = bankBranchRepository.findAllByBankNameAndIsActiveTrueOrderByBranchName(bankName);
				svcOutcome.setData(bankBranch);
		}catch (Exception ex) {
			log.error(ex.getMessage());

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		
		return svcOutcome;
		}

		@Override
		public ServiceOutcome<List<BankBranch>>  findIfscByBankNameBranchName(String bankName, String branchName) {
			ServiceOutcome<List<BankBranch>> svcOutcome = new ServiceOutcome<>();
			try {
				List<BankBranch> bankBranch = bankBranchRepository.findAllByBankNameAndBranchName(bankName , branchName);
				svcOutcome.setData(bankBranch);
		}catch (Exception ex) {
			log.error(ex.getMessage());

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		
		return svcOutcome;
		}
		
		@Override
		  public String getDocument(String downloadCode,Long docId) {
			return downloadCode; 
//			  String doc=""; 
//			  try { 
//				  switch (downloadCode) {
//				  	case "FUND_RECEIVED": 
//				  		FundReceived fnd= fundReceivedRepository.findById(docId).get();
//				  		if(Optional.ofNullable(fnd).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+fnd.getFundRcvCode()+File.separator+fnd.getSupportingDocument();
//				  		}
//				  	break;
//				  	case "EXPENDITURE_DETAILS1":
//				  		ExpenditureDetail exp1= expenditureDetailRepository.findById(docId).get();
//				  		if(Optional.ofNullable(exp1).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+exp1.getExpDtlsCode()+File.separator+exp1.getExpSupportDoc();
//				  		}
//				  	break;
//				  	case "EXPENDITURE_DETAILS2":
//				  		ExpenditureDetail exp2= expenditureDetailRepository.findById(docId).get();
//				  		if(Optional.ofNullable(exp2).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+exp2.getExpDtlsCode()+File.separator+exp2.getExpSupportDoc2();
//				  		}
//				  	break;
//				  	case "EXPENDITURE_DETAILS3":
//				  		ExpenditureDetail exp3= expenditureDetailRepository.findById(docId).get();
//				  		if(Optional.ofNullable(exp3).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+exp3.getExpDtlsCode()+File.separator+exp3.getExpSupportDoc3();
//				  		}
//				  	break; 
//				  	case "LETTER_RECEIVED":
//				  		LetterReceived letterDoc= letterReceivedRepository.findById(docId).get();
//				  		if(Optional.ofNullable(letterDoc).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+letterDoc.getLetterRcvCode()+File.separator+letterDoc.getSupportingDocument();
//				  		}
//				  	break;
//				  	case "UC_SUBMISSION":
//				  		UcSubmission ucDoc= ucSubmissionRepository.findById(docId).get();
//				  		if(Optional.ofNullable(ucDoc).isPresent()) {
//				  			doc=yaml.getPath()+File.separator+ucDoc.getUcSbmtCode()+File.separator+ucDoc.getUcDoc();
//				  		}
//				  	break;
//				  }
//				  
//		  } catch (Exception e) { 
//			  log.error("message", e); 
//			  } 
//			  return doc; 
//		  }
		}
		
		@Override
		public ServiceOutcome<String> inActNActDemgry(String demoCall, Long id,Boolean status) {
			ServiceOutcome<String> srvc = new ServiceOutcome<>();
			try {
				switch (demoCall) {
				case "DIST":
					District dist = districtRepository.findById(id).get();
					if(dist != null) {
						dist.setIsActive(status);
						districtRepository.save(dist);
						srvc.setMessage(messageSource.getMessage(status.equals(false) ? "msg.success.inActDist" : "msg.success.actDist", null, LocaleContextHolder.getLocale()));
					}
					break;

				case "BLOCK":
					Block blk = blockRepository.findById(id).get();
					if(blk != null) {
						blk.setIsActive(status);
						blockRepository.save(blk);
						srvc.setMessage(messageSource.getMessage(status.equals(false) ? "msg.success.inActBlk" : "msg.success.actBlk", null, LocaleContextHolder.getLocale()));
					}
					break;
					
				case "GP":
					Grampanchayat gp = grampanchayatRpository.findById(id).get();
					if(gp != null) {
						gp.setIsActive(status);
						grampanchayatRpository.save(gp);
						srvc.setMessage(messageSource.getMessage(status.equals(false) ? "msg.success.inActGp" : "msg.success.actGp", null, LocaleContextHolder.getLocale()));
					}
					break;
					
				case "VLG":
					Village vlg = villageRepository.findById(id).get();
					if(vlg != null) {
						if(status.equals(true)) {
							vlg.setIsActive(false);
						}else {
							vlg.setIsActive(true);
						}
						villageRepository.save(vlg);
						srvc.setMessage(messageSource.getMessage(status.equals(false) ? "msg.success.actVlg" : "msg.success.inActVlg", null, LocaleContextHolder.getLocale()));
					}
					break;
				default:
					break;
				}
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return srvc;
		}
		
		@Override
		public List<Village> findAllFilterVillageList(Long districtId, Long blockId, Long gpId, Long villageId) {
			List<Village> list = new ArrayList<>();
			try {
				list = villageRepository.findAllFilterList(districtId,blockId,gpId,villageId);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return list;
		}
		
		 @Override
		    public List<Activity> getActivityList(String cmpCode) {
		        List<Activity> activityList = new ArrayList<>();
		        try {
		            List<MapActivity> mapList = mapActivityRepository.findByComponentCmpCodeAndIsActiveTrue(cmpCode);
		            if (mapList != null && mapList.size() > 0) {
		                activityList = mapList.stream().filter(d -> d.getActCode().getIsActive() == true).map(data -> data.getActCode()).collect(Collectors.toList());
		            }
		            return activityList;

		        } catch (Exception e) {
		            log.error("Error in getActivityList", e);
		            return activityList;
		        }
		    }
		 
		 @Override
		    public List<SubActivity> getSubActivityList(String actCode) {
		        List<SubActivity> subActivityList = new ArrayList<>();
		        try{
		            List<MapSubActivity>   mapList=mapSubActivityRepository.findByActCodeActCodeAndIsActiveTrue(actCode);
		            if(mapList!=null && mapList.size()>0){
		                subActivityList=mapList.stream().filter(d->d.getSubActCode().getIsActive() == true).map(data->data.getSubActCode()).collect(Collectors.toList());
		            }
		            return subActivityList;

		        }catch (Exception e){
		            log.error("Error in getSubActivityList", e);
		            return subActivityList;
		        }
		    }

		 @Override
		public ServiceOutcome<List<?>> getAdminstartiveNameByAdminstrativeId(List<Long> allAdminstrativeId, Long adminstrativeId,
				String demographyName) {
			ServiceOutcome<List<?>> soc = new ServiceOutcome<>();
			List<Block> blockList = new ArrayList<>();
			try {
				
				switch(demographyName) {
				case "BLOCK":
					allAdminstrativeId.forEach(blockId->{
						Block findByBlockId = blockRepository.findByBlockId(blockId);
						if (findByBlockId != null) {
							blockList.add(findByBlockId);
							soc.setData(blockList);
	                    }
					});
					break;
				case "GP":
					List<Grampanchayat> findByBlockBlockIdAndIsActive = grampanchayatRpository.findByBlockBlockIdAndIsActive(adminstrativeId, true);
					soc.setData(findByBlockBlockIdAndIsActive);
					break;
				
				  default:
				    // code block
				}
				
			} catch (Exception e) {
				log.error("Error in getAdminstartiveNameByAdminstrativeId", e);
			}
			
			return soc;
		}

//		@Override
//		public ServiceOutcome getAdminstartiveNameByAdminstrativeId(List allAdminstrativeId, Long adminstrativeId,
//				String demographyName) {
//			// TODO Auto-generated method stub
//			return null;
//		}
		 
		 
		 
}