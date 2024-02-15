package com.fpo.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpo.web.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findAllByIsActive(boolean b);

	Optional<Member> findByMemberCode(String memberCode);

	@Query(value = "SELECT * FROM t_frm_member tfm WHERE tfm.aadhar_no LIKE CONCAT('%', :aadharNo, '%')", nativeQuery = true)
	List<Member> getMemberDetailsByAadharNo(@Param("aadharNo") String aadharNo);


}