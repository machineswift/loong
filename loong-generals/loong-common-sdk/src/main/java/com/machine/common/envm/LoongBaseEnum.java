package com.machine.common.envm;

public interface LoongBaseEnum<T extends Enum<T>, E> {

    E getCode();

    String getMsg();

    /**
     * 根据Code获取对应IBaseEnum实例
     *
     * @param clazz 枚举类
     * @param code  编码
     * @param <T>   枚举类
     * @return 枚举类实例
     */
    default <T extends Enum<T>> T getInstance(Class<T> clazz, E code) {
        if (!clazz.isEnum() || !LoongBaseEnum.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("未知的枚举编码：" + code);
        }
        T[] enumConstants = clazz.getEnumConstants();
        if (enumConstants != null) {
            for (T enumConstant : enumConstants) {
                LoongBaseEnum<?, ?> baseEnum = (LoongBaseEnum<?, ?>) enumConstant;
                if (baseEnum.getCode().equals(code)) {
                    return enumConstant;
                }
            }
        }
        throw new IllegalArgumentException("未知的枚举编码：" + code);
    }

}
