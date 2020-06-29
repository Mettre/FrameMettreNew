package com.mettre.moduleclient.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mettre.modulecommon.util.ListUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AccountList {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDay;

    private BigDecimal expenditureDay;

    private BigDecimal incomeDay;

    private List<AccountBean> accountBeans;

    public AccountList() {
    }

    public AccountList(List<AccountBean> accountBeans, Date recordDay) {
        this.expenditureDay = addType0(accountBeans);
        this.incomeDay = addType1(accountBeans);
        this.accountBeans = accountBeans;
        this.recordDay = recordDay;
    }


    public BigDecimal addType0(List<AccountBean> accountBeans) {
        if (ListUtils.isEmpty(accountBeans)) {
            return new BigDecimal("0.00");
        } else {
            BigDecimal bigDecimal = new BigDecimal("0.00");
            for (AccountBean accountBean : accountBeans) {
                if (accountBean.getType() == 0) {
                    bigDecimal = bigDecimal.add(accountBean.getAmount());
                }
            }
            System.out.println(bigDecimal + " -----------------------------");
            return bigDecimal;
        }
    }

    public BigDecimal addType1(List<AccountBean> accountBeans) {
        if (ListUtils.isEmpty(accountBeans)) {
            return new BigDecimal("0.00");
        } else {
            BigDecimal bigDecimal = new BigDecimal("0.00");
            for (AccountBean accountBean : accountBeans) {
                if (accountBean.getType() == 1) {
                    bigDecimal = bigDecimal.add(accountBean.getAmount());
                }
            }
            System.out.println(bigDecimal + " -----------------------------");
            return bigDecimal;
        }
    }
}
