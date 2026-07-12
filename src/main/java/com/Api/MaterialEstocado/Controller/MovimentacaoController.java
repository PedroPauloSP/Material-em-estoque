
package com.Api.MaterialEstocado.Controller;

import com.Api.MaterialEstocado.Data.ClienteEntity;
import com.Api.MaterialEstocado.Data.ClienteRepository;
import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.EquipamentoRepository;
import com.Api.MaterialEstocado.Data.FornecedorEntity;
import com.Api.MaterialEstocado.Data.FornecedorRepository;
import com.Api.MaterialEstocado.Data.MovimentacaoEntity;
import com.Api.MaterialEstocado.Data.MovimentacaoRepository;
import com.Api.MaterialEstocado.Data.TipoMovimentacao;
import com.Api.MaterialEstocado.Model.Equipamento;
import com.Api.MaterialEstocado.Service.MovimentacaoService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pedro
 */
@Controller
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;
    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("movimentacoes", movimentacaoService.listarTodas());
        return "ListaMovimentacoes";
    }

    @GetMapping("/nova")
    public String novaMovimentacao(Model model) {

        MovimentacaoEntity mov = new MovimentacaoEntity();
        mov.setEquipamento(new EquipamentoEntity());
        mov.setCliente(new ClienteEntity());
        mov.setFornecedor(new FornecedorEntity());

        model.addAttribute("movimentacao",mov);
        model.addAttribute("equipamentos", equipamentoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        model.addAttribute("tipos", TipoMovimentacao.values());

        return "/MovimentacaoForm";
    }
    
     @GetMapping("/editar/{id}")
public String editar(@PathVariable Integer id, Model model) {
    // Busca movimentação existente pelo ID
    MovimentacaoEntity mov = movimentacaoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada: " + id));
    
    // Preenche listas para selects
    model.addAttribute("movimentacao", mov);
    model.addAttribute("equipamentos", equipamentoRepository.findAll());
    model.addAttribute("clientes", clienteRepository.findAll());
    model.addAttribute("fornecedores", fornecedorRepository.findAll());
    model.addAttribute("tipos", TipoMovimentacao.values());
    
    return "Movimentacaoform"; // Nome consistente
}

   @PostMapping("/atualizar/{id}")
public String atualizar(@PathVariable Integer id, 
                       @Valid @ModelAttribute("movimentacao") MovimentacaoEntity mov, 
                       BindingResult result, Model model) {
    if (result.hasErrors()) {
        // Recarrega listas se der erro de validação
        model.addAttribute("equipamentos", equipamentoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        model.addAttribute("tipos", TipoMovimentacao.values());
        return "/movimentacoes/movimentacaoform";
    }
    
    mov.setId(id); // Garante que ID correto seja usado
    movimentacaoRepository.save(mov);
    return "redirect:/movimentacoes";
}
  
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute MovimentacaoEntity movimentacao) {
        movimentacaoService.salvar(movimentacao);
        return "redirect:/movimentacoes/listar";
    }

    @GetMapping("/equipamento/{id}")
    public String movimentacoesPorEquipamento(@PathVariable Integer id, Model model) {
        model.addAttribute("movimentacoes", movimentacaoService.listarPorEquipamento(id));
        return "Listamovimentacoes";
    }
  @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
       movimentacaoService.deletarMovimentacao(id);
        return "redirect:/movimentacoes/listar";
    }
}

