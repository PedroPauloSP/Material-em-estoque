package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.ClienteEntity;
import com.Api.MaterialEstocado.Data.ClienteRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity cadastrarCliente(ClienteEntity cliente) {
        cliente.setId(null);

        clienteRepository.save(cliente);
        return cliente;
    }

    public ClienteEntity atualizarCliente(Integer clienteId, ClienteEntity clienteRequest) {

        ClienteEntity cliente = getClienteId(clienteId);

        cliente.setNome(clienteRequest.getNome());

        cliente.setCnpj(clienteRequest.getCnpj());
        cliente.setEndereco(clienteRequest.getEndereco());

        clienteRepository.save(cliente);

        return cliente;

    }

    public ClienteEntity getClienteId(Integer clienteId) {

        return clienteRepository.findById(clienteId).orElse(null);

    }

    public List<ClienteEntity> listarTodosClientes() {
        return clienteRepository.findAll();

    }

    public void deletarCliente(Integer clienteId) {
        ClienteEntity cliente = getClienteId(clienteId);

        clienteRepository.deleteById(cliente.getId());

    }

}
