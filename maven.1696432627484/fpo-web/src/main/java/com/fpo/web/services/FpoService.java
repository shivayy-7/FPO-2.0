package com.fpo.web.services;

import java.util.List;

import com.aashdit.framework.core.ServiceOutcome;
import com.aashdit.umt.model.User;
import com.fpo.web.entities.Fpo;
import com.fpo.web.entities.TrainingFpoMap;
import com.fpo.web.vos.FpoDtls;

public interface FpoService {

	ServiceOutcome<Boolean> saveFpoDtls(FpoDtls fpoDtls);

	ServiceOutcome<FpoDtls> getFpoByCode(String fpoCode);

	ServiceOutcome<FpoDtls> isEventAvailable(User user);

	ServiceOutcome<List<TrainingFpoMap>> setEventAcceptanceValue(String eventCode, String status);

}
