package com.fpo.web.repositories;


import com.fpo.web.entities.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {

	/**
	 * get all active Bank list.
	 */
	List<BankBranch> findByIsActive(Boolean isActive);

	/**
	 * get all Branch list by bankName.
	 */
	List<BankBranch> findAllByBankName(String bankName);

	/**
	 * get ifsc by bankName and branchName.
	 */

	List<BankBranch> findAllByBankNameAndBranchName(String bankName, String branchName);

	@Query("select distinct(bankName) from BankBranch where isActive=?1 order by bankName")
	List<String> getBankList(Boolean b);
	
//	@Query("select distinct(bankName) from BankBranch where isActive=?1 order by bankName")
//	List<BankBranch> getBankList1(Boolean b);


	List<BankBranch> findAllByBankNameAndIsActiveTrueOrderByBranchName(String bankName);
	
	List<BankBranch> findAllByBankNameAndIsActiveTrue(String bankName);

	List<BankBranch> findDistinctByIsActiveTrueOrderByBankNameAsc();

	List<BankBranch> findAllBybankBranchIdAndIsActiveTrue(long l);

//	List<BankBranch> findAllByIsActive(boolean b);
	

}
