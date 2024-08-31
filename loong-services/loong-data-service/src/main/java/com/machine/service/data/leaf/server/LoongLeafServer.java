package com.machine.service.data.leaf.server;

import com.machine.client.data.leaf.ILoongLeafClient;
import com.machine.service.data.leaf.service.ILoongLeafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/leaf")
public class LoongLeafServer implements ILoongLeafClient {

    @Autowired
    private ILoongLeafService leafService;

    @GetMapping("hykh")
    public String getHykh() {
        return leafService.getKqBatchNo("HYKH", "会员卡号");
    }


}
