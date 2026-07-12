/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Api.MaterialEstocado.Config;

import com.Api.MaterialEstocado.Data.UsuarioEntity;
import com.Api.MaterialEstocado.Data.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {

                UsuarioEntity admin = new UsuarioEntity();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("123"));
                admin.setRole("ADMIN");

                repo.save(admin);

                System.out.println("✅ ADMIN criado com sucesso!");
            }
        };
    }
}