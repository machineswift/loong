package com.machine.app.manage.data.base.business;

import com.machine.app.manage.data.material.controller.request.LoongMaterialPermanentQueryPageRequest;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentDetailResponse;
import com.machine.app.manage.data.material.controller.response.LoongMaterialPermanentListResponse;
import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ILoongMaterialPermanentBusiness {

    String upload(DataMaterIalTypeEnum materIalType, MultipartFile file);

    LoongMaterialPermanentDetailResponse downloadFile(String id, HttpServletResponse response);

    LoongPageResponse<LoongMaterialPermanentListResponse> selectPage(LoongMaterialPermanentQueryPageRequest request);
}