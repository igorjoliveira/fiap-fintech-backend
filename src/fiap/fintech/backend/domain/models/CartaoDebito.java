package fiap.fintech.backend.domain.models;

public class CartaoDebito extends Cartao {
    public CartaoDebito(int codigoCateiraDigital, String numero, String nome, String dataVencimento) {
        super(codigoCateiraDigital, numero, nome, dataVencimento);
    }

    @Override
    public char getTipoCartao() {
        return 'D';
    }
    @Override
    public String exibirDetalhe() {
        return super.exibirDetalhe();
    }
    @Override
    public String exibirResumo() {
        return super.exibirResumo();
    }
}
