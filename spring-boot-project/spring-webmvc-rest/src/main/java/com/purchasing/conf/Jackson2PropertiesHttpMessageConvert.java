package com.purchasing.conf;

import com.purchasing.model.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author zhangwei
 * @createTime 2018/9/12
 */
public class Jackson2PropertiesHttpMessageConvert extends AbstractHttpMessageConverter<Person> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public Jackson2PropertiesHttpMessageConvert(){
        super(new MediaType("application", "properties",DEFAULT_CHARSET), MediaType.ALL);
        setDefaultCharset(DEFAULT_CHARSET);
    }

    @Override
    protected boolean supports(Class clazz) {
         return clazz.isAssignableFrom(Person.class);
    }

    /**
     * 从Properties文件中读取信息传递给Person
     *
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Person readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            InputStream inputStream = inputMessage.getBody();
            Properties properties = new Properties();
            properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
            Long id = Long.parseLong(properties.getProperty("person.id"));
            String name = properties.getProperty("person.name");
            Integer age = Integer.parseInt(properties.getProperty("person.age"));
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(properties.getProperty("person.birth"));
            return new Person(id,name,age,date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Person对象中的值写入到Properties中
     *
     * @param person
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Person person, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        Properties properties = new Properties();
        properties.setProperty("person.id",String.valueOf(person.getId()));
        properties.setProperty("person.name",person.getName());
        properties.setProperty("person.age",String.valueOf(person.getAge()));
        properties.setProperty("person.birth",new SimpleDateFormat("yyyy-MM-dd").format(person.getBirth()));
        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"Person Convert Properties Success !");
    }
}
