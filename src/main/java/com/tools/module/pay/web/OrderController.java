package com.tools.module.pay.web;

import com.tools.common.config.AbstractController;
import com.tools.common.model.Result;
import com.tools.module.pay.entity.Order;
import com.tools.module.pay.service.OrderService;
import com.tools.module.pay.util.PayUtils;
import com.yungouos.pay.util.PaySignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单管理
 */
@RestController
@RequestMapping("/order")
public class OrderController extends AbstractController {

    @Autowired
    private PayUtils payUtils;
    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param order
     * @return
     */
    @PostMapping(value="list")
    public Result list(Order order){
        return orderService.list(order);
    }

    /**
     * 微信支付异步回调
     * @param request
     * @return
     */
    @RequestMapping(value="wxNotify")
    public String wxNotify(HttpServletRequest request) {
        try {
            String key = payUtils.getWxPayProperties().getKey();
            boolean flag = PaySignUtil.checkNotifySign(request,key);
            if(flag){
                String outTradeNo = request.getParameter("outTradeNo");
                logger.info("商户订单号为：{}",outTradeNo);
                orderService.updateStatus(outTradeNo);
                return "SUCCESS";
            }else{
                return "ERROR";
            }
        } catch (Exception e) {
            logger.error("回调验证失败：{}",e.getMessage());
            return "ERROR";
        }
    }

    /**
     * 支付宝支付异步回调
     * @param request
     * @return
     */
    @RequestMapping(value="aliNotify")
    public String aliNotify(HttpServletRequest request) {
        try {
            String key = payUtils.getAliPayProperties().getKey();
            boolean flag = PaySignUtil.checkNotifySign(request,key);
            if(flag){
                String outTradeNo = request.getParameter("outTradeNo");
                logger.info("商户订单号为：{}",outTradeNo);
                orderService.updateStatus(outTradeNo);
                return "SUCCESS";
            }else{
                return "ERROR";
            }
        } catch (Exception e) {
            logger.error("回调验证失败：{}",e.getMessage());
            return "ERROR";
        }
    }
}
