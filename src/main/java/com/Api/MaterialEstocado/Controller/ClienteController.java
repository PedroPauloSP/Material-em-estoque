
package com.Api.MaterialEstocado.Controller;



import com.Api.MaterialEstocado.Data.ClienteEntity;
import com.Api.MaterialEstocado.Data.FornecedorEntity;
import com.Api.MaterialEstocado.Service.ClienteService;
import com.Api.MaterialEstocado.Service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // LISTAR
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listarClientes",
                clienteService.listarTodosClientes());
        return "ListarCliente";
    }

    // FORM NOVO
    @GetMapping("/novo")
    public String criar(Model model) {
        model.addAttribute("cliente", new FornecedorEntity());
        return "Cliente";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("cliente") ClienteEntity cliente,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "Cliente";
        }

        if (cliente.getId() == null) {
            clienteService.cadastrarCliente(cliente);
        } else {
            clienteService.atualizarCliente(cliente.getId(), cliente);
        }

        return "redirect:/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        ClienteEntity cliente = clienteService.getClienteId(id);

        if (cliente == null) {
            return "redirect:/listar";
        }

        model.addAttribute("cliente", cliente);
        return "Cliente";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
       clienteService.deletarCliente(id);
        return "redirect:/listar";
    }
}
