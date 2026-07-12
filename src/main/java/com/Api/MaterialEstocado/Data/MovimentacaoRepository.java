/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Api.MaterialEstocado.Data;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author pedro
 */
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity,Integer> {

    public List<MovimentacaoEntity> findByEquipamentoId(Integer Id);
    
     @Query("SELECT COALESCE(SUM(m.quantidade), 0) FROM MovimentacaoEntity m " +
           "WHERE m.equipamento.id = :equipId AND m.tipo = 'ENTRADA'")
    Integer somaEntradasPorEquipamento(@Param("equipId") Integer equipId);
    
    @Query("SELECT COALESCE(SUM(m.quantidade), 0) FROM MovimentacaoEntity m " +
           "WHERE m.equipamento.id = :equipId AND m.tipo = 'SAIDA'")
    Integer somaSaidasPorEquipamento(@Param("equipId") Integer equipId);
    
    
    
    
 
}


