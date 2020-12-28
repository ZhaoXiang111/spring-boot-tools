package com.tools.module.pay.service.impl;

import cn.hutool.core.util.IdUtil;
import com.tools.common.constant.SystemConstant;
import com.tools.common.dynamicquery.DynamicQuery;
import com.tools.common.model.Result;
import com.tools.common.util.DateUtils;
import com.tools.common.util.ShiroUtils;
import com.tools.module.pay.entity.Order;
import com.tools.module.pay.model.Product;
import com.tools.module.pay.service.AliPayService;
import com.tools.module.pay.util.PayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.path.PathException;

import java.math.BigDecimal;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private DynamicQuery dynamicQuery;
    @Autowired
    private PayUtils payUtils;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Result aliPay(Product product) {
        try {
            Long orderNo = IdUtil.getSnowflake(1,1).nextId();
            product.setOutTradeNo(orderNo+"");
            String result = payUtils.aliPay(product);
            Order order = new Order();
            order.setProductId(product.getProductId());
            order.setTotalFee(new BigDecimal(product.getTotalFee()));
            order.setStatus(SystemConstant.PAY_STATUS_NO);
            order.setType(SystemConstant.PAY_TYPE_ALI);
            order.setOrderNo(orderNo+"");
            order.setBody(product.getBody());
            order.setGmtCreate(DateUtils.getTimestamp());
            order.setGmtModified(order.getGmtCreate());
            order.setUserCreate(ShiroUtils.getUserId());
            dynamicQuery.save(order);
            return Result.ok(result);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
    }
}
