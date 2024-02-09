package com.machine.service.data.material.rest;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.model.PageResponse;
import com.machine.service.data.material.rest.request.DragonMaterialTemporarySelectPageRequest;
import com.machine.service.data.material.rest.response.DragonMaterialTemporaryPageResponse;
import com.machine.service.data.material.service.IDragonMaterialTemporaryService;
import com.machine.service.data.material.service.bo.outbo.DragonMaterialTemporaryPageOutBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("material/temporary")
public class DragonMaterialTemporaryRest {


    @Autowired
    private IDragonMaterialTemporaryService dragonMaterialTemporaryService;

    @GetMapping("test")
    public String test() {
        Integer a = 0;
        if (a == 0) {
            a = null;
        }

        return a.toString();
    }

    @PostMapping("selectPage")
    public PageResponse<DragonMaterialTemporaryPageResponse> selectPage(@RequestBody DragonMaterialTemporarySelectPageRequest request) {
        Page<DragonMaterialTemporaryPageOutBO> outBOPage = dragonMaterialTemporaryService.selectPage(request);

        return new PageResponse<>(
                outBOPage.getCurrent(),
                outBOPage.getSize(),
                outBOPage.getTotal(),
                JSONUtil.toList(JSONUtil.toJsonStr(outBOPage.getRecords()), DragonMaterialTemporaryPageResponse.class));
    }

}
