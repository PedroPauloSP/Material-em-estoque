package com.Api.MaterialEstocado.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name="entrada")
public class EntradaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message="Fornecedor obrigatório")
    @ManyToOne
    @JoinColumn(name="fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;
    
    //@NotBlank(message = "CNPJ obrigatório")
    @Size(min = 14, max = 18, message = "CNPJ invalido")
    private String cnpj;
    
     @NotNull(message="Equipamento obrigatório")
    @ManyToOne
    @JoinColumn(name="equipamento_id", nullable = false)
    private EquipamentoEntity equipamento;
     @NotNull(message="Categoria obrigatória")
    @ManyToOne
    @JoinColumn(name="categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @NotNull (message="Quantidade obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que 0")
    private Integer quantidade;

    @NotNull(message = "Data inválida")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;
}
