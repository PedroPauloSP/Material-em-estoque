package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Data.CategoriaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */
@Service

public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaEntity cadastrarCategoria(CategoriaEntity cat) {
        cat.setId(null);
        return categoriaRepository.save(cat);
    }

    public CategoriaEntity getCategoriaId(Integer cat) {
        return categoriaRepository.findById(cat).orElse(null);

    }

    public List<CategoriaEntity> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(Integer catId) {
        CategoriaEntity cat = getCategoriaId(catId);
        categoriaRepository.deleteById(cat.getId());

    }

    public CategoriaEntity atualizarCategoria(Integer catId, CategoriaEntity categoriaRequest) {
        CategoriaEntity cat = getCategoriaId(catId);
        cat.setId(catId);
        cat.setNome(categoriaRequest.getNome());
        categoriaRepository.save(cat);
        return cat;

    }
}
