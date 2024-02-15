package com.fpo.web.services;

import java.util.Date;
import java.util.List;
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
import com.fpo.web.entities.Block;
import com.fpo.web.entities.Farm;
import com.fpo.web.entities.Grampanchayat;
import com.fpo.web.entities.Village;
import com.fpo.web.repositories.BlockRepository;
import com.fpo.web.repositories.FarmRepository;
import com.fpo.web.repositories.FarmerRepository;
import com.fpo.web.repositories.GrampanchayatRpository;
import com.fpo.web.repositories.VillageRepository;
import com.fpo.web.vos.FarmDtls;
import com.fpo.web.vos.FarmVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FarmServiceImpl implements FarmService {
	
	@Autowired private FarmRepository farmRepository;
	
	@Autowired private FarmerRepository farmerRepository;
	
	@Autowired private BlockRepository blockRepository;
	
	@Autowired private GrampanchayatRpository gpRepository;
	
	@Autowired private VillageRepository villageRepository;

	@Override
	@Transactional
	public ServiceOutcome<FarmDtls> manageFarm(FarmDtls farmDtls) {
		ServiceOutcome<FarmDtls> soc = new ServiceOutcome<FarmDtls>();
		User user = SecurityHelper.getCurrentUser();
		Random rand = new Random();
		try {
			Farm savesfarm = Optional.ofNullable(farmDtls.getFarmVO().getFarmId())
			        .flatMap(farmRepository::findById)
			        .map(existing->{
			        	BeanUtils.copyProperties(farmDtls.getFarmVO(), existing, "farmerId");
			        	existing.setLastUpdatedBy(user);
			        	existing.setLastUpdatedOn(new Date());
			        	return existing;
			        }).orElseGet(()->{
			        	Farm farm = new Farm();
			        	BeanUtils.copyProperties(farmDtls.getFarmVO(), farm);
			        	String farmCode = "FARM_" + rand.ints(48, 100)
  				        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
  				        .limit(6)
  				        .mapToObj(c -> (char) c)
  				        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
  				        .toString();
			        	farm.setFarmCode(farmCode);
			        	farm.setFarmerId(Optional.ofNullable(farmDtls.getFarmVO().getFrmId()).isPresent() ? farmerRepository.findById(farmDtls.getFarmVO().getFrmId()).get() : null);
			        	farm.setCreatedBy(user);
			        	farm.setCreatedOn(new Date());
			        	return farm;
			        });
			savesfarm.setIsActive(true);
			farmRepository.save(savesfarm);
			
			soc.setData(farmDtls);
			soc.setMessage(Optional.ofNullable(farmDtls.getFarmVO().getFarmId()).isPresent()?"Farm Updated Based on Farmer":"Farm Added Based on Farmer");
			soc.setOutcome(true);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			log.error("Exception occurred in managefarm()-> FarmServiceImpl "+ e.getMessage());
			soc.setData(null);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<FarmDtls> getFarmData(FarmDtls farmDtls) {
		ServiceOutcome<FarmDtls> soc = new ServiceOutcome<FarmDtls>();
		FarmDtls farmDtl = new FarmDtls();
		try {
			
			List<Farm> farmData = farmRepository.getFarmData(farmDtls.getFarmVO().getBlockId(), farmDtls.getFarmVO().getGpId(), farmDtls.getFarmVO().getVillageId(), farmDtls.getFarmVO().getFrmId());
			farmDtl.setFarmList(farmData);
			
			soc.setData(farmDtl);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in getFarmData()-> FarmServiceImpl "+ e.getMessage());
			soc.setData(null);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<FarmDtls> getFarmByFarmCode(String farmCode) {
		ServiceOutcome<FarmDtls> soc = new ServiceOutcome<FarmDtls>();
		FarmDtls farmDtl = new FarmDtls();
		FarmVO farmVo = new FarmVO();
		try {
			Farm findByFarmCode = farmRepository.findByFarmCode(farmCode);
			BeanUtils.copyProperties(findByFarmCode, farmVo);
			
			Village findByVillageId = villageRepository.findByVillageId(farmVo.getFarmerId().getVillageId().getVillageId());
			Grampanchayat findByGpId = gpRepository.findByGpId(findByVillageId.getGpId().getGpId());
			Block findByBlockId = blockRepository.findByBlockId(findByGpId.getBlock().getBlockId());
			farmVo.setBlockId(findByBlockId.getBlockId());
			farmVo.setGpId(findByGpId.getGpId());
			farmVo.setVillageId(findByVillageId.getVillageId());
			farmVo.setFrmId(findByFarmCode.getFarmerId().getFarmerId());
			
			farmDtl.setFarmVO(farmVo);
			soc.setData(farmDtl);
			soc.setMessage("success");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in getFarmByFarmCode()-> FarmServiceImpl "+ e.getMessage());
			soc.setData(null);
			soc.setMessage("error");
			soc.setOutcome(false);
		}
		return soc;
	}
	
	

}
