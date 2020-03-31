package com.nuodb.quick;

import java.net.InetAddress;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    private String myAddress = "Unknown";

    public SayHelloController() {
        // Get IP address to show which container is running
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("Running on IP: " + inetAddress.getHostAddress());

            myAddress = "IP " + inetAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello from " + myAddress + "\n\r";
    }
}
