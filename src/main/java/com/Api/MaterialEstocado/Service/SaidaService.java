
package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.SaidaEntity;
import com.Api.MaterialEstocado.Data.SaidaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaidaService {
     @Autowired
 SaidaRepository saidaRepository;
    
    public SaidaEntity cadastrarSaida(SaidaEntity saida){
     saida.setId(null);
     
     saidaRepository.save(saida);
     return saida;
 }
    
    public SaidaEntity atualizarSaida(Integer saidaId, SaidaEntity saidaRequest) {

        SaidaEntity saida = getsaidaId (saidaId);

        saida.setRazao_social(saidaRequest.getRazao_social());

        saida.setCnpj(saidaRequest.getCnpj());
        saida.setEndereco(saidaRequest.getEndereco());
        saida.setEmail(saidaRequest.getEmail());

       

        saidaRepository.save(saida);

        return saida;

    }
     public SaidaEntity getsaidaId(Integer saidaId) { 

 return saidaRepository.findById(saidaId).orElse(null);
 

     } 
  public List<SaidaEntity> listarTodasSaidas() { 
return saidaRepository.findAll(); 

} 

public void deletarSaidas(Integer saidaId) { 
SaidaEntity saida = getsaidaId(saidaId); 

saidaRepository.deleteById(saida.getId()); 

} 

}
