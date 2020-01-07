package com.mettre.modulecommon.resultUtil;

import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.enums.CustomerException;

public class ResultUtils {

    public static <T> Boolean getInfoSuccess(Result<T> result) {
        if (result.getCode() != 0) {
            throw new CustomerException(result.getMsg());
        }
        return true;
    }

}
