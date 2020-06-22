package com.mettre.moduleclient.pojo;

import com.mettre.modulecommon.base.ResultList;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MonthAccount extends ResultList {

    public BigDecimal expenditure;//支出

    public BigDecimal income;//收人

    public MonthAccount(List data) {
        super(data);
    }

    public MonthAccount(List data, BigDecimal expenditure, BigDecimal income) {
        super(data);
        this.expenditure = expenditure == (null) ? new BigDecimal(0.00) : expenditure;
        this.income = income == (null) ? new BigDecimal(0.00) : income;
    }
}
