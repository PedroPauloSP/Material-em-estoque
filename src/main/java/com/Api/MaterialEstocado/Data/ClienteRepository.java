
package com.Api.MaterialEstocado.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer> {
    
}
