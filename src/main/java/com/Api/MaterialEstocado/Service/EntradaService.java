
package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.EntradaEntity;
import com.Api.MaterialEstocado.Data.EntradaRepository;
import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.EquipamentoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntradaService {

    
    @Autowired
private EntradaRepository entradaRepository;

@Autowired
private EquipamentoRepository equipamentoRepository;

@Transactional
public EntradaEntity cadastrarEntrada(EntradaEntity entrada){

    // busca equipamento real do banco
    EquipamentoEntity equipamento = equipamentoRepository
        .findById(entrada.getEquipamento().getId())
        .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
                 // entrada.getEquipamento().getId())
    // soma quantidade
    equipamento.setQuantidade(
        equipamento.getQuantidade() + entrada.getQuantidade()
    );

    // salva equipamento atualizado
    equipamentoRepository.save(equipamento);

    // garante que entrada usa o objeto gerenciado
    entrada.setEquipamento(equipamento);

    // salva entrada
    return entradaRepository.save(entrada);
}
   

    public EntradaEntity atualizarEntrada(Integer id, EntradaEntity request) {

        EntradaEntity entrada = getEntradaById(id);

        entrada.setFornecedor(request.getFornecedor());
        entrada.setEquipamento(request.getEquipamento());
        entrada.setCategoria(request.getCategoria());
        entrada.setQuantidade(request.getQuantidade());
       
        if(request.getData()!= null){
        entrada.setData(request.getData());
      }

        return entradaRepository.save(entrada);
    }


    public EntradaEntity getEntradaById(Integer id){
        return entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada não encontrada: " + id));
    }


    public List<EntradaEntity> listarTodasEntradas(){
        return entradaRepository.findAll();
    }


    public void deletarEntrada(Integer id){
        if(!entradaRepository.existsById(id)){
            throw new RuntimeException("Entrada não encontrada: " + id);
        }
        entradaRepository.deleteById(id);
    }
}
















