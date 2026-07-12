
package com.Api.MaterialEstocado.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 */


@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
}
        