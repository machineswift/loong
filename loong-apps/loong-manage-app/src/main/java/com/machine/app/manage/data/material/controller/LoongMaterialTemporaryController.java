package com.machine.app.manage.data.material.controller;

import com.machine.app.manage.data.material.business.ILoongMaterialTemporaryBusiness;
import com.machine.app.manage.data.material.controller.response.LoongMaterialTemporaryDetailResponse;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "临时素材模块")
@RestController
@RequestMapping("material-temporary")
public class LoongMaterialTemporaryController {

//    @Autowired
//    private ILoongMaterialTemporaryBusiness materialTemporaryBusiness;
//
//    @PostMapping("upload")
//    public String upload(@RequestParam("materIalType") DataMaterIalTypeEnum materIalType,
//                         @RequestParam("file") MultipartFile file) {
//        log.info("上传临时素材 materIalType:{} fileName:{} length:{}",
//                materIalType, file.getOriginalFilename(), file.getSize());
//        return materialTemporaryBusiness.upload(materIalType,file);
//    }
//
//    @GetMapping("download")
//    public LoongMaterialTemporaryDetailResponse downloadFile(@RequestParam("id") String id,
//                                                             HttpServletResponse response) {
//        log.info("下载临时素材 id:{}", id);
//        return materialTemporaryBusiness.downloadFile(id,response);
//    }
}