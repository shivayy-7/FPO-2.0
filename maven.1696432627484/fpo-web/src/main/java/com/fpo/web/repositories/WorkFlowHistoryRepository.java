package com.fpo.web.repositories;

import com.fpo.web.entities.WorkFlowHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkFlowHistoryRepository extends JpaRepository<WorkFlowHistory, Long> {

	List<WorkFlowHistory> findByEntityId(Long formId);

	List<WorkFlowHistory> findByEntityIdAndModuleForm(Long formId, String moduleForm);

	List<WorkFlowHistory> findByEntityIdAndModuleFormOrderByHistoryIdAsc(Long formId, String moduleForm);

	List<WorkFlowHistory> findByEntityIdAndModuleFormAndToStageUpdatedStatusStatusIgnoreCaseOrderByHistoryIdAsc(
			Long formId, String moduleForm, String statusFinder);


}
