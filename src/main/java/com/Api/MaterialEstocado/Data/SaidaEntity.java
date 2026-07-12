
package com.Api.MaterialEstocado.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data 
@Entity 
@Table(name="saida") 
public class SaidaEntity {
     @Id 
@GeneratedValue(strategy = GenerationType.AUTO) 
 
    private Integer id;
   
    @CNPJ(message="CNPJ invalido")
    private String cnpj;
    
    @Size(min=2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String razao_social;
   
     @Size(min=2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String endereco;
    
    
  /* @NotBlank(message="Telefone obrigatório")
    private String telefone;*/
   
   @Email(message="E-mail inválido")
    private String email;

}
