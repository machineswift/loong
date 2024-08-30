package com.machine.app.manage.wx.cp.business;

public interface ILoongWxCpAuthBusiness {

    String authGet(String corpId,
                   Integer agentId,
                   String signature,
                   String timestamp,
                   String nonce,
                   String echoStr);
}
