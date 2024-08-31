package fiap.fintech.backend.domain.models;

public class Cheque extends FormaPagamento {
    private int numeroTalao;
    private int quantidadeFolha;
    private Boolean bloqueado;

    public Cheque(int codigoCateiraDigital, int numeroTalao, int quantidadeFolha) {
        super(codigoCateiraDigital, TipoPagamento.Cheque);

        this.numeroTalao = numeroTalao;
        this.quantidadeFolha = quantidadeFolha;
        this.bloqueado = true;
    }
}
