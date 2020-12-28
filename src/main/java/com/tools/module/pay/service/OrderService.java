package com.tools.module.pay.service;

import com.tools.common.model.Result;
import com.tools.module.pay.entity.Order;

/**
 * 订单管理
 * 爪哇笔记 https://blog.52itstyle.vip
 * @author 小柒2012
 */
public interface OrderService {

    Result list(Order order);

    Order updateStatus(String orderNo);

}
