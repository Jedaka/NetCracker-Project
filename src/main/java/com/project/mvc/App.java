package com.project.mvc;

import com.project.util.DatabaseFiller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by jedaka on 31.10.2015.
 */
public class App {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");

        DatabaseFiller.persistTestData();

    }

}
