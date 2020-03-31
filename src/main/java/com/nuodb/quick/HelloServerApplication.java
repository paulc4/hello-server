package com.nuodb.quick;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple Spring Boot, single REST URL, web-app to demonstrate containers.
 * 
 * @author Paul Chapman
 */
@SpringBootApplication
public class HelloServerApplication {

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        System.out.println("System Environment ...");

        for (Map.Entry<String, String> entry : env.entrySet())
            System.out.println("    " + entry.getKey() + "=" + entry.getValue());

        SpringApplication.run(HelloServerApplication.class, args);
    }

}
