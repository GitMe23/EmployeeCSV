package com.sparta.ah.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertiesLoader {
   public static Properties getProperties() {
       Properties properties = new Properties();
       try {
           properties.load(new FileReader("src/main/resources/jdbc.properties"));
       } catch (IOException e) {
            e.printStackTrace();
       }
       return properties;
   }
}
