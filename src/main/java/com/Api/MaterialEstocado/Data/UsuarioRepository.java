
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

