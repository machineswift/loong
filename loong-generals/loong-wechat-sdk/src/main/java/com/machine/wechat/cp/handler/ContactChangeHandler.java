package com.machine.wechat.cp.handler;

import com.machine.wechat.cp.builder.TextBuilder;
import com.machine.wechat.cp.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通讯录变更事件处理器.
 */
@Slf4j
@Component
public class ContactChangeHandler extends AbstractHandler {

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                    WxSessionManager sessionManager) {
        String content = "收到通讯录变更事件，内容：" + JsonUtils.toJson(wxMessage);
        log.info(content);

        return new TextBuilder().build(content, wxMessage, cpService);
    }

}
