package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Data.CategoriaRepository;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {


    @Mock
    private CategoriaRepository categoriaRepository;


    @InjectMocks
    private CategoriaService categoriaService;



    @Test
    void deveCadastrarCategoria() {

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNome("Ferramentas");


        when(categoriaRepository.save(categoria))
                .thenReturn(categoria);


        CategoriaEntity resultado =
                categoriaService.cadastrarCategoria(categoria);


        assertEquals("Ferramentas",
                resultado.getNome());

        verify(categoriaRepository)
                .save(categoria);
    }



    @Test
    void deveBuscarCategoriaPorId() {


        CategoriaEntity categoria = new CategoriaEntity();

        categoria.setId(1);
        categoria.setNome("Elétrica");


        when(categoriaRepository.findById(1))
                .thenReturn(Optional.of(categoria));


        CategoriaEntity resultado =
                categoriaService.getCategoriaId(1);



        assertNotNull(resultado);

        assertEquals("Elétrica",
                resultado.getNome());


        verify(categoriaRepository)
                .findById(1);
    }



    @Test
    void deveListarTodasCategorias() {


        CategoriaEntity c1 = new CategoriaEntity();
        c1.setNome("Ferramentas");


        CategoriaEntity c2 = new CategoriaEntity();
        c2.setNome("Máquinas");


        when(categoriaRepository.findAll())
                .thenReturn(Arrays.asList(c1,c2));


        List<CategoriaEntity> lista =
                categoriaService.listarTodasCategorias();


        assertEquals(2, lista.size());

        verify(categoriaRepository)
                .findAll();
    }




    @Test
    void deveDeletarCategoria() {


        CategoriaEntity categoria = new CategoriaEntity();

        categoria.setId(1);


        when(categoriaRepository.findById(1))
                .thenReturn(Optional.of(categoria));


        categoriaService.deletarCategoria(1);



        verify(categoriaRepository)
                .deleteById(1);
    }




    @Test
    void deveAtualizarCategoria() {


        CategoriaEntity categoriaAtual =
                new CategoriaEntity();

        categoriaAtual.setId(1);
        categoriaAtual.setNome("Antigo");


        CategoriaEntity novaCategoria =
                new CategoriaEntity();

        novaCategoria.setNome("Nova Categoria");



        when(categoriaRepository.findById(1))
                .thenReturn(Optional.of(categoriaAtual));



        CategoriaEntity resultado =
                categoriaService.atualizarCategoria(
                        1,
                        novaCategoria);



        assertEquals("Nova Categoria",
                resultado.getNome());



        verify(categoriaRepository)
                .save(categoriaAtual);
    }

}
