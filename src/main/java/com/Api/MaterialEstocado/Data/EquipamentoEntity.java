/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Api.MaterialEstocado.Data;

import com.Api.MaterialEstocado.Model.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="equipamento")
public class EquipamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id_equipamento;
    private Integer id;

    @Size(min=2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String nome;

    private String descricao;

    @Column(nullable = false) 
    private Integer quantidade;
    
     @Transient
     private Integer estoqueDisponivel;
    
    private Double preco;
    
     @ManyToOne
     @JoinColumn(name = "categoria_id",nullable = true)
     private CategoriaEntity categoria;


     }
     

