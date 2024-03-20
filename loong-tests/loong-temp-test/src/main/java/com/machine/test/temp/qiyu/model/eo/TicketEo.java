package com.machine.test.temp.qiyu.model.eo;

import lombok.Data;

import java.util.List;

/**
 * @author Qian.YongYan
 * @create 2024-03-13 16:43
 */
@Data
public class TicketEo {

    // 工单标题，不超过100个字符
    private String title;

    // 开发者的应用里的用户ID，不超过64个字符(如果没有userMobile则必传)
    private String uid;

    // 分类ID，0表示未分类
    private Integer typeId;

    // 工单内容，不超过3000个字符
    private String content;

    // 用户姓名，不超过128个字符
    private String userName;

    // 用户联系方式，不超过128个字符(如果没有uid则必传)
    private String userMobile;

    // 用户联系邮箱，不超过255个字符 (当fromType=8的时候则必传)
    private String userEmail;

    // 收件人邮箱
    private String toEmail;

    // 指定客服分组ID(开启自动流转请勿传此字段)
    private Integer targetGroupId;
    //private Integer groupId;

    // 托管客服ID (当fromType=8的时候则不是必传)
    private String staffId;

    // 优先级,2=低;5=一般;8=紧急;10=非常紧急
    private Integer priority;

    // 模板ID
    private Integer templateId;

    // 关联会话ID（七鱼在线或呼叫系统的会话ID）
    private String connectionId;

    // 工单关注人
    private List<String> followerIds;

    // 附件列表
    private List<Attachment> attachments;

    // 自定义字段
    private List<CustomField> customFields;

    // 附加属性对，json格式，不超过1024个字符
    private String properties;

    // 工单类型 8=邮件工单，不传则是普通工单
    private Integer fromType;

    @Data
    public static class Attachment {
        // 附件的文件名，不超过128个字符
        private String fileName;

        // 附件的类型，1=文件的Base64，目前仅支持此类型
        private Integer type;

        // 对应type的内容
        private String payload;
    }

    @Data
    public static class CustomField {
        // 自定义字段的ID，对应获取工单模板字段接口返回的fieldId字段
        private Integer id;

        // 自定义字段的值 如果是级联字段，需要按顺序用英文逗号,分割传入子集的ID。看入参示例入参。子集ID从[获取级联字段子字段详细信息]接口获取
        private String value;
    }
}
