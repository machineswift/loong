package com.machine.common.envm.data.material;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DragonDataMaterIalTypeEnum {
    TEXT("TEXT", "文本"),
    IMAGE("IMAGE", "图片"),
    VOICE("VOICE", "音频"),
    VIDEO("VIDEO", "视频"),
    DOCUMENT("DOCUMENT", "文档"),
    FILE("FILE", "文件");

    private final String code;
    private final String msg;
}
