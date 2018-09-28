package com.purchasing.annotation.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 *
 * 无论是Class, Constructor, Field, Method, and Package
 * 以上方式获取到注解的属性值如何作用到对应的内容上？
 * @author Administrator
 * @createTime 2018/9/28
 */
public class UseCustomAnnotation {

    public static void main(String [] args) throws NoSuchMethodException {
        Class<SetCustomAnnotation> classObject = SetCustomAnnotation.class;
        System.out.println(classObject.toGenericString());
        readAnnotation(classObject);
        Method method = classObject.getMethod("say",null);
        readMethodAnnotation(method);
    }

    static void readMethodAnnotation(AnnotatedElement element){
        if(element.isAnnotationPresent(TestDemo.class)){
            Annotation methodAnnotation = element.getAnnotation(TestDemo.class);
            TestDemo testDemo = (TestDemo) methodAnnotation;
            System.out.println(testDemo.value());
        }
    }

    static void readAnnotation(AnnotatedElement element) {
        try {
            System.out.println("Annotation element values: \n");
            if (element.isAnnotationPresent(TypeHeader.class)) {
                // getAnnotation returns Annotation type
                Annotation singleAnnotation = element.getAnnotation(TypeHeader.class);
                TypeHeader header = (TypeHeader) singleAnnotation;
                System.out.println("Developer: " + header.developer());
                System.out.println("Last Modified: " + header.lastModified());
                // teamMembers returned as String []
                System.out.print("Team members: ");
                for (String member : header.teamMembers()){
                    System.out.print(member + ", ");
                    System.out.print("\n");
                }
                System.out.println("Meaning of Life: "+ header.meaningOfLife());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
