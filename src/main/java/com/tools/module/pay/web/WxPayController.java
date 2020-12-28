package com.tools.module.pay.web;

import com.tools.common.config.AbstractController;
import com.tools.common.model.Result;
import com.tools.module.pay.model.Product;
import com.tools.module.pay.service.WxPayService;
import com.yungouos.pay.entity.WxOauthInfo;
import com.yungouos.pay.wxpay.WxPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 个人微信支付
 * 爪哇笔记 https://blog.52itstyle.vip
 * @author 小柒2012
 */
@RestController
@RequestMapping("/pay")
public class WxPayController extends AbstractController {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 生成支付二维码
     * @return
     */
    @PostMapping(value="wxPay")
    public Result wxPay(Product product) {
        return wxPayService.wxPay(product);
    }

    /**
     * 获取授权链接
     * @return
     */
    @GetMapping(value="getOauthUrl")
    public void getOauthUrl(HttpServletResponse response) throws IOException {
        /**
         * 这里改成自己的商品回调地址
         */
        String url="https://pay.xxxxx.vip/pay/weiXin.html?a=1";
        String oauthUrl = WxPay.getWxOauthUrl(null, url);
        response.sendRedirect(oauthUrl);
    }

    /**
     * 查询授权信息
     * @param code
     * @return
     */
    @PostMapping(value="getWxOauthInfo")
    public Result getWxOauthInfo(String code) {
        WxOauthInfo wxOauthInfo = WxPay.getWxOauthInfo(code);
        return Result.ok(wxOauthInfo);
    }

    /**
     * 公众号支付获取
     * @return
     */
    @PostMapping(value="jsApiPay")
    public Result jsApiPay(Product product) {
        return wxPayService.jsApiPay(product);
    }

}
