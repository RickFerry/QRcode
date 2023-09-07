package br.com.fatec.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsCatalogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCatalogApiApplication.class, args);
    }
}
