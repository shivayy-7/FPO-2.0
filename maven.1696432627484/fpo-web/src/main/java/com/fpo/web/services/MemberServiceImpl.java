package com.fpo.web.services;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.Activity;
import com.fpo.web.entities.Caste;
import com.fpo.web.entities.Farmer;
import com.fpo.web.entities.FarmerSubActMap;
import com.fpo.web.entities.FpoSubactivityMap;
import com.fpo.web.entities.MapActivity;
import com.fpo.web.entities.MapSubActivity;
import com.fpo.web.entities.Member;
import com.fpo.web.entities.SubActivity;
import com.fpo.web.entities.Village;
import com.fpo.web.repositories.ActivityRepository;
import com.fpo.web.repositories.CasteRepository;
import com.fpo.web.repositories.FarmerRepository;
import com.fpo.web.repositories.FarmerSubActMapRepository;
import com.fpo.web.repositories.GenderRepository;
import com.fpo.web.repositories.MapActivityRepository;
import com.fpo.web.repositories.MapSubActivityRepository;
import com.fpo.web.repositories.MemberRepository;
import com.fpo.web.repositories.SubActivityRepository;
import com.fpo.web.repositories.VillageRepository;
import com.fpo.web.utils.ApplicationConstants;
import com.fpo.web.vos.FarmerDtls;
import com.fpo.web.vos.FarmerVO;
import com.fpo.web.vos.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired private MemberRepository memberRepository;
	
	@Autowired private GenderRepository genderRepository;
	
	@Autowired private CasteRepository casteRepository;
	
	@Autowired private FarmerRepository farmerRepository;
	
	@Autowired private FarmerSubActMapRepository farmerSubActMapRepository;
	
	@Autowired private SubActivityRepository subActivityRepository;
	
	@Autowired private MapSubActivityRepository mapSubActivityRepository;
	
	@Autowired private MapActivityRepository mapActRepository;
	
	@Autowired private ActivityRepository actRepository;
	
	@Autowired private VillageRepository villageRepository;
	
	
	@Override
	public ServiceOutcome<FarmerDtls> manageMember(FarmerDtls farmerDtls) {
		ServiceOutcome<FarmerDtls> soc = new ServiceOutcome<FarmerDtls>();
		SimpleDateFormat adjustedFormat = new SimpleDateFormat("dd/MM/yyyy");
		Random rand = new Random();
		try {
			String status = farmerDtls.getMember().getStatus();
			if(status.equalsIgnoreCase(ApplicationConstants.UPDATE)) {
				// for existing member
				Optional<Member> existingMember = memberRepository.findById(farmerDtls.getMember().getMemberId());
				if(existingMember.isPresent()) {
					String memberCode = existingMember.get().getMemberCode();
					Farmer farmer = existingMember.get().getFarmer();
					BeanUtils.copyProperties(farmerDtls.getMember(), existingMember.get(),"farmer");
//					existingMember.get().setMemberCode(memberCode);
					existingMember.get().setCaste(Optional.ofNullable(farmerDtls.getMember().getCaste().getCasteId()).isPresent() ? casteRepository.findById(farmerDtls.getMember().getCaste().getCasteId()).get() : null);
					existingMember.get().setGender(Optional.ofNullable(farmerDtls.getMember().getGender().getGenderId()).isPresent() ? genderRepository.findByGenderId(farmerDtls.getMember().getGender().getGenderId()) : null);
//					existingMember.get().setFarmer(null)
					try {
						existingMember.get().setDateOfReg(adjustedFormat.parse(farmerDtls.getDateOfReg().trim()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					existingMember.get().setIsActive(true);
					Member saveMember = memberRepository.save(existingMember.get()); 
					if(Optional.ofNullable(existingMember.get().getFarmer()).isPresent()) {
						Optional<Farmer> farmerMember = farmerRepository.findById(existingMember.get().getFarmer().getFarmerId());
						if(farmerMember.isPresent()) {
							farmerMember.get().setName(saveMember.getName());
							farmerMember.get().setAadharNo(saveMember.getAadharNo());
							farmerMember.get().setGender(saveMember.getGender());
							farmerMember.get().setCaste(saveMember.getCaste());
							farmerRepository.save(farmerMember.get());
							
						}
					}
				}
				
			}else {
				//for new Member
				farmerDtls.getMemberVO().forEach(member->{
					System.out.println(member);
					Member newMember = new Member();
						BeanUtils.copyProperties(member, newMember);
						String memberCode = "MEMBER_" + rand.ints(48, 100)
					        .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
					        .limit(6)
					        .mapToObj(c -> (char) c)
					        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
					        .toString();
						newMember.setMemberCode(memberCode);
					newMember.setCaste(Optional.ofNullable(member.getCaste().getCasteId()).isPresent() ? casteRepository.findById(member.getCaste().getCasteId()).get() : null);
					newMember.setGender(Optional.ofNullable(member.getGender().getGenderId()).isPresent() ? genderRepository.findByGenderId(member.getGender().getGenderId()) : null);
					try {
						newMember.setDateOfReg(adjustedFormat.parse(farmerDtls.getDateOfReg().trim()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					newMember.setIsActive(true);
					memberRepository.save(newMember); 
				});
			}
			
			soc.setMessage(status.equalsIgnoreCase(ApplicationConstants.UPDATE) ? "Member Updated" : "Member Added");
			soc.setOutcome(true);
		} catch (Exception e) {
			log.error("Exception occurred in manageMember()-> MemberServiceImpl "+ e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	@Override
	public ServiceOutcome<FarmerDtls> getByMemberCode(String memberCode) {
		ServiceOutcome<FarmerDtls> soc = new ServiceOutcome<FarmerDtls>();
		FarmerDtls farmerDtls = new FarmerDtls();
		MemberVO memberVo = new MemberVO();
		FarmerVO farmerVo = new FarmerVO();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Optional<Member> findByMemberCode = memberRepository.findByMemberCode(memberCode);
			if(Optional.ofNullable(findByMemberCode).isPresent()) {
				
				BeanUtils.copyProperties(findByMemberCode.get(), memberVo);
				Date dateOfReg = dateTimeFormat.parse(findByMemberCode.get().getDateOfReg().toString());
				memberVo.setDateOfReg(dateFormat.format(dateOfReg));
				farmerDtls.setMember(memberVo);
				
				if(Optional.ofNullable(findByMemberCode.get().getFarmer()).isPresent()) {
					BeanUtils.copyProperties(findByMemberCode.get().getFarmer(), farmerVo);
					farmerDtls.setFarmerVO(farmerVo);
					List<FarmerSubActMap> farmerSubact = farmerSubActMapRepository.findByFarmerFarmerId(findByMemberCode.get().getFarmer().getFarmerId());
					if(farmerSubact.size()>0) {
						List<String> subActList = new ArrayList<>();
						farmerSubact.forEach(subAct -> {
//						    Long id = subAct.getSubActivity().getId();
						    subActList.add(subAct.getSubActivity().getSubActCode());
//						    System.out.println("SubActivity -"+id);
						});
						farmerDtls.setSubActivity(subActList);
						
						for (String subAct : subActList) {
							SubActivity subActivity = subActivityRepository.findBySubActCode(subAct);
							List<MapSubActivity> mapSubActivity = mapSubActivityRepository.findBySubActCodeSubActCode(subActivity.getSubActCode());
							mapSubActivity.forEach(mapSub->{
								String actCode = mapSub.getActCode().getActCode();
								farmerDtls.setActivity(actCode);
								
								Activity activity = actRepository.findByActCode(actCode);
								System.out.println(activity.getId());
								MapActivity mapActivity = mapActRepository.findById(activity.getId()).get();
								farmerDtls.setComponent( mapActivity.getComponent().getCmpCode());
							});
						}
					}
					if(Optional.ofNullable(findByMemberCode.get().getFarmer().getVillageId()).isPresent()) {
						Village findByVillageId = villageRepository.findByVillageId(findByMemberCode.get().getFarmer().getVillageId().getVillageId());
						farmerDtls.setGp(findByVillageId.getGpId().getGpId());
					}
					
				}
				
				soc.setData(farmerDtls);
				System.out.println(farmerDtls);
				soc.setMessage("success");
				soc.setOutcome(true);
			}
		} catch (Exception e) {
			log.error("Exception occurred in getByMemberCode()-> MemberServiceImpl "+ e);
			soc.setMessage("Unable to process");
			soc.setOutcome(false);
		}
		return soc;
	}

	
	
}
