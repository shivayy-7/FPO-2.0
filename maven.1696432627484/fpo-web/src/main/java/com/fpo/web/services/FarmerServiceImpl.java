package com.fpo.web.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.aashdit.umt.util.SecurityHelper;
import com.fpo.web.entities.Farmer;
import com.fpo.web.entities.FarmerSubActMap;
import com.fpo.web.entities.Member;
import com.fpo.web.repositories.BankBranchRepository;
import com.fpo.web.repositories.CasteRepository;
import com.fpo.web.repositories.FarmerRepository;
import com.fpo.web.repositories.FarmerSubActMapRepository;
import com.fpo.web.repositories.GenderRepository;
import com.fpo.web.repositories.MemberRepository;
import com.fpo.web.repositories.SubActivityRepository;
import com.fpo.web.repositories.VillageRepository;
import com.fpo.web.vos.FarmerDtls;
import com.fpo.web.vos.FarmerSubActMapVO;
import com.fpo.web.vos.FarmerVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FarmerServiceImpl implements FarmerService {
	
	@Autowired private FarmerRepository farmerRepository;
	
	@Autowired private FarmerSubActMapRepository farmerSubActMapRepository;
	
	@Autowired private GenderRepository genderRepository;
	
	@Autowired private CasteRepository casteRepository;
	
	@Autowired private SubActivityRepository subActivityRepository;
	
	@Autowired private VillageRepository villageRepository;
	
	@Autowired private BankBranchRepository bankBranchRepository;
	
	@Autowired private MemberRepository memberRepository;
	
	@Override
	@Transactional
	public ServiceOutcome<FarmerDtls> manageFarmer(FarmerDtls farmerDtls) {
		ServiceOutcome<FarmerDtls> soc = new ServiceOutcome<FarmerDtls>();
		FarmerDtls farmerDtl = new FarmerDtls();
		FarmerVO farmerVO = new FarmerVO();
		Random rand = new Random();
		String currentTab = null;
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		User user = SecurityHelper.getCurrentUser();
//		Farmer existingFarmer = null;
		try {
			
			Farmer existingFarmer = Optional.ofNullable(farmerDtls.getFarmerVO().getFarmerId())
						                        .flatMap(farmerRepository::findById)
						                        .map(farmer->{
						                        	BeanUtils.copyProperties(farmerDtls.getFarmerVO(), farmer);
						                        	farmer.setFarmerCode(farmerDtls.getFarmerVO().getFarmerCode());
						                        	farmer.setGender(Optional.ofNullable(farmerDtls.getFarmerVO().getGender().getGenderId()).isPresent() ? genderRepository.findById(farmerDtls.getFarmerVO().getGender().getGenderId()).get() : null);
						                        	farmer.setCaste(Optional.ofNullable(farmerDtls.getFarmerVO().getCaste().getCasteId()).isPresent() ? casteRepository.findById(farmerDtls.getFarmerVO().getCaste().getCasteId()).get() : null);
						                        	farmer.setActive(true);
						                        	farmer.setStatus(farmerDtls.getFarmerVO().getStatus());
						                        	try {
														farmer.setDob(adjustedFormat.parse(farmerDtls.getFarmerVO().getDob().trim()));
													} catch (ParseException e) {
														e.printStackTrace();
													}
						                        	farmer.setLastUpdatedBy(user);
						                        	farmer.setLastUpdatedOn(new Date());
//						                        	return farmerRepository.save(farmer);
						                        	return farmer;
						                        })
						                        .orElseGet(()->{
						                        	Farmer newFarmer = new Farmer();
						                        	BeanUtils.copyProperties(farmerDtls.getFarmerVO(), newFarmer);
						                        	String farmerCode = "FARMER_" + rand.ints(48, 100)
							  				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
							  				        .limit(6)
							  				        .mapToObj(c -> (char) c)
							  				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
							  				        .toString();
						                        	newFarmer.setFarmerCode(farmerCode);
						                        	newFarmer.setGender(Optional.ofNullable(farmerDtls.getFarmerVO().getGender().getGenderId()).isPresent() ? genderRepository.findByGenderId(farmerDtls.getFarmerVO().getGender().getGenderId()) : null);
						                        	newFarmer.setCaste(Optional.ofNullable(farmerDtls.getFarmerVO().getCaste().getCasteId()).isPresent() ? casteRepository.findById(farmerDtls.getFarmerVO().getCaste().getCasteId()).get() : null);
						                        	newFarmer.setActive(true);
						                        	newFarmer.setStatus(farmerDtls.getFarmerVO().getStatus());
						                        	try {
						                        		newFarmer.setDob(adjustedFormat.parse(farmerDtls.getFarmerVO().getDob().trim()));
													} catch (ParseException e) {
														e.printStackTrace();
													}
						                        	newFarmer.setCreatedBy(user);
						                        	newFarmer.setCreatedOn(new Date());
//						                        	return farmerRepository.save(newFarmer);
						                        	return newFarmer;
						                        });
			
			existingFarmer.setVillageId(Optional.ofNullable(farmerDtls.getFarmerVO().getVillageId()).isPresent()? villageRepository.findByVillageId(farmerDtls.getFarmerVO().getVillageId().getVillageId()) : null);
			existingFarmer.setBankBranchId(Optional.ofNullable(farmerDtls.getFarmerVO().getBankBranchId().getBankBranchId()).isPresent()? bankBranchRepository.findById(farmerDtls.getFarmerVO().getBankBranchId().getBankBranchId()).get() : null);
			
			Farmer saveFarmer = farmerRepository.save(existingFarmer);
			
			FarmerSubActMap existingFarmerSubAct = Optional.ofNullable(farmerDtls.getFarmerSubActMapVO().getFarmerSubactId())
			        .flatMap(farmerSubActMapRepository::findById)
			        .map(existingfarmerSubAct->{
			        	existingfarmerSubAct.setFarmer(saveFarmer);
			        	existingfarmerSubAct.setSubActivity(subActivityRepository.findBySubActCodeAndIsActiveTrue(farmerDtls.getFarmerSubActMapVO().getSubActivity().getSubActCode()));
			        	existingfarmerSubAct.setActive(true);
			        	existingfarmerSubAct.setLastUpdatedBy(user);
			        	existingfarmerSubAct.setLastUpdatedOn(new Date());
			        	return existingfarmerSubAct;
			        }).orElseGet(()->{
			        	FarmerSubActMap farmerSubActMap = new FarmerSubActMap();
						farmerSubActMap.setFarmer(saveFarmer);
						farmerSubActMap.setSubActivity(subActivityRepository.findBySubActCodeAndIsActiveTrue(farmerDtls.getFarmerSubActMapVO().getSubActivity().getSubActCode()));
						farmerSubActMap.setActive(true);
						farmerSubActMap.setCreatedBy(user);
						farmerSubActMap.setCreatedOn(new Date());
						return farmerSubActMap;
			        });
			
			farmerSubActMapRepository.save(existingFarmerSubAct);
			
			if(Optional.ofNullable(farmerDtls.getMember().getMemberId()).isPresent()) {
				Optional<Member> existingMember = memberRepository.findById(farmerDtls.getMember().getMemberId());
				if(existingMember.isPresent()) {
					existingMember.get().setName(saveFarmer.getName());
					existingMember.get().setAadharNo(saveFarmer.getAadharNo());
					existingMember.get().setGender(saveFarmer.getGender());
					existingMember.get().setCaste(saveFarmer.getCaste());
					existingMember.get().setFarmer(saveFarmer);
					existingMember.get().setLastUpdatedBy(user);
					existingMember.get().setLastUpdatedOn(new Date());
					memberRepository.save(existingMember.get());
				}
			}
			
			soc.setData(farmerDtl);
			soc.setMessage(Optional.ofNullable(farmerDtls.getFarmerVO().getFarmerId()).isPresent() ? "FarmerUpdated" : "Farmer Added" );
			soc.setOutcome(true);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("Exception occurred in manageFarmer()-> FarmerServiceImpl "+ e);	
			soc.setData(null);
			soc.setMessage("Unable to process.");
			soc.setOutcome(false);
		}
		return soc;
	}

}
