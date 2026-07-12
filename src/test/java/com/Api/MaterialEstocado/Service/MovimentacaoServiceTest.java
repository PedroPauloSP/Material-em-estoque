package com.Api.MaterialEstocado.Service;


import com.Api.MaterialEstocado.Data.*;

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
class MovimentacaoServiceTest {


    @Mock
    private MovimentacaoRepository movimentacaoRepository;


    @Mock
    private EquipamentoRepository equipamentoRepository;


    @InjectMocks
    private MovimentacaoService movimentacaoService;




    @Test
    void deveSalvarEntradaComFornecedor(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();

        equipamento.setId(1);



        FornecedorEntity fornecedor =
                new FornecedorEntity();


        MovimentacaoEntity mov =
                new MovimentacaoEntity();


        mov.setEquipamento(equipamento);
        mov.setFornecedor(fornecedor);
        mov.setTipo(TipoMovimentacao.ENTRADA);
        mov.setQuantidade(10);



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        when(movimentacaoRepository.findByEquipamentoId(1))
                .thenReturn(List.of());



        when(movimentacaoRepository.save(mov))
                .thenReturn(mov);



        MovimentacaoEntity resultado =
                movimentacaoService.salvar(mov);



        assertNotNull(resultado);

        assertNull(resultado.getCliente());

        verify(movimentacaoRepository)
                .save(mov);

    }





    @Test
    void entradaSemFornecedorDeveGerarErro(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();

        equipamento.setId(1);



        MovimentacaoEntity mov =
                new MovimentacaoEntity();


        mov.setEquipamento(equipamento);
        mov.setTipo(TipoMovimentacao.ENTRADA);
        mov.setQuantidade(5);



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        assertThrows(RuntimeException.class, () -> {

            movimentacaoService.salvar(mov);

        });

    }






    @Test
    void deveSalvarSaidaComCliente(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();

        equipamento.setId(1);



        ClienteEntity cliente =
                new ClienteEntity();



        MovimentacaoEntity mov =
                new MovimentacaoEntity();


        mov.setEquipamento(equipamento);
        mov.setCliente(cliente);
        mov.setTipo(TipoMovimentacao.SAIDA);
        mov.setQuantidade(5);



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        MovimentacaoEntity entrada =
                new MovimentacaoEntity();

        entrada.setTipo(TipoMovimentacao.ENTRADA);
        entrada.setQuantidade(20);



        when(movimentacaoRepository.findByEquipamentoId(1))
                .thenReturn(List.of(entrada));



        when(movimentacaoRepository.save(mov))
                .thenReturn(mov);



        MovimentacaoEntity resultado =
                movimentacaoService.salvar(mov);



        assertNotNull(resultado);

        assertNull(resultado.getFornecedor());


        verify(movimentacaoRepository)
                .save(mov);

    }





    @Test
    void naoDevePermitirSaidaMaiorQueEstoque(){


        EquipamentoEntity equipamento =
                new EquipamentoEntity();

        equipamento.setId(1);



        ClienteEntity cliente =
                new ClienteEntity();



        MovimentacaoEntity mov =
                new MovimentacaoEntity();


        mov.setEquipamento(equipamento);
        mov.setCliente(cliente);
        mov.setTipo(TipoMovimentacao.SAIDA);
        mov.setQuantidade(50);



        when(equipamentoRepository.findById(1))
                .thenReturn(Optional.of(equipamento));



        when(movimentacaoRepository.findByEquipamentoId(1))
                .thenReturn(List.of());



        assertThrows(RuntimeException.class, () -> {

            movimentacaoService.salvar(mov);

        });

    }





    @Test
    void deveCalcularSaldoEstoque(){


        MovimentacaoEntity entrada =
                new MovimentacaoEntity();


        entrada.setTipo(TipoMovimentacao.ENTRADA);
        entrada.setQuantidade(100);



        MovimentacaoEntity saida =
                new MovimentacaoEntity();


        saida.setTipo(TipoMovimentacao.SAIDA);
        saida.setQuantidade(30);



        when(movimentacaoRepository.findByEquipamentoId(1))
                .thenReturn(List.of(entrada, saida));



        int saldo =
                movimentacaoService.calcularSaldo(1);



        assertEquals(70, saldo);

    }





    @Test
    void deveListarTodasMovimentacoes(){


        when(movimentacaoRepository.findAll())
                .thenReturn(List.of(new MovimentacaoEntity()));



        List<MovimentacaoEntity> lista =
                movimentacaoService.listarTodas();



        assertEquals(1, lista.size());

    }





    @Test
    void deveDeletarMovimentacao(){


        when(movimentacaoRepository.existsById(1))
                .thenReturn(true);



        movimentacaoService.deletarMovimentacao(1);



        verify(movimentacaoRepository)
                .deleteById(1);

    }

}
