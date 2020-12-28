package com.tools.module.cockpit.service.impl;

import com.tools.common.dynamicquery.DynamicQuery;
import com.tools.common.model.PageBean;
import com.tools.common.model.Result;
import com.tools.common.util.DateUtils;
import com.tools.module.cockpit.repository.CockpitEarlyWarningRepository;
import com.tools.module.cockpit.entity.CockpitEarlyWarning;
import com.tools.module.cockpit.service.CockpitEarlyWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CockpitEarlyWarningServiceImpl implements CockpitEarlyWarningService {

    @Autowired
    private DynamicQuery dynamicQuery;
    @Autowired
    private CockpitEarlyWarningRepository earlyRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(CockpitEarlyWarning early) {
        earlyRepository.saveAndFlush(early);
        return Result.ok("保存成功");
    }

    @Override
    public Result get(Long id) {
        CockpitEarlyWarning early = earlyRepository.getOne(id);
        return Result.ok(early);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(Long id) {
        earlyRepository.deleteById(id);
        return Result.ok("删除成功");
    }

    @Override
    public Result list(CockpitEarlyWarning early) {
        String countSql = "SELECT COUNT(*) FROM cockpit_early_warning";
        Long count = dynamicQuery.nativeQueryCount(countSql);
        PageBean<CockpitEarlyWarning> data = new PageBean<>();
        if(count>0){
            StringBuffer nativeSql = new StringBuffer();
            nativeSql.append("SELECT * FROM cockpit_early_warning ");
            Pageable pageable = PageRequest.of(early.getPageNo(),early.getPageSize());
            List<CockpitEarlyWarning> list =  dynamicQuery.nativeQueryPagingList(CockpitEarlyWarning.class,pageable,nativeSql.toString());
            data = new PageBean(list,count);
        }
        return Result.ok(data);
    }
}
