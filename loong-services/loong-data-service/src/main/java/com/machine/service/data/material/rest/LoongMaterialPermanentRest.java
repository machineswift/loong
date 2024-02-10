package com.machine.service.data.material.rest;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageResponse;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectLoongPageRequest;
import com.machine.service.data.material.rest.response.LoongMaterialPermanentDetailResponse;
import com.machine.service.data.material.rest.response.LoongMaterialPermanentPageResponse;
import com.machine.service.data.material.service.ILoongMaterialPermanentService;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialPermanentInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentPageOutBO;
import com.machine.starter.minio.LoongMinioUtil;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("material/permanent")
public class LoongMaterialPermanentRest {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private ILoongMaterialPermanentService materialPermanentService;


    @PostMapping("upload")
    public String upload(@RequestParam("materIalType") DataMaterIalTypeEnum materIalType,
                         @RequestParam("file") MultipartFile file) {
        log.info("上传永久素材 materIalType:{} fileName:{} length:{}",
                materIalType, file.getOriginalFilename(), file.getSize());
        LoongMaterialPermanentInsertInBO insertInDTO = new LoongMaterialPermanentInsertInBO();
        insertInDTO.setType(materIalType);
        insertInDTO.setLength(file.getSize());
        insertInDTO.setName(file.getOriginalFilename());
        insertInDTO.setUrl(LoongMinioUtil.uploadFilePermanentMinio(minioClient, file));
        return materialPermanentService.insert(insertInDTO);
    }

    @GetMapping("download")
    public LoongMaterialPermanentDetailResponse downloadFile(@RequestParam("id") String id,
                                                             HttpServletResponse response) {
        log.info("下载永久素材 id:{}", id);
        LoongMaterialPermanentDetailOutBO detailOutBO = materialPermanentService.selectById(id);
        if (null == detailOutBO) {
            return null;
        }

        try {
            GetObjectResponse objectResponse = LoongMinioUtil.getPermanentMinioClient(minioClient)
                    .getObject(GetObjectArgs.builder()
                            .bucket(LoongMinioUtil.PERMANENT_BUCKET_NAME)
                            .object(detailOutBO.getUrl())
                            .build());
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(detailOutBO.getLength()));
            response.setHeader("Content-disposition", "attachment; filename=" +
                    new String(detailOutBO.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
            outputStream.write(objectResponse.readAllBytes());
            outputStream.flush();
        } catch (Exception e) {
            log.error(String.format("下载永久素材异常,id:%s", id), e);
            throw new RuntimeException(String.format("下载永久素材异常,id:%s", id), e);
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutBO), LoongMaterialPermanentDetailResponse.class);
    }

    @PostMapping("selectPage")
    public LoongPageResponse<LoongMaterialPermanentPageResponse> selectPage(@RequestBody LoongMaterialPermanentSelectLoongPageRequest request) {
        Page<LoongMaterialPermanentPageOutBO> outBOPage = materialPermanentService.selectPage(request);
        return new LoongPageResponse<>(outBOPage.getCurrent(), outBOPage.getSize(), outBOPage.getTotal(), JSONUtil.toList(JSONUtil.toJsonStr(outBOPage.getRecords()), LoongMaterialPermanentPageResponse.class));
    }

}
