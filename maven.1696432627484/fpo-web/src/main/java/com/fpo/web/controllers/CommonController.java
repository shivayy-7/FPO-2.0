package com.fpo.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.framework.core.exception.JsonMappingExceptionHandler;
import com.fpo.web.entities.Activity;
import com.fpo.web.entities.BankBranch;
import com.fpo.web.entities.Block;
import com.fpo.web.entities.District;
import com.fpo.web.entities.Grampanchayat;
import com.fpo.web.entities.Municipality;
import com.fpo.web.entities.State;
import com.fpo.web.entities.SubActivity;
import com.fpo.web.entities.Village;
import com.fpo.web.entities.Ward;
import com.fpo.web.services.CommonService;
import com.fpo.web.vos.DuplicateCheckDto;

import lombok.extern.slf4j.Slf4j;

@Controller
//@RequestMapping("/common")  
@RequestMapping("/core")
@Slf4j
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	public static final String APPLICATION_JSON_VALUE = "application/json";

//	@Autowired
//	JsonMappingExceptionHandler jexp;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
	}
	
	
	@GetMapping("/getData")
	public @ResponseBody ServiceOutcome<List<?>> getAjaxDtlsByIdentity(String identity, String id){
		ServiceOutcome<List<?>> soc = new ServiceOutcome<List<?>>();
		try {
			soc = commonService.getAllAjaxCallDetails(identity, id);
		} catch (Exception e) {
			log.error("Exception occurred in getAjaxDtlsByIdentity("+identity+") -> CommonController" + e.getMessage());
		}
		return soc;
	}
	
	/**
	 * purpose fetch districtList data since : 11/11/2020
	 */
	@GetMapping(path = "/district/list", name = "District List")
	public String districtList(Model model) {
		try {
			ServiceOutcome<List<District>> serviceOutcome = commonService.findByAllDistrict();
			if (serviceOutcome.getOutcome()) {
				model.addAttribute("districtList", serviceOutcome.getData());
			} else {
				model.addAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.districtList";

	}

	/**
	 * purpose save and update district data since : 11/11/2020
	 */
	@PostMapping(path = "/district/addNupdate", name = "Add And Update District")
	public String addNupdate(RedirectAttributes attr, District district) {
		try {
			ServiceOutcome<District> serviceOutcome = commonService.addNupdateDistrict(district);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/district/add";

	}

	/**
	 * purpose get district data for edit since : 11/11/2020
	 */
	@GetMapping(path = "/district/edit/{id}", name = "Edit District")
	public String editDistrict(RedirectAttributes attr, @PathVariable("id") Long districtId) {
		try {
			ServiceOutcome<District> serviceOutcome = commonService.getDistrictByDistrictId(districtId);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("districtData", serviceOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				attr.addFlashAttribute("stateList", staOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", staOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/district/add";
	}

	/**
	 * purpose get district page since : 11/11/2020
	 */
	@GetMapping(path = "/district/add", name = "Add District")
	public String addDistrict(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", staOutcome.getMessage());
			}
			ServiceOutcome<List<District>> serviceOutcome = commonService.findByAllDistrict();
			model.addAttribute("districtList", serviceOutcome.getData());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return "site.core.addDistrict";

	}

	/**
	 * purpose:This method used to validate unique code for district since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/district/validateDistrictCode", name = "Validate district code")
	public @ResponseBody DuplicateCheckDto validateDistrictCode(String districtCode, Long districtId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyCode(districtCode.trim(),
					districtId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique name for district since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/district/validateDistrictName", name = "Validate district Name")
	public @ResponseBody DuplicateCheckDto validateDistrictName(String districtName, Long districtId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyName(districtName.trim(),
					districtId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose: get block page since : 11/11/2020
	 */
	@GetMapping(path = "/block/add", name = "Add Block")
	public String addBlock(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", "The request could not be completed");
			}
			ServiceOutcome<List<Block>> serviceOutcome = commonService.getAllBlock();
			if (serviceOutcome.getOutcome()) {
				model.addAttribute("blockList", serviceOutcome.getData());
			}
//			else {
//				model.addAttribute("error_msg", serviceOutcome.getMessage());
//			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.addBlock";

	}

	/**
	 * purpose get block list since : 11/11/2020
	 */
	@GetMapping(path = "/block/list", name = "Block List")
	public String blockList(Model model) {
		try {
			ServiceOutcome<List<Block>> serviceOutcome = commonService.getAllBlock();
			if (serviceOutcome.getOutcome()) {
				model.addAttribute("blockList", serviceOutcome.getData());
			} else {
				model.addAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.blockList";

	}

	/**
	 * purpose save or update block since : 11/11/2020
	 */
	@PostMapping(path = "/block/addNupdate", name = "Add And Update Block")
	public String addNupdate(RedirectAttributes attr, Block block) {
		try {
			ServiceOutcome<Boolean> serviceOutcome = commonService.addBlock(block);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/block/add";
	}

	/**
	 * purpose get block for edit since : 11/11/2020
	 */
	@GetMapping(path = "/block/edit/{id}")
	public String editBlock(RedirectAttributes attr, @PathVariable("id") Long blockId) {
		try {
			ServiceOutcome<Block> serviceOutcome = commonService.editBlock(blockId);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("blockData", serviceOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				attr.addFlashAttribute("stateList", staOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", staOutcome.getMessage());
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/block/add";

	}

	/**
	 * purpose:This method used to validate unique code for block since : 11/11/2020
	 */
	@GetMapping(path = "/block/validateBlockCode", name = "Validate Block Code")
	public @ResponseBody DuplicateCheckDto validateBlockCode(String blockCode, Long blockId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyCode(blockCode.trim(),
					blockId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique code for block since : 11/11/2020
	 */
	@GetMapping(path = "/block/validateBlockName", name = "Validate Block Name")
	public @ResponseBody DuplicateCheckDto validateBlockName(String blockName, Long blockId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyName(blockName.trim(),
					blockId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose get Municipality Page since : 11/11/2020
	 */
	@GetMapping(path = "/municipality/add", name = "Add Municipality")
	public String addMunicipality(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", staOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return "site.core.addMuncipality";
	}

	/**
	 * purpose get Municipality List since : 11/11/2020
	 */
	@GetMapping(path = "/municipality/list", name = "Muncipality List")
	public String muncipalityList(Model model) {
		try {
			ServiceOutcome<List<Municipality>> muOutcome = commonService.getAllMunicipality();
			if (muOutcome.getOutcome()) {
				model.addAttribute("municipalityList", muOutcome.getData());
			} else {
				model.addAttribute("error_msg", muOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return "site.core.municipalityList";
	}

	/**
	 * purpose save or update Municipality since : 11/11/2020
	 */
	@PostMapping(path = "/municipality/addNupdate", name = "Add And Update Municipality")
	public String addNupdateMunicipality(RedirectAttributes attr, Municipality municipality) {
		try {
			ServiceOutcome<Municipality> serviceOutcome = commonService.addNupdateMunicipality(municipality);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/municipality/list";

	}

	/**
	 * purpose get Municipality for edit since : 11/11/2020
	 */
	@GetMapping(path = "/municipality/edit/{id}", name = "Edit Municipality")
	public String editMuncipality(RedirectAttributes attr, @PathVariable("id") Long muncipalityId) {
		try {
			ServiceOutcome<Municipality> serviceOutcome = commonService.getMuncipalityByMuncipalityId(muncipalityId);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("municipalityData", serviceOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				attr.addFlashAttribute("stateList", staOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", staOutcome.getMessage());
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/municipality/add";

	}

	/**
	 * purpose:This method used to validate unique code for Municipality since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/municipality/validateMunicipalityCode", name = "Validate Municipality Code")
	public @ResponseBody DuplicateCheckDto validateMunicipalityCode(String municipalityCode, Long municipalityId,
																	String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService
					.checkDuplicateByAnyCode(municipalityCode.trim(), municipalityId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique Name for Municipality since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/municipality/validateMunicipalityName", name = "Validate Municipality Name")
	public @ResponseBody DuplicateCheckDto validateMunicipalityName(String municipalityName, Long municipalityId,
																	String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService
					.checkDuplicateByAnyName(municipalityName.trim(), municipalityId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose get grampanchayat page since : 11/11/2020
	 */
	@GetMapping(path = "/grampanchayat/add", name = "Add Grampanchayat")
	public String addGrampanchayat(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", staOutcome.getMessage());
			}
			ServiceOutcome<List<Grampanchayat>> gpOutcome = commonService.getAllGrampanchayats();
			if (gpOutcome.getOutcome()) {
				model.addAttribute("gpList", gpOutcome.getData());
			}
//			else {
//				model.addAttribute("error_msg", gpOutcome.getMessage());
//			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.addGrampanchayat";
	}

	/**
	 * purpose get grampanchayat list since : 11/11/2020
	 */
	@GetMapping(path = "/grampanchayat/list", name = "Grampanchayat List")
	public String grampanchayatList(Model model) {
		try {
			ServiceOutcome<List<Grampanchayat>> gpOutcome = commonService.getAllGrampanchayats();
			if (gpOutcome.getOutcome()) {
				model.addAttribute("gpList", gpOutcome.getData());
			} else {
				model.addAttribute("error_msg", gpOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return "site.core.grampanchayatList";
	}

	/**
	 * purpose get grampanchayat for edit since : 11/11/2020
	 */
	@GetMapping(path = "/grampanchayat/edit/{id}")
	public String editGrampanchayat(RedirectAttributes attr, @PathVariable("id") Long gpId) {
		try {
			ServiceOutcome<Grampanchayat> serviceOutcome = commonService.getGrampanchayatByGpId(gpId);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("grampanchayatData", serviceOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				attr.addFlashAttribute("stateList", staOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", staOutcome.getMessage());
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/grampanchayat/add";

	}

	/**
	 * purpose save or update grampanchayat since : 11/11/2020
	 */
	@PostMapping(path = "/grampanchayat/addNupdate", name = "Add And Update Grampanchayat")
	public String addNupdateGrampanchayat(RedirectAttributes attr, Grampanchayat grampanchayat) {
		try {
			ServiceOutcome<Grampanchayat> serviceOutcome = commonService.addNupdateGrampanchayat(grampanchayat);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/grampanchayat/add";

	}

	/**
	 * purpose:This method used to validate unique code for Grampanchayat since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/grampanchayat/validateGrampanchayatCode", name = "Validate Grampanchayat Code")
	public @ResponseBody DuplicateCheckDto validateGrampanchayatCode(String grampanchayatCode, Long grampanchayatId,
																	 String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService
					.checkDuplicateByAnyCode(grampanchayatCode.trim(), grampanchayatId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique Name for Grampanchayat since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/grampanchayat/validateGrampanchayatName", name = "Validate Grampanchayat Name")
	public @ResponseBody DuplicateCheckDto validateGrampanchayatName(String grampanchayatName, Long grampanchayatId,
																	 String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService
					.checkDuplicateByAnyName(grampanchayatName.trim(), grampanchayatId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose to get village list since : 11/11/2020
	 */
	@GetMapping(path = "/village/list", name = "Village List")
	public String villageList(Model model) {
		try {
			List<Village> list = new ArrayList<>();
			ServiceOutcome<List<District>> districtList = commonService.getDistricts(true);
			model.addAttribute("districtList", districtList.getData());
			model.addAttribute("villageList", list);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.villageList";
	}

	@PostMapping(path = "/village/getAllVillage.json", name = "Village List")
	@ResponseBody
	public String allVillageList(Model model ,@RequestParam Integer draw,
								 @RequestParam Integer start,
								 @RequestParam Integer length,
								 @RequestParam(name="search[value]", required = false) String search) {
		JSONObject village =null;
		try {
			List<Village> villageList = new ArrayList<Village>();

			villageList = commonService.findAllVillage();
			village = new JSONObject();
			village.put("draw", draw);
			village.put("recordsTotal",villageList.size() );
			JSONArray jarr = new JSONArray();
			List<Village> finalVillageList = new ArrayList<Village>();
			if (!search.equals(""))
			{
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getBlock().getDistrict().getDistrictName() != null && b.getGpId().getBlock().getDistrict().getDistrictName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getBlock().getBlockName() != null  && b.getGpId().getBlock().getBlockName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getGpName() != null && b.getGpId().getGpName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getVillageName() != null && b.getVillageName().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getVillageCode() != null && b.getVillageCode().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getIsActive() != null && b.getIsActive()).collect(Collectors.toList()));
				village.put("recordsFiltered",finalVillageList.size() );

			}
			else {
				finalVillageList.addAll(villageList);
				village.put("recordsFiltered",villageList.size() );
			}

			int listSize = finalVillageList.size();
			village.put("data",jarr);

			if (listSize > start + length )
			{
				finalVillageList = finalVillageList.subList(start, start + length);
			}

			if (start + length >= listSize  )
			{
				finalVillageList = finalVillageList.subList(start, finalVillageList.size());
			}

			for (int idx = 0; idx < finalVillageList.size(); idx++ )
			{
				Village b = finalVillageList.get(idx);
				JSONObject jEntry = new JSONObject();
				jEntry.put("slNum", start + idx +1);
				jEntry.put("DistrictName", b.getGpId().getBlock().getDistrict().getDistrictName() == null ? "" : b.getGpId().getBlock().getDistrict().getDistrictName());
				jEntry.put("BlockName", b.getGpId().getBlock().getBlockNameEN() == null ? "" : b.getGpId().getBlock().getBlockNameEN());
				jEntry.put("GpName", b.getGpId().getGpNameEN() == null ? "" : b.getGpId().getGpNameEN());
				jEntry.put("VillageName", b.getVillageNameEn() == null ? "" : b.getVillageNameEn());
				jEntry.put("VillageCode", b.getVillageCode() == null ? "" : b.getVillageCode());
//				jEntry.put("VillageLgdCode", b.getVillageLgdCode() == null ? "" : b.getVillageLgdCode());
//				jEntry.put("VillageCensusCode", b.getVillageCensusCode() == null ? "" : b.getVillageCensusCode());
//				jEntry.put("VillageTribal", b.getVillageTribal() == true ? "Yes" : "No");
				jEntry.put("Status", b.getIsActive() == true ? "Active" : "Inactive");
				jEntry.put("id", b.getVillageId() == null ? ""  : b.getVillageId());
				jEntry.put("isActive",b.getIsActive());

				jarr.put(jEntry);
			}
			village.put("data",jarr);

//		if (villaServiceOutcome.getOutcome()) {
//			model.addAttribute("villageList", villaServiceOutcome.getData());
//		} else {
//			model.addAttribute("error_msg", villaServiceOutcome.getMessage());
//		}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return village.toString();
	}

	/**
	 * purpose : get village page since : 11/11/2020
	 */
	@GetMapping(path = "/village/add", name = "Add Village")
	public String addVillage(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", "The request could not be completed");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "site.core.addVillage";
	}

	/**
	 * purpose : save or update village since : 11/11/2020
	 */
	@PostMapping(path = "/village/addNupdate", name = "Add And Update Village")
	public String addNupdateVillage(RedirectAttributes attr, Village village) {
		try {
			ServiceOutcome<Village> villOutcome = commonService.addNupdateVillage(village);
			if (villOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg", villOutcome.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", villOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/village/add";

	}

	/**
	 * purpose : get village for edit since : 11/11/2020
	 */
	@GetMapping(path = "/village/edit/{id}", name = "Edit Village")
	public String editVilllage(RedirectAttributes attr, @PathVariable("id") Long gpId) {
		try {
			ServiceOutcome<Village> serviceOutcome = commonService.getVillageByVillageId(gpId);
			if (serviceOutcome.getOutcome()) {
				attr.addFlashAttribute("villageData", serviceOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

			ServiceOutcome<List<State>> staOutcome = commonService.findAllState(true);
			if (staOutcome.getOutcome()) {
				attr.addFlashAttribute("stateList", staOutcome.getData());
			} else {
				attr.addFlashAttribute("error_msg", "The request could not be completed");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "redirect:/core/village/add";

	}

	/**
	 * purpose:This method used to validate unique code for Village since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/village/validateVillageCode", name = "Validate Village Code")
	public @ResponseBody DuplicateCheckDto validateVillageCode(String villageCode, Long villageId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyCode(villageCode.trim(),
					villageId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique Name for Village since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/village/validateVillageName", name = "Validate Village Code")
	public @ResponseBody DuplicateCheckDto validateVillageName(String villageName, Long villageId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonService.checkDuplicateByAnyName(villageName.trim(),
					villageId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return duplicateCheckDto;
	}

	/** Ajax call Start **/

	@GetMapping(path = "/getStateList", name = "get State List")
	public @ResponseBody String getStateList() {
		JSONArray array = new JSONArray();
		try {
			ServiceOutcome<List<State>> stateList = commonService.findAllState(true);
			if (stateList != null) {
				stateList.getData().forEach(state -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("stateId", state.getStateId());
					jsonObject.put("stateName", state.getStateName());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}

	@GetMapping(path = "/findDistrictListByStateId", name = "find district list by state id")
	public @ResponseBody String findDistrictListByStateId(Long stateId) {
		JSONArray array = new JSONArray();
		try {
			ServiceOutcome<List<District>> districtList = commonService.getAllActiveDistrictByStateId(stateId,false);
			if (districtList != null) {
				districtList.getData().forEach(district -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("districtId", district.getDistrictId());
					jsonObject.put("districtName", district.getDistrictName());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}

	@GetMapping(path = "/findBlockListByDistrictId", name = "find Block list by District id")
	public @ResponseBody String findBlockListByDistrictId(Long districtId) {
		JSONArray array = new JSONArray();
		try {
			List<Block> blockList = commonService.getAllActiveBlockByDistrictId(districtId,false).getData();
			if (blockList != null) {
				blockList.forEach(block -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("blockId", block.getBlockId());
					jsonObject.put("blockName", block.getBlockNameEN());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}

	@GetMapping(path = "/findGpListByBlockId", name = "find Gp list by block id")
	public @ResponseBody String findGpListByBlockId(Long blockId) {
		JSONArray array = new JSONArray();
		try {
			List<Grampanchayat> grampanchayatList = commonService.getAllActiveGpByBlockId(blockId,false).getData();
			if (grampanchayatList != null) {
				grampanchayatList.forEach(grampanchayat -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("gpId", grampanchayat.getGpId());
					jsonObject.put("gpName", grampanchayat.getGpNameEN());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}

	@GetMapping(path = "/findVillageListByGpId", name = "find Village list by gp id")
	public @ResponseBody String findVillageListByGpId(Long gpId) {
		JSONArray array = new JSONArray();
		try {
			List<Village> villageList = commonService.getAllActiveVillageByGpId(gpId,false).getData();
			if (villageList != null) {
				villageList.forEach(village -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("villageId", village.getVillageId());
					jsonObject.put("villageName", village.getVillageNameEn());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}

	@GetMapping(path = "/findMunicipalityListByDistrictId", name = "find Municipality list by district id")
	public @ResponseBody String findMunicipalityListByDistrictId(Long districtId) {
		JSONArray array = new JSONArray();
		try {
			List<Municipality> municipalities = commonService.getMunicipalityByDistrictId(districtId,false).getData();
			if (municipalities != null) {
				municipalities.forEach(municipality -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("municipalityId", municipality.getMunicipalityId());
					jsonObject.put("municipalityName", municipality.getMunicipalityNameEn());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();

	}


	@GetMapping(path = "/findWardListByULBId", name = "find Ward list by ULB id")
	public @ResponseBody String findWardListByULBId(Long ulbId) {
		JSONArray array = new JSONArray();
		try {
			List<Ward> wards = commonService.getWardListByUlb(ulbId,false).getData();
			if (wards != null) {
				wards.forEach(ward -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("ulbId", ward.getWardId());
					jsonObject.put("ulbName", ward.getWardName());
					array.put(jsonObject);
				});
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array.toString();
	}


	/**
	 * purpose : Find all banks
	 *
	 * @since : 17/10/2020
	 */
	@GetMapping(path = "/bank/list", produces = MediaType.APPLICATION_JSON_VALUE, name = "Bank LIst")
	public @ResponseBody ServiceOutcome<List<String>> getBankList() {
		return commonService.getBankList();

	}

	/**
	 * purpose : Find all Branch list for a bank
	 *
	 * @since : 17/10/2020
	 */
	@GetMapping(path = "/getBranchNameByBankName", produces = MediaType.APPLICATION_JSON_VALUE, name = "Branch Name for a Bank")
	public @ResponseBody ServiceOutcome<List<BankBranch>> getBranchByBankName(@RequestParam("bankName") String bankName) {
		return commonService.findByBankName(bankName);

	}

	/**
	 * purpose : Find IFSC by bankName and branchName
	 *
	 * @since : 17/10/2020
	 */
	@GetMapping(path = "/getIfscByBranchName", produces = MediaType.APPLICATION_JSON_VALUE, name = "IFSC Code by Bank and Branch")
	public @ResponseBody ServiceOutcome<List<BankBranch>> getIfscByBranchName(@RequestParam("bankName") String bankName,
																			  @RequestParam("branchName") String branchName) {
		return commonService.findIfscByBankNameBranchName(bankName, branchName);

	}


//@ResponseBody
//@GetMapping(value = "/getBankBranchByBank")
//public String getBankBranchByBank(@RequestParam("bank") String bank) {
//
//	JSONArray array = new JSONArray();
//	JSONObject jsonObject = null;
//
//	List<Object[]> branches = commonService.getBankBranchByBank(bank);
//
//	for (Object[] branch : branches) {
//		jsonObject = new JSONObject();
//		jsonObject.putOpt("branchName", branch[0]);
//		jsonObject.putOpt("branchCode", branch[1]);
//		array.put(jsonObject);
//	}
//	return array.toString();
//}
//
//@ResponseBody
//@GetMapping(value = "/getIFSCByBankBranch")
//public String getIFSCByBankBranch(@RequestParam("bank") String bank, @RequestParam("branch") String branch) {
//
//	JSONArray array = new JSONArray();
//	JSONObject jsonObject = null;
//
//	List<Object> ifsc_code = commonService.getIFSCByBankBranch(bank, branch);
//
//	for (Object ifsc : ifsc_code) {
//		jsonObject = new JSONObject();
//		jsonObject.putOpt("ifsc", ifsc);
//		array.put(jsonObject);
//	}
//	return array.toString();
//}

	
	
	
  @GetMapping(path = "/download/{docId}/{downloadCode}", name ="Download File") 
  public ResponseEntity<byte[]> downloadDocument(HttpServletRequest request, 
		  HttpServletResponse response, @PathVariable("docId") Long docId,@PathVariable("downloadCode")String downloadCode) { 
	  HttpHeaders headers = new HttpHeaders(); 
	  try { 
		  Path path = null;
		  String doc= null; 
		  String fileExtension = "";
		  byte[] data = null;
		  doc=commonService.getDocument(downloadCode,docId);
		  path = Paths.get(doc);
		  fileExtension =doc.split("\\.")[1]; 
		  data = Files.readAllBytes(path);
		  headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		  if(fileExtension.equalsIgnoreCase("pdf"))
			  response.setContentType("application/pdf"); 
		  else if(fileExtension.equalsIgnoreCase("png"))
			  response.setContentType("image/png"); 
		  else if(fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpg")) 
			  response.setContentType("image/jpeg");
		  
		  response.setHeader("Content-disposition","inline;filename="+path.getFileName());
		  headers.setContentLength(data.length);
		  
		  return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
  } catch(IOException e) {
	  return new ResponseEntity<byte[]>(null, headers,HttpStatus.INTERNAL_SERVER_ERROR); }
  }
  
	@GetMapping(path = "/activeInActiveDmgy/{demoCall}/{id}/{status}")
	public String inActNActDemgry(RedirectAttributes attr,@PathVariable String demoCall,@PathVariable Long id,@PathVariable Boolean status) {
		try {
			ServiceOutcome<String> serviceOutcome = commonService.inActNActDemgry(demoCall,id,status);
			attr.addFlashAttribute(serviceOutcome.getOutcome() ? "success_msg" : "error_msg",serviceOutcome.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return demoCall.equals("DIST") ? "redirect:/core/district/add" 
				: demoCall.equals("BLOCK") ? "redirect:/core/block/add" 
						: demoCall.equals("GP") ? "redirect:/core/grampanchayat/add"
								: "redirect:/core/village/list";
	}
	
	@PostMapping(path = "/village/filter/getFilterAllVillage.json", name = "Village List")
	@ResponseBody
	public String filterAllVillageList(@RequestParam(name="districtId",required = false) Long districtId,
			@RequestParam(name="blockId",required = false) Long blockId,
			@RequestParam(name="gpId",required = false) Long gpId,
			@RequestParam(name="villageId",required = false) Long villageId,@RequestParam Integer draw,
			@RequestParam Integer start,
			@RequestParam Integer length,
			@RequestParam(name="search[value]", required = false) String search) {
		JSONObject village =null;
		try {
			List<Village> villageList = new ArrayList<Village>();

			villageList = commonService.findAllFilterVillageList(districtId,blockId,gpId,villageId);
			village = new JSONObject();
			village.put("draw", draw);
			village.put("recordsTotal",villageList.size() );
			JSONArray jarr = new JSONArray();
			List<Village> finalVillageList = new ArrayList<Village>();
			if (!search.equals(""))
			{
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getBlock().getDistrict().getDistrictName() != null && b.getGpId().getBlock().getDistrict().getDistrictName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getBlock().getBlockName() != null  && b.getGpId().getBlock().getBlockName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getGpId().getGpName() != null && b.getGpId().getGpName().trim().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getVillageName() != null && b.getVillageName().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getVillageCode() != null && b.getVillageCode().contains(search)).collect(Collectors.toList()));
				finalVillageList.addAll(villageList.parallelStream().filter(b -> b.getIsActive() != null && b.getIsActive()).collect(Collectors.toList()));
				village.put("recordsFiltered",finalVillageList.size() );

			}
			else {
				finalVillageList.addAll(villageList);
				village.put("recordsFiltered",villageList.size() );
			}

			int listSize = finalVillageList.size();
			village.put("data",jarr);

			if (listSize > start + length )
			{
				finalVillageList = finalVillageList.subList(start, start + length);
			}

			if (start + length >= listSize  )
			{
				finalVillageList = finalVillageList.subList(start, finalVillageList.size());
			}

			for (int idx = 0; idx < finalVillageList.size(); idx++ )
			{
				Village b = finalVillageList.get(idx);
				JSONObject jEntry = new JSONObject();
				jEntry.put("slNum", start + idx +1);
				jEntry.put("DistrictName", b.getGpId().getBlock().getDistrict().getDistrictName() == null ? "" : b.getGpId().getBlock().getDistrict().getDistrictName());
				jEntry.put("BlockName", b.getGpId().getBlock().getBlockNameEN() == null ? "" : b.getGpId().getBlock().getBlockNameEN());
				jEntry.put("GpName", b.getGpId().getGpNameEN() == null ? "" : b.getGpId().getGpNameEN());
				jEntry.put("VillageName", b.getVillageNameEn() == null ? "" : b.getVillageNameEn());
				jEntry.put("VillageCode", b.getVillageCode() == null ? "" : b.getVillageCode());
//				jEntry.put("VillageLgdCode", b.getVillageLgdCode() == null ? "" : b.getVillageLgdCode());
//				jEntry.put("VillageCensusCode", b.getVillageCensusCode() == null ? "" : b.getVillageCensusCode());
//				jEntry.put("VillageTribal", b.getVillageTribal() == true ? "Yes" : "No");
				jEntry.put("Status", b.getIsActive() == true ? "Active" : "Inactive");
				jEntry.put("id", b.getVillageId() == null ? ""  : b.getVillageId());
				jEntry.put("isActive",b.getIsActive());

				jarr.put(jEntry);
			}
			village.put("data",jarr);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return village.toString();
	}
	
	@ResponseBody
	@GetMapping("/getActivityList")
    public List<Activity> getActivityList(String cmpCode) {
        try{
            return commonService.getActivityList(cmpCode);
        }catch (Exception e){
            log.error("Error in getActivityList", e);
            return new ArrayList<Activity>();
        }
    }

    @GetMapping("/getSubActivityList")
    public List<SubActivity> getSubActivityList(String actCode) {
        try{
            return commonService.getSubActivityList(actCode);
        }catch (Exception e){
            log.error("Error in getSubActivityList", e);
            return new ArrayList<SubActivity>();
        }
    }
	
}
