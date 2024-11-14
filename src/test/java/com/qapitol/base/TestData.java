package com.qapitol.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class TestData {
        private static Properties properties;
        static {
            try{
                String userDir = System.getProperty("user.dir");
                // Construct the path to the properties file relative to the project root directory
                String filePath = userDir + "\\src\\test\\resources\\TestaData.properties";
                properties=new Properties();
                FileInputStream file = new FileInputStream(filePath);
                properties.load(file);
                file.close();
            }
            catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Failed to load test data properties file.");
            }
        }
        public static String get(String key) {
            return properties.getProperty(key);
        }
    }


