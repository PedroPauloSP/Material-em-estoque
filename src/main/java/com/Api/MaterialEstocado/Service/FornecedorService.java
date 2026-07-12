
package com.Api.MaterialEstocado.Service;
     
import com.Api.MaterialEstocado.Data.FornecedorEntity;
import com.Api.MaterialEstocado.Data.FornecedorRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FornecedorService {
    
      @Autowired
 private   FornecedorRepository fornecedorRepository;
    
   
      public FornecedorEntity criar(FornecedorEntity forne){
    //if (forne.getId_fornecedor() != null){
        if (forne == null){
        throw new IllegalArgumentException("Novo fornecedor não pode ter ID nulo");
    }
         if (forne.getId()!= null){
        throw new IllegalArgumentException("Erro ao criar fornecedor: ID deve ser nulo");
    }
        
        
    return fornecedorRepository.save(forne);
}
      
      
      public FornecedorEntity atualizarFornecedor(Integer forneId, FornecedorEntity fornecedorRequest) {

    FornecedorEntity forne = fornecedorRepository.findById(forneId)
        .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado: " + forneId));

    forne.setNome(fornecedorRequest.getNome());
    forne.setCnpj(fornecedorRequest.getCnpj());
    forne.setTelefone(fornecedorRequest.getTelefone());
    forne.setEndereco(fornecedorRequest.getEndereco());
    forne.setEmail(fornecedorRequest.getEmail());

    return fornecedorRepository.save(forne);
}
     
     public FornecedorEntity getFornecedorId(Integer fornecedorId) { 

 return fornecedorRepository.findById(fornecedorId).orElse(null);
 

     } 
  public List<FornecedorEntity> listarTodosFornecedores() { 
return fornecedorRepository.findAll(); 

} 

public void deletarFornecedor(Integer fornecedorId) { 
FornecedorEntity fornecedor= getFornecedorId(fornecedorId); 

fornecedorRepository.deleteById(fornecedor.getId());

} 

    
}

