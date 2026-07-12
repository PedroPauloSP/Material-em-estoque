/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Api.MaterialEstocado.Data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
     Optional<UsuarioEntity> findByUsername(String username);
}

