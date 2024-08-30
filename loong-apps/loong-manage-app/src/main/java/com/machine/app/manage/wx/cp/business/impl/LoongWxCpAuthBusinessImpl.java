package com.machine.app.manage.wx.cp.business.impl;

import com.machine.app.manage.wx.cp.business.ILoongWxCpAuthBusiness;
import com.machine.wechat.cp.config.mutil.WxCpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class LoongWxCpAuthBusinessImpl implements ILoongWxCpAuthBusiness {

    @Override
    public String authGet(String corpId,
                          Integer agentId,
                          String signature,
                          String timestamp,
                          String nonce,
                          String echoStr) {
        final WxCpService wxCpService = WxCpConfiguration.getCpService(corpId, agentId);
        if (wxCpService == null) {
            throw new IllegalArgumentException(String.format("未找到对应agentId=[%d]的配置，请核实！", agentId));
        }

        if (wxCpService.checkSignature(signature, timestamp, nonce, echoStr)) {
            return new WxCpCryptUtil(wxCpService.getWxCpConfigStorage()).decrypt(echoStr);
        }
        return "非法请求";
    }
}
