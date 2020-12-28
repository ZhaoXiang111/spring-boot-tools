package com.tools.module.cockpit.web;

import com.tools.common.config.AbstractController;
import com.tools.common.model.Result;
import com.tools.module.cockpit.entity.CockpitEarlyWarning;
import com.tools.module.cockpit.service.CockpitEarlyWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cockpit/early")

public class CockpitEarlyWarningController extends AbstractController {

    @Autowired
    private CockpitEarlyWarningService earlyService;

    /**
     * 列表
     */
    @PostMapping("list")
    public Result list(CockpitEarlyWarning early){
        return earlyService.list(early);
    }
    /**
     * 查询
     */
    @PostMapping("get")
    public Result get(Long id){
        return earlyService.get(id);
    }
    /**
     * 保存
     */
    @PostMapping("save")
    public Result save(@RequestBody CockpitEarlyWarning early){
        return earlyService.save(early);
    }

    /**
     * 删除
     */
    @PostMapping("delete")
    public Result delete(Long id){
        return earlyService.delete(id);
    }
}
