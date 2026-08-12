package org.accountBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class acccountBookApplication {

    public static void main(String[] args){
        SpringApplication.run(acccountBookApplication.class,args);
    }
}
