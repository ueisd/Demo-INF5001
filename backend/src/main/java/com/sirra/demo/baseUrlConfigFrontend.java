package com.sirra.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sirra.demo.model.UrlConfig;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.net.InetAddress;

@Configuration
public class baseUrlConfigFrontend {
    static {
        if(InetAddress.getLoopbackAddress().getHostName() == "localhost"){
            String baseUrl = "http://" + InetAddress.getLoopbackAddress().getHostName();
            UrlConfig urlConfig = new UrlConfig(baseUrl, 9090);
            try{
                Gson gson = new GsonBuilder().create();
                FileWriter writer = new FileWriter("demo-ui/src/assets/baseUrlConfig.json");
                gson.toJson(urlConfig, writer);
                writer.flush();
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
