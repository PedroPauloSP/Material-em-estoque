
package com.Api.MaterialEstocado.Controller;


import com.Api.MaterialEstocado.Data.FornecedorEntity;
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
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    // LISTAR
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listarFornecedores",
                fornecedorService.listarTodosFornecedores());
        return "ListarFornecedor";
    }

    // FORM NOVO
    @GetMapping("/novo")
    public String criar(Model model) {
        model.addAttribute("fornecedor", new FornecedorEntity());
        return "Fornecedor";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("fornecedor") FornecedorEntity fornecedor,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "Fornecedor";
        }

        if (fornecedor.getId() == null) {
            fornecedorService.criar(fornecedor);
        } else {
            fornecedorService.atualizarFornecedor(fornecedor.getId(), fornecedor);
        }

        return "redirect:/fornecedores/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        FornecedorEntity fornecedor = fornecedorService.getFornecedorId(id);

        if (fornecedor == null) {
            return "redirect:/fornecedores/listar";
        }

        model.addAttribute("fornecedor", fornecedor);
        return "Fornecedor";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        fornecedorService.deletarFornecedor(id);
        return "redirect:/fornecedores/listar";
    }
}
