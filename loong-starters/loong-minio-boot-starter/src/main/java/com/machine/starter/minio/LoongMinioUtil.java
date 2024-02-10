package com.machine.starter.minio;

import io.minio.*;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class LoongMinioUtil {

    public final static String PERMANENT_BUCKET_NAME = "loong-permanent";

    public final static String TEMPORARY_BUCKET_NAME = "loong-temporary";

    private static MinioClient minioClient;

    public static MinioClient getPermanentMinioClient(MinioClient minioClient) {
        return getMinioClient(PERMANENT_BUCKET_NAME, minioClient);
    }

    public static MinioClient getTemporaryMinioClient(MinioClient minioClient) {
        return getMinioClient(TEMPORARY_BUCKET_NAME, minioClient);
    }

    public static String uploadFilePermanentMinio(MinioClient minioClient,
                                                  MultipartFile multipartFile) {
        return uploadFileMinio(PERMANENT_BUCKET_NAME, minioClient, multipartFile);
    }

    public static String uploadFileTemporaryMinio(MinioClient minioClient,
                                                  MultipartFile multipartFile) {
        return uploadFileMinio(TEMPORARY_BUCKET_NAME, minioClient, multipartFile);
    }


    @SneakyThrows
    private static String uploadFileMinio(String bucketName,
                                          MinioClient minioClient,
                                          MultipartFile multipartFile) {
        //重新命名文件名字
        String objectName = UUID.randomUUID().toString()
                .replace("-", "") +
                "-" + multipartFile.getOriginalFilename();
        // 将MultipartFile转换为InputStream
        InputStream inputStream = multipartFile.getInputStream();

        // 上传对象到MinIO
        ObjectWriteResponse objectWriteResponse = getMinioClient(bucketName, minioClient).putObject(PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(inputStream, multipartFile.getSize(), -1)
                .build());

        // 关闭InputStream
        inputStream.close();
        return objectWriteResponse.object();
    }

    @SneakyThrows
    private static MinioClient getMinioClient(String bucketName,
                                              MinioClient minioClient) {
        if (null != LoongMinioUtil.minioClient) {
            return LoongMinioUtil.minioClient;
        }

        synchronized (LoongMinioUtil.class) {
            if (null != LoongMinioUtil.minioClient) {
                return LoongMinioUtil.minioClient;
            }

            BucketExistsArgs bucketExistsArgs = BucketExistsArgs
                    .builder()
                    .bucket(bucketName)
                    .build();

            boolean isExist = minioClient.bucketExists(bucketExistsArgs);
            if (!isExist) {
                MakeBucketArgs makeBucketArgs = MakeBucketArgs
                        .builder()
                        .bucket(bucketName)
                        .build();
                minioClient.makeBucket(makeBucketArgs);
            }
            LoongMinioUtil.minioClient = minioClient;
            return minioClient;
        }
    }
}
