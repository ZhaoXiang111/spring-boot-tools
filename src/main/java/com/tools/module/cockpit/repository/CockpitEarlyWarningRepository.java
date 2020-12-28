package com.tools.module.cockpit.repository;

import com.tools.module.cockpit.entity.CockpitEarlyWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CockpitEarlyWarningRepository extends JpaRepository<CockpitEarlyWarning, Long> {

}