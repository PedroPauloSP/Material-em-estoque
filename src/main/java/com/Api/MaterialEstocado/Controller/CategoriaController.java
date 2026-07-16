
package com.Api.MaterialEstocado.Controller;

import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author pedro
 */
@Controller
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    
     @GetMapping("/atualizarCategoria/{id}")
public String atualizarCategoria(@PathVariable(value ="id")Integer id, Model model) {
    // BUSCAR (não atualizar!)
    CategoriaEntity cat = categoriaService.getCategoriaId(id);  // ← Método correto
    model.addAttribute("categoria", cat);
    model.addAttribute("categorias", categoriaService.listarTodasCategorias());
    return "Atualizar";

}

@PostMapping("/adicionar")
public String atualizar(@ModelAttribute CategoriaEntity cat) {
if(cat.getId()==null){
    categoriaService.cadastrarCategoria(cat);
}else{
    categoriaService.atualizarCategoria(cat.getId(),cat);
   
}
   return "redirect:/";

}
 @PostMapping("/cadastrar")
    public String Cadastro(@Valid @ModelAttribute("categoria") CategoriaEntity cat, BindingResult result,Model model) {
        
        if (result.hasErrors()) {
          model.addAttribute("categorias",categoriaService.listarTodasCategorias());
            return "Categoria";
        }
        if (cat.getId() == null) {
            categoriaService.cadastrarCategoria(cat);
        } else {
            categoriaService.atualizarCategoria(cat.getId(), cat);
        }
        return "redirect:/";
    }
 @GetMapping("/")
    public String listacategoria(Model model) {
        model.addAttribute("listarCategoria", categoriaService.listarTodasCategorias());
        return "index";
    }

    @GetMapping("/criarCategoria")
    public String criarCategoria(Model model) {
      model.addAttribute( "categoria",  new CategoriaEntity());
        model.addAttribute("categorias", categoriaService.listarTodasCategorias());
        return "Categoria";

    }

    @GetMapping("deletarCategoria/{id}")
    public String deletarCategoria(@PathVariable(value = "id") Integer id) {
        categoriaService.deletarCategoria(id);
        return "redirect:/";
    }
}

