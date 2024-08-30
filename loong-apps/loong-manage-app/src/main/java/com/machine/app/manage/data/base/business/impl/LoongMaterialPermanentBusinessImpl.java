package com.machine.app.manage.data.base.business.impl;

import cn.hutool.json.JSONUtil;
import com.machine.app.manage.data.base.business.ILoongMaterialPermanentBusiness;
import com.machine.app.manage.data.material.controller.request.LoongMaterialPermanentQueryPageRequest;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentDetailResponse;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentListResponse;
import com.machine.client.data.material.ILoongMaterialPermanentClient;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialPermanentDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentListOutputDto;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageResponse;
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
public class LoongMaterialPermanentBusinessImpl implements ILoongMaterialPermanentBusiness {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ILoongMaterialPermanentClient materialPermanentClient;


    @Override
    public String upload(DataMaterIalTypeEnum materIalType, MultipartFile file) {
        LoongMaterialPermanentCreateInputDto inputDto = new LoongMaterialPermanentCreateInputDto();
        inputDto.setType(materIalType);
        inputDto.setLength(file.getSize());
        inputDto.setName(file.getOriginalFilename());
        inputDto.setUrl(LoongMinioUtil.uploadFilePermanentMinio(minioClient, file));
        return materialPermanentClient.create(inputDto);
    }

    @Override
    public LoongMaterialPermanentDetailResponse downloadFile(String id, HttpServletResponse response) {
        LoongMaterialPermanentDetailOutputDto detailOutputDto = materialPermanentClient.detail(id);
        if (null == detailOutputDto) {
            return null;
        }

        try {
            GetObjectResponse objectResponse = LoongMinioUtil.getPermanentMinioClient(minioClient)
                    .getObject(GetObjectArgs.builder()
                            .bucket(LoongMinioUtil.PERMANENT_BUCKET_NAME)
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
            log.error(String.format("下载永久素材异常,id:%s", id), e);
            throw new RuntimeException(String.format("下载永久素材异常,id:%s", id), e);
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutputDto), LoongMaterialPermanentDetailResponse.class);
    }

    @Override
    public LoongPageResponse<LoongMaterialPermanentListResponse> selectPage(LoongMaterialPermanentQueryPageRequest request) {
        LoongMaterialPermanentQueryPageInputVo inputVo = JSONUtil.toBean(JSONUtil.toJsonStr(request), LoongMaterialPermanentQueryPageInputVo.class);
        LoongPageResponse<LoongMaterialPermanentListOutputDto> page = materialPermanentClient.selectPage(inputVo);
        return new LoongPageResponse<>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                JSONUtil.toList(JSONUtil.toJsonStr(page.getRecords()), LoongMaterialPermanentListResponse.class));
    }
}