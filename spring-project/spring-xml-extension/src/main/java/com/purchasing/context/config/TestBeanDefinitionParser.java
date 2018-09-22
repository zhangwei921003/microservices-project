package com.purchasing.context.config;


import com.purchasing.model.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Administrator
 * @createTime 2018/9/22
 */
public class TestBeanDefinitionParser implements BeanDefinitionParser {


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        //定义BeanDefinition，通过BeanDefinitionBuilder创建
        BeanDefinitionBuilder builder =BeanDefinitionBuilder.genericBeanDefinition();
        builder.getRawBeanDefinition().setBeanClass(User.class);
        //添加Bean对象属性
        builder.addPropertyValue("id",element.getAttribute("id"));
        builder.addPropertyValue("name",element.getAttribute("name"));
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        //注册BeanDefinition
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(beanDefinition, element.getAttribute("bean-name")), parserContext.getRegistry());
        return beanDefinition;
    }
}
