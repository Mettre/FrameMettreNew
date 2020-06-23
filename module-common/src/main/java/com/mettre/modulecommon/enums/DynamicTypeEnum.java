package com.mettre.modulecommon.enums;

public enum DynamicTypeEnum {

    NEWS("新闻"), MOMENTS("说说"), FORUM("广场");

    public String dynamicType;

    DynamicTypeEnum(String dynamicType) {
        this.dynamicType = dynamicType;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static boolean contains(String dynamicType) {
        for (DynamicTypeEnum typeEnum : DynamicTypeEnum.values()) {
            if (typeEnum.name().equals(dynamicType)) {
                return true;
            }
        }
        return false;
    }
}
