package com.tools.module.cockpit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tools.common.model.PageBean;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class CockpitEarlyWarning extends PageBean implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 预警来源 0-手动录入 1-系统生成
     */
    @Column(name = "warning_source")
    private Integer warningSource;
    /**
     * 预警类型 0-业务预警
     */
    @Column(name = "warning_type")
    private Integer warningType;
    /**
     * 0-代表所有指数预警，预警对应的code(当前自己设置，后期对应中枢上编号)
     */
    @Column(name = "warning_code")
    private Integer warningCode;
    /**
     * 预警详细信息
     */
    @Column(name = "warning_content")
    private String warningContent;
    /**
     * 生效时间
     */
    @Column(name = "warning_startTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp warningStarttime;
    /**
     * 结束是假
     */
    @Column(name = "warning_endTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp warningEndtime;
    /**
     * 状态（0正常 1停用）
     */
    /**
     * 创建者
     */
    @Column(name = "create_by",length = 64)
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}