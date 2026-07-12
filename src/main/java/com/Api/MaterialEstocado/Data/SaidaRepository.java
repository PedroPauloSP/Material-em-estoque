
package com.Api.MaterialEstocado.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<SaidaEntity, Integer> {
    
}
