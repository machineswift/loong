package com.machine.service.data.material.rest;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageResponse;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectLoongPageRequest;
import com.machine.service.data.material.rest.response.LoongMaterialTemporaryDetailResponse;
import com.machine.service.data.material.rest.response.LoongMaterialTemporaryPageResponse;
import com.machine.service.data.material.service.ILoongMaterialTemporaryService;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialTemporaryInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryPageOutBO;
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
@RequestMapping("material/temporary")
public class LoongMaterialTemporaryRest {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ILoongMaterialTemporaryService materialTemporaryService;

    @PostMapping("upload")
    public String upload(@RequestParam("materIalType") DataMaterIalTypeEnum materIalType,
                         @RequestParam("file") MultipartFile file) {
        log.info("上传临时素材 materIalType:{} fileName:{} length:{}",
                materIalType, file.getOriginalFilename(), file.getSize());
        LoongMaterialTemporaryInsertInBO insertInDTO = new LoongMaterialTemporaryInsertInBO();
        insertInDTO.setType(materIalType);
        insertInDTO.setLength(file.getSize());
        insertInDTO.setName(file.getOriginalFilename());
        insertInDTO.setUrl(LoongMinioUtil.uploadFileTemporaryMinio(minioClient, file));
        return materialTemporaryService.insert(insertInDTO);
    }

    @GetMapping("download")
    public LoongMaterialTemporaryDetailResponse downloadFile(@RequestParam("id") String id,
                                                             HttpServletResponse response) {
        log.info("下载临时素材 id:{}", id);
        LoongMaterialTemporaryDetailOutBO detailOutBO = materialTemporaryService.selectById(id);
        if (null == detailOutBO) {
            return null;
        }

        try {
            GetObjectResponse objectResponse = LoongMinioUtil.getTemporaryMinioClient(minioClient)
                    .getObject(GetObjectArgs.builder()
                            .bucket(LoongMinioUtil.TEMPORARY_BUCKET_NAME)
                            .object(detailOutBO.getUrl())
                            .build());
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(detailOutBO.getLength()));
            response.setHeader("Content-disposition", "attachment; filename=" +
                    new String(detailOutBO.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));

            byte[] result = new byte[detailOutBO.getLength().intValue()];
            objectResponse.read(result);
            outputStream.write(result);
            outputStream.flush();
        } catch (Exception e) {
            log.error(String.format("下载临时素材异常,id:%s", id), e);
            throw new RuntimeException(String.format("下载临时素材异常,id:%s", id), e);
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutBO), LoongMaterialTemporaryDetailResponse.class);
    }

    @PostMapping("selectPage")
    public LoongPageResponse<LoongMaterialTemporaryPageResponse> selectPage(@RequestBody LoongMaterialTemporarySelectLoongPageRequest request) {
        Page<LoongMaterialTemporaryPageOutBO> outBOPage = materialTemporaryService.selectPage(request);
        return new LoongPageResponse<>(outBOPage.getCurrent(), outBOPage.getSize(), outBOPage.getTotal(), JSONUtil.toList(JSONUtil.toJsonStr(outBOPage.getRecords()), LoongMaterialTemporaryPageResponse.class));
    }
}
