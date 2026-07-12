
package com.Api.MaterialEstocado.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity, Integer> {
    
}
