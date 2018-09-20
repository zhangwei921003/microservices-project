package com.purchasing.model;

import com.purchasing.validation.constraints.CardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author zhangwei
 * @createTime 2018/9/19
 */
public class Staff {

    @Max(value = 1000)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @CardNumber
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
