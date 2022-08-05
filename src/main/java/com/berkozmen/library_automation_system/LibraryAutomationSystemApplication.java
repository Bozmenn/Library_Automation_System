package com.berkozmen.library_automation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
public class LibraryAutomationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryAutomationSystemApplication.class, args);
    }

}
