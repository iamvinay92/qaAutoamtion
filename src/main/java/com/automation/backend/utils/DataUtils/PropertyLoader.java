package com.automation.backend.utils.DataUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    static Properties properties = null;

    static{
        if(properties == null){
            loadProperty();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    private static void loadProperty(){
       try{
           InputStream inputStream = null;
           inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream("src/test/resources/config.properties");
           if(inputStream == null){
               inputStream = new FileInputStream(new File("src/test/resources/config.properties"));
           }
           properties = new Properties();
           properties.load(inputStream);
       }
       catch (Exception e){
           System.out.println("Unable to load file - " + e.getMessage());
       }
    }
}
