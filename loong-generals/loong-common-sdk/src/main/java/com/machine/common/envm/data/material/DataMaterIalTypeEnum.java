package com.machine.common.envm.data.material;

import com.machine.common.envm.LoongBaseEnum;
import com.machine.common.envm.LoongStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataMaterIalTypeEnum implements LoongBaseEnum<LoongStatusEnum, String> {
    TEXT("TEXT", "文本"),
    IMAGE("IMAGE", "图片"),
    VOICE("VOICE", "音频"),
    VIDEO("VIDEO", "视频"),
    DOCUMENT("DOCUMENT", "文档"),
    FILE("FILE", "文件");

    private final String code;
    private final String msg;
}
