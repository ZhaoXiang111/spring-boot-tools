package com.tools.module.pay.service;

import com.tools.common.model.Result;
import com.tools.module.pay.model.Product;

/**
 * 支付宝支付
 * 爪哇笔记 https://blog.52itstyle.vip
 * @author 小柒2012
 */
public interface AliPayService {

    Result aliPay(Product product);

}
