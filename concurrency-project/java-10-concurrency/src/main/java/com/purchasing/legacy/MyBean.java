package com.purchasing.legacy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * JAVA内省机制
 * @author zhangwei
 * @createTime 2018/9/11
 */
public class MyBean {

    private String value;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(String value, PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
          String oldValue = this.value;
          this.value  = newValue;
          this.pcs.firePropertyChange("value",oldValue,newValue);
    }

    public static void main(String[] args) {
        MyBean bean = new MyBean();
        bean.addPropertyChangeListener("value",(event) ->{
            System.out.printf("[MyBean] 属性 %s ,OldValue : %s , newValue : %s \n",event.getPropertyName(),
                    event.getOldValue(),event.getNewValue());
        });
        bean.setValue("Hello World");
        bean.setValue("Hello World1");
    }
}
