/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Api.MaterialEstocado.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Entity
@Table(name="fornecedor")
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    @Column(name = "id")
    private Integer id;

    @Size(min = 2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String nome;
   // private String razao_social;

   @CNPJ(message = "CNPJ inválido(00.000.000/0001-00)")
   @NotBlank(message = "CNPJ é obrigatório")
    @Column(nullable = false, length = 18)
    private String cnpj;
     
    @Size(min = 8, max = 15, message = "Telefone inválido(min=8,max=15")
    private String telefone;
   
     @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    
     @NotBlank(message = "Endereço é obrigatório")
     @Size(min = 5,max=200, message = "Endereço deve ter entre 5 e 200 caracteres")
    private String endereco;
}
