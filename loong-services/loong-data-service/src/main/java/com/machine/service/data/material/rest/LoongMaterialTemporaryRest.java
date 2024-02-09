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
import io.minio.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("material/temporary")
public class LoongMaterialTemporaryRest {

    private final static String BUCKET_NAME = "loong-temporary";

    @Value("${loong.minio.url}")
    private String minioUrl;

    @Value("${loong.minio.accessKey}")
    private String minioAccessKey;

    @Value("${loong.minio.secretKey}")
    private String minioSecretKey;

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
        insertInDTO.setUrl(uploadFileTMinio(getTemporaryMinioClient(), file));
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
            GetObjectResponse objectResponse = getTemporaryMinioClient().getObject(GetObjectArgs.builder()
                    .bucket(BUCKET_NAME)
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


    @SneakyThrows
    private MinioClient getTemporaryMinioClient() {
        if (null != this.minioClient) {
            return minioClient;
        }
        MinioClient minioClient = MinioClient.
                builder()
                .endpoint(minioUrl)
                .credentials(minioAccessKey, minioSecretKey)
                .build();

        BucketExistsArgs bucketExistsArgs = BucketExistsArgs
                .builder()
                .bucket(BUCKET_NAME)
                .build();

        boolean isExist = minioClient.bucketExists(bucketExistsArgs);
        if (!isExist) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs
                    .builder()
                    .bucket(BUCKET_NAME)
                    .build();
            minioClient.makeBucket(makeBucketArgs);
        }
        this.minioClient = minioClient;
        return minioClient;
    }


    @SneakyThrows
    private String uploadFileTMinio(MinioClient temporaryMinioClient,
                                    MultipartFile multipartFile) {
        //重新命名文件名字
        String objectName = UUID.randomUUID().toString()
                .replace("-", "") +
                "-" + multipartFile.getOriginalFilename();
        // 将MultipartFile转换为InputStream
        InputStream inputStream = multipartFile.getInputStream();

        // 上传对象到MinIO
        ObjectWriteResponse objectWriteResponse = temporaryMinioClient.putObject(PutObjectArgs
                .builder()
                .bucket(BUCKET_NAME)
                .object(objectName)
                .stream(inputStream, multipartFile.getSize(), -1)
                .build());

        // 关闭InputStream
        inputStream.close();
        return objectWriteResponse.object();
    }
}
