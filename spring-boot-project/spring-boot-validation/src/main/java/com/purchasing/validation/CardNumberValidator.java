package com.purchasing.validation;

import com.purchasing.validation.constraints.CardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author zhangwei
 * @createTime 2018/9/19
 */
public class CardNumberValidator  implements ConstraintValidator<CardNumber,String> {

    /**
     * 初始化参数，例如@CardNumber中有value()属性等，可以通过此方法传递
     * 以便校验处理
     * @param constraintAnnotation
     */
    public void initialize(CardNumberValidator constraintAnnotation) {

    }

    /**
     * 规则：
     *① 以“CARD”开头
     *② 以数字结尾
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        /**
         * 注意常见的拆分字符串有哪些？
         *  1） Spring 自带split() {@link String#split(String } 不建议使用：该方法使用了正则表达式，其次是NPE保护不够
         *  2 )  JDK StringTokenizer  {@link java.util.StringTokenizer} （不足类似于枚举 Enumeration API）
         *  3） Apache Commons Lang {@link StringUtils#split(String)}} 如果传入的String是null，返回也是null
         *  4）Spring {@link org.springframework.util.StringUtils#delimitedListToStringArray(String, String)} 如果如果传入的String是null，返回空的数组
         */
        String[] parts = StringUtils.split(value,"-");
        if(ArrayUtils.getLength(parts) !=2){
            return false;
        }
        String prefix = parts[0];
        String suffix = parts[1];

        boolean isValidPrefix = Objects.equals(prefix,"CARD");
        boolean isValidSuffix = StringUtils.isNumeric(suffix);

        return isValidPrefix && isValidSuffix;
    }
}
