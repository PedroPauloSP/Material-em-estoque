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
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO pode variar; IDENTITY é seguro para MySQL
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres)")
    private String nome;
   

    @CNPJ(message = "CNPJ inválido(00.000.000/0001-00)")
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @Size(min = 8, max = 15, message = "Telefone inválido(min=8,max=15")
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 5, max = 200, message = "Endereço deve ter entre 5 e 200 caracteres")
    private String endereco;
}
