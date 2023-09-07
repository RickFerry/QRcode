package br.com.fatec.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsQrcodeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsQrcodeApiApplication.class, args);
    }
}
