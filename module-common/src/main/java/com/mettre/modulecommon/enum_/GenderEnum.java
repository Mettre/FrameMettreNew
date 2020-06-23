package com.mettre.modulecommon.enum_;

public enum GenderEnum {

    MAN("男"), WOMAN("女");

    public String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static boolean contains(String gender) {
        for (GenderEnum typeEnum : GenderEnum.values()) {
            if (typeEnum.name().equals(gender)) {
                return true;
            }
        }
        return false;
    }
}
