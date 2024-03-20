package com.machine.test.temp.qiyu.model.eo;

import lombok.Data;

/**
 * @author Qian.YongYan
 * @create 2024-03-14 10:43
 */
@Data
public class ComplaintEo {

    // 商户号
    private String merchantNumber;
    // 商户名称
    private String merchantName;
    // 交易订单号
    private String transactionOrderNumber;
    // 投诉单号
    private String complaintNumber;
    // 投诉人联系方式
    private String complainantContact;
    // 投诉内容
    private String complaintContent;
    // 投诉时间
    private String complaintDate;
    // 投诉来源
    private String complaintSource;
    // 投诉状态
    private String complaintStatus;

    public String buildContent() {
        return "商户号：" + (merchantNumber == null ? "无" : merchantNumber) + "\n" +
                "商户名称：" + (merchantName == null ? "无" : merchantName) + "\n" +
                "交易订单号：" + (transactionOrderNumber == null ? "无" : transactionOrderNumber) + "\n" +
                "投诉单号：" + (complaintNumber == null ? "无" : complaintNumber) + "\n" +
                "投诉人联系方式：" + (complainantContact == null ? "无" : complainantContact) + "\n" +
                "投诉内容：" + (complaintContent == null ? "无" : complaintContent) + "\n" +
                "投诉时间：" + (complaintDate == null ? "无" : complaintDate) + "\n" +
                "投诉来源：" + (complaintSource == null ? "无" : complaintSource) + "\n" +
                "投诉状态：" + (complaintStatus == null ? "无" : complaintStatus);
    }
}
