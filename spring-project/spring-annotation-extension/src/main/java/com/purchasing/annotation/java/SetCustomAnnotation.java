package com.purchasing.annotation.java;

/**
 * This is the annotation being applied to a class
 * @author Administrator
 * @createTime 2018/9/28
 */
@TypeHeader(developer = "Bob Bee",
        lastModified = "2013-02-12",
        teamMembers = { "Ann", "Dan", "Fran" },
        meaningOfLife = 42)
public class SetCustomAnnotation {
    // Class contents go here
    /**
     * Developer: Bob Bee
     * Last Modified: 2013-02-12
     * Team members: Ann,
     * Dan,
     * Fran,
     * Meaning of Life: 42
     */
    @TestDemo(value = "say")
    public String say(){
        return "test";
    }
}
