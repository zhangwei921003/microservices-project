package com.purchasing.service;

import com.purchasing.annotation.TransactionService;

/**
 * @author zhangwei
 * @createTime 2018/9/26
 */
@TransactionService(value = "echoService-2018",txName = "echoTransactionManager")//使用指定的事务BeanName
public class EchoService {

    public String echo(String string){
        return string;
    }

}
