package com.fpo.web.services;

import com.aashdit.framework.core.ServiceOutcome;
import com.fpo.web.entities.FarmerCbbo;
import com.fpo.web.vos.CbboVO;

import java.util.List;

public interface CBBOServices {
    ServiceOutcome<Boolean> manageCBBO(CbboVO cbboVO);

    ServiceOutcome<CbboVO> getCbboByCbboCode(String cbboCode);
}
