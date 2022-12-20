package com.reanima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = "com.reanima")
@SpringBootApplication(scanBasePackages = "com.reanima")
public class RecipeApp {

    public static void main(String[] args) {
        SpringApplication.run(RecipeApp.class, args);
    }
}
