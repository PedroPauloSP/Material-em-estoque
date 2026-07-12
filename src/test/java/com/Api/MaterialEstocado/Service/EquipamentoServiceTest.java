package com.Api.MaterialEstocado.Service;


import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Data.CategoriaRepository;
import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.EquipamentoRepository;
import com.Api.MaterialEstocado.Data.MovimentacaoRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class EquipamentoServiceTest {


    @Mock
    private EquipamentoRepository equipamentoRepository;


    @Mock
    private CategoriaRepository categoriaRepository;


    @Mock
    private MovimentacaoRepository movimentacaoRepo;



    @InjectMocks
    private EquipamentoService equipamentoService;



    @Test
    void deveCadastrarEquipamento(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();


        equipamento.setNome("Notebook");


        when(equipamentoRepository.save(equipamento))
                .thenReturn(equipamento);



        EquipamentoEntity resultado =
                equipamentoService.cadastrarEquipamento(equipamento);



        assertNotNull(resultado);

        assertEquals("Notebook",
                resultado.getNome());


        verify(equipamentoRepository)
                .save(equipamento);

    }





    @Test
    void deveBuscarEquipamentoPorId(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();


        equipamento.setId(1);
        equipamento.setNome("Monitor");



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        EquipamentoEntity resultado =
                equipamentoService.getEquipamentoId(1);



        assertEquals("Monitor",
                resultado.getNome());


        verify(equipamentoRepository)
                .findById(1);

    }






    @Test
    void deveListarTodosEquipamentos(){


        EquipamentoEntity e1 =
                new EquipamentoEntity();

        e1.setNome("Mouse");


        EquipamentoEntity e2 =
                new EquipamentoEntity();

        e2.setNome("Teclado");



        when(equipamentoRepository.findAll())
                .thenReturn(Arrays.asList(e1,e2));



        List<EquipamentoEntity> lista =
                equipamentoService.listarTodosEquipamentos();



        assertEquals(2,
                lista.size());



        verify(equipamentoRepository)
                .findAll();

    }





    @Test
    void deveAtualizarEquipamento(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();


        equipamento.setId(1);
        equipamento.setNome("Antigo");



        EquipamentoEntity request =
                new EquipamentoEntity();


        request.setNome("Novo");
        request.setDescricao("Descrição nova");
        request.setQuantidade(10);
        request.setPreco(500.0)
        ;



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        when(equipamentoRepository.save(equipamento))
                .thenReturn(equipamento);



        EquipamentoEntity resultado =
                equipamentoService.atualizarEquipamento(
                        1,
                        request
                );



        assertEquals("Novo",
                resultado.getNome());


        assertEquals(10,
                resultado.getQuantidade());



        verify(equipamentoRepository)
                .save(equipamento);

    }






    @Test
    void deveCalcularEstoqueDisponivel(){


        when(movimentacaoRepo
                .somaEntradasPorEquipamento(1))
                .thenReturn(100);



        when(movimentacaoRepo
                .somaSaidasPorEquipamento(1))
                .thenReturn(30);



        Integer estoque =
                equipamentoService
                .calcularEstoqueDisponivel(1);



        assertEquals(70,
                estoque);

    }





    @Test
    void estoqueNuncaDeveSerNegativo(){


        when(movimentacaoRepo
                .somaEntradasPorEquipamento(1))
                .thenReturn(20);



        when(movimentacaoRepo
                .somaSaidasPorEquipamento(1))
                .thenReturn(50);



        Integer estoque =
                equipamentoService
                .calcularEstoqueDisponivel(1);



        assertEquals(0,
                estoque);

    }






    @Test
    void deveListarEquipamentosComEstoque(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();


        equipamento.setId(1);
        equipamento.setNome("Notebook");



        when(equipamentoRepository.findAll())
                .thenReturn(List.of(equipamento));



        when(movimentacaoRepo
                .somaEntradasPorEquipamento(1))
                .thenReturn(50);



        when(movimentacaoRepo
                .somaSaidasPorEquipamento(1))
                .thenReturn(10);



        List<EquipamentoEntity> lista =
                equipamentoService.findAllComEstoque();



        assertEquals(1,
                lista.size());


        assertEquals(40,
                lista.get(0)
                .getEstoqueDisponivel());

    }

}
