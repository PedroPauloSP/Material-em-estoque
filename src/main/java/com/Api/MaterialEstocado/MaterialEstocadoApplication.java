package com.Api.MaterialEstocado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MaterialEstocadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterialEstocadoApplication.class, args);
        }

    /**
     *
     * @return
     */
   @Bean
             public Java8TimeDialect java8TimeDialect() {
             return new Java8TimeDialect();            
}
}