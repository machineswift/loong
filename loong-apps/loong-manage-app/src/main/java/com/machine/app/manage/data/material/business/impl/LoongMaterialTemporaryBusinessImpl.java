package com.machine.app.manage.data.material.business.impl;

import cn.hutool.json.JSONUtil;
import com.machine.app.manage.data.material.business.ILoongMaterialTemporaryBusiness;
import com.machine.app.manage.data.material.controller.response.LoongMaterialTemporaryDetailResponse;
import com.machine.client.data.material.ILoongMaterialTemporaryClient;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.starter.minio.LoongMinioUtil;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoongMaterialTemporaryBusinessImpl implements ILoongMaterialTemporaryBusiness {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ILoongMaterialTemporaryClient materialTemporaryClient;

    @Override
    public String upload(DataMaterIalTypeEnum materIalType,
                         MultipartFile file) {
        LoongMaterialTemporaryCreateInputDto inputDto = new LoongMaterialTemporaryCreateInputDto();
        inputDto.setType(materIalType);
        inputDto.setLength(file.getSize());
        inputDto.setName(file.getOriginalFilename());
        inputDto.setUrl(LoongMinioUtil.uploadFileTemporaryMinio(minioClient, file));
        return materialTemporaryClient.create(inputDto);
    }

    @Override
    public LoongMaterialTemporaryDetailResponse downloadFile(String id,
                                                             HttpServletResponse response) {
        LoongMaterialTemporaryDetailOutputDto detailOutputDto = materialTemporaryClient.detail(id);
        if (null == detailOutputDto) {
            return null;
        }

        try {
            GetObjectResponse objectResponse = LoongMinioUtil.getTemporaryMinioClient(minioClient)
                    .getObject(GetObjectArgs.builder()
                            .bucket(LoongMinioUtil.TEMPORARY_BUCKET_NAME)
                            .object(detailOutputDto.getUrl())
                            .build());
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(detailOutputDto.getLength()));
            response.setHeader("Content-disposition", "attachment; filename=" +
                    new String(detailOutputDto.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1"));

            byte[] result = new byte[detailOutputDto.getLength().intValue()];
            objectResponse.read(result);
            outputStream.write(result);
            outputStream.flush();
        } catch (Exception e) {
            log.error(String.format("下载临时素材异常,id:%s", id), e);
            throw new RuntimeException(String.format("下载临时素材异常,id:%s", id), e);
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutputDto), LoongMaterialTemporaryDetailResponse.class);
    }
}