/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Api.MaterialEstocado.Controller;

import com.Api.MaterialEstocado.Service.CategoriaService;
import com.Api.MaterialEstocado.Service.EntradaService;
import com.Api.MaterialEstocado.Service.EquipamentoService;
import com.Api.MaterialEstocado.Service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pedro
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/entrada")
public class EntradaController {
      private final EntradaService entradaService;
    private final CategoriaService categoriaService;
    private final EquipamentoService equipamentoservice;
    private final FornecedorService fornecedorservice;

    
}
