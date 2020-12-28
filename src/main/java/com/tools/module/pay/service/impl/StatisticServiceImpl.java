package com.tools.module.pay.service.impl;

import com.tools.common.dynamicquery.DynamicQuery;
import com.tools.common.model.Result;
import com.tools.module.pay.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 统计管理
 * 爪哇笔记 https://blog.52itstyle.vip
 * @author 小柒2012
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private DynamicQuery dynamicQuery;

    @Override
    public Result console() {
        HashMap<String,Object> map = new HashMap<>();
        String nativeSql = "SELECT COUNT(*) FROM sys_user";
        Long count = dynamicQuery.nativeQueryCount(nativeSql);
        map.put("userCount",count);
        nativeSql = "SELECT COUNT(*) FROM app_order";
        count = dynamicQuery.nativeQueryCount(nativeSql);
        map.put("orderCount",count);
        nativeSql = "SELECT SUM(total_Fee) FROM app_order";
        BigDecimal totalFee = (BigDecimal) dynamicQuery.querySingleResult(nativeSql);
        map.put("totalFee",totalFee.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        return Result.ok(map);
    }
}
