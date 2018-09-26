package com.purchasing.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @author zhangwei
 * @createTime 2018/9/26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service //它是Service组件
@Transactional //它也是事物注解
public @interface TransactionService {

    @AliasFor(annotation = Service.class,attribute = "value")
    String value() ;//Service是Component的派生注解，只认value属性值

    @AliasFor(annotation = Transactional.class,attribute = "value")
    String txName();
}
