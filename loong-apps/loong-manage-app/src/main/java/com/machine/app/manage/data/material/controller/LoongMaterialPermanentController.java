package com.machine.app.manage.data.material.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.machine.app.manage.data.material.business.ILoongMaterialPermanentBusiness;
import com.machine.app.manage.data.material.controller.request.LoongMaterialPermanentQueryPageRequest;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentDetailResponse;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentListResponse;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Tag(name = "永久素材模块")
@RestController
@RequestMapping("data/material/permanent")
public class LoongMaterialPermanentController {

    @Autowired
    private ILoongMaterialPermanentBusiness materialPermanentBusiness;

    @PostMapping("upload")
    public String upload(@RequestParam("materIalType") DataMaterIalTypeEnum materIalType,
                         @RequestParam("file") MultipartFile file) {
        log.info("上传永久素材 materIalType:{} fileName:{} length:{}",
                materIalType, file.getOriginalFilename(), file.getSize());
        return materialPermanentBusiness.upload(materIalType,file);
    }

    @GetMapping("download")
    public LoongMaterialPermanentDetailResponse downloadFile(@RequestParam("id") String id,
                                                             HttpServletResponse response) {
        log.info("下载永久素材 id:{}", id);
        return materialPermanentBusiness.downloadFile(id,response);
    }

    @Operation(summary = "分页查询")
    @ApiOperationSupport(order = 50)
    @PostMapping("page")
    public LoongPageResponse<LoongMaterialPermanentListResponse> selectPage(@RequestBody LoongMaterialPermanentQueryPageRequest request) {
        return materialPermanentBusiness.selectPage(request);
    }

}