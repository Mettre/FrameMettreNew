package com.mettre.modulecommon.base;


import com.mettre.modulecommon.enum_.ResultEnum;
import com.mettre.modulecommon.enums.CustomerException;

public class ReturnType {

    public static int ReturnType(int type, ResultEnum resultEnum) {
        if (type < 1) {
            throw new CustomerException(resultEnum);
        }
        return type;
    }

    public static int ReturnType(int type, String errMas) {
        if (type < 1) {
            throw new CustomerException(errMas);
        }
        return type;
    }

    public static int ReturnType(String errMas) {
        throw new CustomerException(errMas);
    }


    public static Object ReturnType(Object object, ResultEnum resultEnum) {
        if (object == null) {
            throw new CustomerException(resultEnum);
        }
        return object;
    }
}
