package com.cy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
public class NacosConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    @RestController
    public class ConsumerController{

        @Value("${spring.application.name}")
        private String appName;

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private LoadBalancerClient loadBalancerClient;


       @GetMapping("/consumer/doLoadBalanced")
        public String doRestLoadBalancedEcho(){
            String url=String.format("http://%s/provider/echo/%s","nacos-provider",appName);
           return loadBalancedRestTemplate.getForObject(url,String.class);
        }



    @GetMapping("/consumer/doRestEcho")
    public String doRestEcho(){

         //基于服务名获取服务实例
       ServiceInstance serviceIntance =
                loadBalancerClient.choose("nacos-provider");

        //获取实例对应的ip地址
        String host=serviceIntance.getHost();
        //获取实例对应的端口号
        int port=serviceIntance.getPort();

        String url=String.format("http://%s:%s/provider/echo/%s",
                host,port,appName);
        //String url="http://"+host+":"+port+"/provider/echo/"+applicationName;
        //String url="http://localhost:8081/provider/echo/"+applicationName;
        System.out.println("url="+url);
        //return url;

        return restTemplate.getForObject(url, String.class);
    }
    }
}
