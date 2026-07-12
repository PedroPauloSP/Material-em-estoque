
package com.Api.MaterialEstocado.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
@Entity 
@Table(name="categoria") 
public class CategoriaEntity {
@Id 
@GeneratedValue(strategy = GenerationType.AUTO)     
 
   private Integer id;
   private String descricao;
   @Size(min=2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String nome;
 
    
}
