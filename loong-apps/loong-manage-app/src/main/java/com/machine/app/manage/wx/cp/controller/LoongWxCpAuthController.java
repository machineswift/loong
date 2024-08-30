package com.machine.app.manage.wx.cp.controller;

import com.honey.web.manage.wxcp.fade.HoneyWxCpAuthFade;
import com.machine.app.manage.wx.cp.business.ILoongWxCpAuthBusiness;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("wx/cp/auth")
public class LoongWxCpAuthController {

    @Autowired
    public ILoongWxCpAuthBusiness wxCpAuthBusiness;

    @GetMapping(path = "authGet", produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "corpId") String corpId,
                          @RequestParam(name = "agentId") Integer agentId,
                          @RequestParam(name = "msg_signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echoStr) {
        log.info("接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
                signature, timestamp, nonce, echoStr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echoStr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        return wxCpAuthBusiness.authGet(corpId, agentId, signature, timestamp, nonce, echoStr);
    }
}
