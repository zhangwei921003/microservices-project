package com.purchasing;

import com.purchasing.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author zhangwei
 * @createTime 2018/9/26
 */
@ComponentScan(basePackages = "com.purchasing")
@EnableTransactionManagement//激活transaction
public class SpringApplicationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringApplicationTest.class);//扫描com.purchasing下的类信息
        context.refresh();
        context.getBeansOfType(EchoService.class).forEach((beanName,bean) ->{
            System.err.println("BeanName : "+beanName + " & Bean : "+bean);
            System.out.println("调用方法可得："+bean.echo(beanName));

        });
        context.close();
    }

    @Component(value = "echoTransactionManager")//指定了事务BeanName
    public static class MyPlatformTransactionManager implements PlatformTransactionManager {

        @Override
        public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
            return new  DefaultTransactionStatus(
                    null, true, true, transactionDefinition.isReadOnly(), true, null);
        }

        @Override
        public void commit(TransactionStatus transactionStatus) throws TransactionException {
            System.err.println("Commit ~~~"+transactionStatus.toString());
        }

        @Override
        public void rollback(TransactionStatus transactionStatus) throws TransactionException {
            System.err.println("Rollback ~~~"+transactionStatus.toString());
        }
    }
}
