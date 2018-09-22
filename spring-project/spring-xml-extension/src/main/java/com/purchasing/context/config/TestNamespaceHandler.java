package com.purchasing.context.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Administrator
 * @createTime 2018/9/22
 * [http://www.springframework.org/schema/context-test
 * org.springframework.beans.factory.xml.NamespaceHandler
 */
public class TestNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new TestBeanDefinitionParser());
    }
}
