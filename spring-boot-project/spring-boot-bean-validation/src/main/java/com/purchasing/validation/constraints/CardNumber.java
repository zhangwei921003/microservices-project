package com.purchasing.validation.constraints;

import com.purchasing.validation.CardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhangwei
 * @createTime 2018/9/19
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CardNumberValidator.class})
public @interface CardNumber {

    String message() default "{com.purchasing.model.validation.card.number.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
