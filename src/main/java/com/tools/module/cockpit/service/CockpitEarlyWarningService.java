package com.tools.module.cockpit.service;

import com.tools.common.model.Result;
import com.tools.module.cockpit.entity.CockpitEarlyWarning;

public interface CockpitEarlyWarningService {

    Result get(Long id);

    Result save(CockpitEarlyWarning early);

    Result delete(Long task);

    Result list(CockpitEarlyWarning early);
}
