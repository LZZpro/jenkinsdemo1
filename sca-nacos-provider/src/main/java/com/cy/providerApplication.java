package com.cy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
public class providerApplication {

    public static void main(String[] args) {
        SpringApplication.run(providerApplication.class,args);
    }

    @Value("${server.port}")
    private String server;


//    @RestController
//    public class ProviderController{
//        @GetMapping("/provider/echo/{string}")
//        public String doEcho(@PathVariable String string, HttpServletRequest request){
//            Enumeration<String> headerNames = request.getHeaderNames();
//
//            while(headerNames.hasMoreElements()){
//                String headerName = headerNames.nextElement();
//                String headerValue = request.getHeader(headerName);
//                System.out.println(headerName+"/"+headerValue);
//            }
////            String pageSize=request.getParameter("pageSize");
////            System.out.println("pageSize="+pageSize);
//            return "nacos-provider:"+server+"-> say hello "+ string;
//        }
//    }

}
