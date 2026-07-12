package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.ClienteEntity;
import com.Api.MaterialEstocado.Data.ClienteRepository;

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
class ClienteServiceTest {


    @Mock
    private ClienteRepository clienteRepository;


    @InjectMocks
    private ClienteService clienteService;



    @Test
    void deveCadastrarCliente() {


        ClienteEntity cliente = new ClienteEntity();

        cliente.setNome("João Silva");
        cliente.setCnpj("12345678000199");
        cliente.setEndereco("Rua A");


        when(clienteRepository.save(cliente))
                .thenReturn(cliente);



        ClienteEntity resultado =
                clienteService.cadastrarCliente(cliente);



        assertNotNull(resultado);

        assertEquals("João Silva",
                resultado.getNome());


        verify(clienteRepository)
                .save(cliente);

    }



    @Test
    void deveBuscarClientePorId() {


        ClienteEntity cliente = new ClienteEntity();

        cliente.setId(1);
        cliente.setNome("Maria");



        when(clienteRepository.findById(1))
                .thenReturn(Optional.of(cliente));



        ClienteEntity resultado =
                clienteService.getClienteId(1);



        assertNotNull(resultado);

        assertEquals("Maria",
                resultado.getNome());


        verify(clienteRepository)
                .findById(1);

    }




    @Test
    void deveListarTodosClientes() {


        ClienteEntity cliente1 = new ClienteEntity();

        cliente1.setNome("Cliente 1");



        ClienteEntity cliente2 = new ClienteEntity();

        cliente2.setNome("Cliente 2");



        when(clienteRepository.findAll())
                .thenReturn(Arrays.asList(cliente1, cliente2));



        List<ClienteEntity> lista =
                clienteService.listarTodosClientes();



        assertEquals(2, lista.size());


        verify(clienteRepository)
                .findAll();

    }





    @Test
    void deveAtualizarCliente() {


        ClienteEntity clienteAtual =
                new ClienteEntity();


        clienteAtual.setId(1);
        clienteAtual.setNome("Nome Antigo");
        clienteAtual.setCnpj("11111111111111");
        clienteAtual.setEndereco("Endereco Antigo");



        ClienteEntity clienteNovo =
                new ClienteEntity();


        clienteNovo.setNome("Nome Novo");
        clienteNovo.setCnpj("22222222222222");
        clienteNovo.setEndereco("Endereco Novo");



        when(clienteRepository.findById(1))
                .thenReturn(Optional.of(clienteAtual));



        ClienteEntity resultado =
                clienteService.atualizarCliente(
                        1,
                        clienteNovo);



        assertEquals("Nome Novo",
                resultado.getNome());


        assertEquals("22222222222222",
                resultado.getCnpj());


        assertEquals("Endereco Novo",
                resultado.getEndereco());



        verify(clienteRepository)
                .save(clienteAtual);

    }




    @Test
    void deveDeletarCliente() {


        ClienteEntity cliente =
                new ClienteEntity();


        cliente.setId(1);



        when(clienteRepository.findById(1))
                .thenReturn(Optional.of(cliente));



        clienteService.deletarCliente(1);



        verify(clienteRepository)
                .deleteById(1);

    }

}
