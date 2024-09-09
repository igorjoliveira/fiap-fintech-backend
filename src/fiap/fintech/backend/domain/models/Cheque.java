package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoPagamento;
import java.time.LocalDateTime;

public class Cheque extends FormaPagamento {
    private int numeroTalao;
    private int quantidadeFolha;
    private Boolean bloqueado;

    public int getNumeroTalao() { return numeroTalao; }
    public int getQuantidadeFolha() { return quantidadeFolha; }
    public Boolean getBloqueado() { return bloqueado; }

    public Cheque(int codigoCateiraDigital, int numeroTalao, int quantidadeFolha) {
        super(codigoCateiraDigital, TipoPagamento.Cheque);

        this.numeroTalao = numeroTalao;
        this.quantidadeFolha = quantidadeFolha;
        this.bloqueado = true;
    }

    public Cheque habilitarTalao(){
        bloqueado = false;
        this.setDataHoraAtualizacao(LocalDateTime.now());
        return this;
    }

    @Override
    public String exibirResumo() {
        return String.format("%s - Número talão: %d - Talão bloqueado: %s\n", super.exibirResumo(), this.getNumeroTalao(), this.getBloqueado());
    }

    @Override
    public String exibirDetalhe() {
        return String.format("%sNúmero talão: %s\nQuantidade de folha: %d\nTalão bloqueado: %s\n",
                super.exibirDetalhe(),
                this.getNumeroTalao(),
                this.getQuantidadeFolha(),
                this.getBloqueado());
    }
}
