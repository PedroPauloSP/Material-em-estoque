
package com.Api.MaterialEstocado.Service;

import com.Api.MaterialEstocado.Data.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final EquipamentoRepository equipamentoRepository;

    @Transactional
    public MovimentacaoEntity salvar(MovimentacaoEntity mov) {

        // BUSCA CORRETA PELO ID
        EquipamentoEntity equipamento = equipamentoRepository
                .findById(mov.getEquipamento().getId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        int saldoAtual = calcularSaldo(equipamento.getId());

        if (mov.getTipo() == TipoMovimentacao.ENTRADA) {

            if (mov.getFornecedor() == null) {
                throw new RuntimeException("Entrada deve ter fornecedor");
            }

            mov.setCliente(null);

        } else if (mov.getTipo() == TipoMovimentacao.SAIDA) {

            if (mov.getCliente() == null) {
                throw new RuntimeException("Saída deve ter cliente");
            }

            if (mov.getQuantidade() > saldoAtual) {
                throw new RuntimeException("Estoque insuficiente!");
            }

            mov.setFornecedor(null);
        }

        mov.setDataMovimentacao(LocalDateTime.now());

        return movimentacaoRepository.save(mov);
    }

    public int calcularSaldo(Integer Id) {

        List<MovimentacaoEntity> lista
                = movimentacaoRepository.findByEquipamentoId(Id);

        int saldo = 0;

        for (MovimentacaoEntity m : lista) {
            if (m.getTipo() == TipoMovimentacao.ENTRADA) {
                saldo += m.getQuantidade();
            } else {
                saldo -= m.getQuantidade();
            }
        }

        return saldo;
    }

    public List<MovimentacaoEntity> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public List<MovimentacaoEntity> listarPorEquipamento(Integer equipamentoId) {
        return movimentacaoRepository.findByEquipamentoId(equipamentoId);
    }

    public void deletarMovimentacao(Integer id) {
        if (!movimentacaoRepository.existsById(id)) {
            throw new RuntimeException("Movimentação não encontrada: " + id);
        }
        movimentacaoRepository.deleteById(id);
    }
}
