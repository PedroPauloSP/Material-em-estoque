package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.CategoriaEntity;
import com.Api.MaterialEstocado.Data.CategoriaRepository;
import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.EquipamentoRepository;
import com.Api.MaterialEstocado.Data.MovimentacaoRepository;
import com.Api.MaterialEstocado.Model.Equipamento;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EquipamentoService {

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;
     @Autowired
    private MovimentacaoRepository movimentacaoRepo;
    @Autowired
    private EquipamentoRepository equipamentoRepo;

    public EquipamentoEntity cadastrarEquipamento(EquipamentoEntity equip){
        equip.setId(null);
        return equipamentoRepository.save(equip);
    }

    public EquipamentoEntity getEquipamentoId(Integer equipId){
        return equipamentoRepository.findById(equipId)
            .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
    }

    public List<EquipamentoEntity> listarTodosEquipamentos(){
        return equipamentoRepository.findAll();
    }

    public void deletarEquipamento(Integer equipId){
        EquipamentoEntity equip = getEquipamentoId(equipId);
        equip.setCategoria(null);
        equipamentoRepository.save(equip);
        equipamentoRepository.delete(equip);
    }

    public EquipamentoEntity atualizarEquipamento(Integer equipId, EquipamentoEntity request){

        EquipamentoEntity equip = getEquipamentoId(equipId);

        equip.setNome(request.getNome());
        equip.setDescricao(request.getDescricao());
        equip.setQuantidade(request.getQuantidade());
        equip.setPreco(request.getPreco());

        if (request.getCategoria() != null && request.getCategoria().getId() != null) {

            CategoriaEntity categoria = categoriaRepository.findById(
                request.getCategoria().getId()
            ).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            equip.setCategoria(categoria);

        } else {
            equip.setCategoria(null);
        }

        return equipamentoRepository.save(equip);
        
    }
    public List<EquipamentoEntity> findAll() {
        return equipamentoRepo.findAll();
    }
    
    
      public Integer calcularEstoqueDisponivel(Integer equipamentoId) {
        Integer totalEntradas = movimentacaoRepo.somaEntradasPorEquipamento(equipamentoId);
        Integer totalSaidas = movimentacaoRepo.somaSaidasPorEquipamento(equipamentoId);
        
        totalEntradas = totalEntradas != null ? totalEntradas : 0;
        totalSaidas = totalSaidas != null ? totalSaidas : 0;
        
        return Math.max(0, totalEntradas - totalSaidas); // Nunca negativo
    }

    // LISTAR COM ESTOQUE CALCULADO
    public List<EquipamentoEntity> findAllComEstoque() {
        List<EquipamentoEntity> equipamentos = listarTodosEquipamentos();
        for (EquipamentoEntity eq : equipamentos) {
            eq.setEstoqueDisponivel(calcularEstoqueDisponivel(eq.getId()));
        }
        return equipamentos;
    }
}






























/*package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.EquipamentoEntity;
import com.Api.MaterialEstocado.Data.EquipamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {
    @Autowired
    EquipamentoRepository equipamentoRepository;
    
 public EquipamentoEntity CadastrarEquipamento (EquipamentoEntity Equip){
     Equip.setId(null);
     
     equipamentoRepository.save(Equip);
     return Equip;
 }
 public EquipamentoEntity getEquipamentoId(Integer EquipId){
     return equipamentoRepository.findById(EquipId).orElse(null);
 }
 public List<EquipamentoEntity>listarTodosEquipamentos(){
     return equipamentoRepository.findAll();
 }
 public void deletarEquipamento(Integer EquipId){
     EquipamentoEntity equip = equipamentoRepository.findById(EquipId)
             .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
    // equip.setFornecedor(null);
     equip.setCategoria(null);
     equipamentoRepository.save(equip);
     
    equipamentoRepository.deleteById(EquipId);
     
 }*/
 /*
 public EquipamentoEntity atualizarEquipamento(Integer EquipId,EquipamentoEntity equipamentoRequest){
     EquipamentoEntity Equip = getEquipamentoId(EquipId);
     Equip.setNome(equipamentoRequest.getNome());
     equipamentoRepository.save(Equip);
     return Equip;
 }*/
/*este
 public EquipamentoEntity atualizarEquipamento(Integer equipId, EquipamentoEntity equipamentoRequest) {
*/
    // Busca o equipamento existente
 /*   EquipamentoEntity equip = getEquipamentoId(equipId);

    // Atualiza campos simples
    equip.setNome(equipamentoRequest.getNome());
    equip.setDescricao(equipamentoRequest.getDescricao());
    equip.setQuantidade(equipamentoRequest.getQuantidade());
    equip.setPreco(equipamentoRequest.getPreco());*/

    // Atualiza chaves estrangeiras de forma segura
  /*  if (equipamentoRequest.getCategoria() != null 
        && equipamentoRequest.getCategoria().getId() != null) {
        equip.setCategoria(equipamentoRequest.getCategoria());
    } else {
        equip.setCategoria(null); // permite deixar categoria vazia
    }*/
/*
    if (equipamentoRequest.getFornecedor() != null 
        && equipamentoRequest.getFornecedor().getId() != null) {
        equip.setFornecedor(equipamentoRequest.getFornecedor());
    } else {
        equip.setFornecedor(null); // permite deixar fornecedor vazio
    }
*/
    // Salva o equipamento atualizado
/*    equipamentoRepository.save(equip);

    return equip;
}
 
 
 
 
 
 
}
     */


