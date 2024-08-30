package com.machine.app.manage.data.base.business;

import com.machine.app.manage.data.material.controller.response.LoongMaterialTemporaryDetailResponse;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ILoongMaterialTemporaryBusiness {

    String upload(DataMaterIalTypeEnum materIalType, MultipartFile file);

    LoongMaterialTemporaryDetailResponse downloadFile(String id, HttpServletResponse response);
}