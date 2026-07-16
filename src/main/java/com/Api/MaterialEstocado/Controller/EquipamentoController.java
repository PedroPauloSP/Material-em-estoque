
package com.Api.MaterialEstocado.Controller;

import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.FornecedorEntity;
import com.Api.MaterialEstocado.Model.Equipamento;
import com.Api.MaterialEstocado.Service.CategoriaService;
import com.Api.MaterialEstocado.Service.EquipamentoService;
import com.Api.MaterialEstocado.Service.FornecedorService;
import com.Api.MaterialEstocado.Service.MovimentacaoService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author pedro
 */

    @Controller
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoservice;
    private final CategoriaService categoriaService;
    private final FornecedorService fornecedorservice;
    private final MovimentacaoService movimentacaoService;
     @PostMapping("/Equip")
    public String equipamento(@Valid @ModelAttribute("equipamento") EquipamentoEntity equip, BindingResult result) {

        if (result.hasErrors()) {

            return "Equipamento";
        }
        if (equip.getId() == null) {
            equipamentoservice.cadastrarEquipamento(equip);

        } else {
            equipamentoservice.atualizarEquipamento(equip.getId(), equip);
        }
        return "redirect:/equipamentos";

    }

    @GetMapping("/criarEquipamento")
    public String criarEquipamento(Model model) {
        EquipamentoEntity equip = new EquipamentoEntity();
        model.addAttribute("equipamento", equip);
        CategoriaEntity cat = new CategoriaEntity();
        model.addAttribute("nome",cat);
          model.addAttribute("categorias", categoriaService.listarTodasCategorias());
        FornecedorEntity forne = new FornecedorEntity();
        model.addAttribute("nome",forne);
         model.addAttribute("fornecedores",fornecedorservice.listarTodosFornecedores());
        return "Equipamento";

    }

    @GetMapping("/ListarEquip")
    public String listaEquipamento(Model model) {
        model.addAttribute("listarEquipamento", equipamentoservice.listarTodosEquipamentos());
        return "redirect:/equipamentos";

    }
       @GetMapping("/atualizarEquipamento/{id}")
public String atualizarEquipamento(@PathVariable Integer id, Model model) {

    EquipamentoEntity equip = equipamentoservice.getEquipamentoId(id);

    if (equip == null) {
        return "redirect:/ListaEquipamento";
    }

    model.addAttribute("equipamento", equip);

    model.addAttribute("categoria", new CategoriaEntity());
    model.addAttribute("categorias", categoriaService.listarTodasCategorias());

    model.addAttribute("fornecedor", new FornecedorEntity());
    model.addAttribute("fornecedores", fornecedorservice.listarTodosFornecedores());

    return "AtualizarEquipamento";
}

    @GetMapping("deletarEquipamento/{id}")
    public String deletarEquipamento(@PathVariable(required = false)Integer id,RedirectAttributes redirectAttributes) {
       if (id == null ) {
        // redireciona para a lista se não houver ID
        redirectAttributes.addFlashAttribute("msgErro", "ID do equipamento não foi fornecido!");
       
       }
 
    try {
        equipamentoservice.deletarEquipamento(id );
         redirectAttributes.addFlashAttribute("msgSucesso", "Equipamento deletado com sucesso!");
    } catch (EmptyResultDataAccessException e) {
        redirectAttributes.addFlashAttribute("msgErro", "Equipamento não encontrado!");
   
       
    }

    return "redirect:/ListarEquip";
}
  @GetMapping("/equipamentos")
public String listar(Model model) {
    List<EquipamentoEntity> equipamentos = equipamentoservice.findAll();
    // Calcula estoque disponível para cada equipamento
    Map<Integer, Integer> estoques = new HashMap<>();
    
    for(EquipamentoEntity equip : equipamentos) {
       int saldo = movimentacaoService.calcularSaldo(equip.getId());
       equip.setEstoqueDisponivel(saldo);
       
    
    }
    model.addAttribute("listarEquipamento", equipamentos);
  
    return "ListaEquipamento";
}
}

