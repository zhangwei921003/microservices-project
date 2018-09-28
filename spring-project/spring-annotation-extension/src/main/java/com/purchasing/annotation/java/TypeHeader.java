package com.purchasing.annotation.java;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Administrator
 * @createTime 2018/9/28
 */
// This is the annotation to be processed
// Default for Target is all Java Elements
// Change retention policy to RUNTIME (default is CLASS)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeHeader {
    // Default value specified for developer attribute
    String developer() default "Unknown";
    String lastModified();
    String [] teamMembers();
    int meaningOfLife();
}
