package fiap.fintech.backend.domain.models;

import java.util.Dictionary;

public class CartaoDebito extends Cartao {
    public CartaoDebito(int codigoCateiraDigital, String numero, String nome, String dataVencimento) {
        super(codigoCateiraDigital, numero, nome, dataVencimento);
    }

    @Override
    public char getTipoCartao() {
        return 'D';
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return super.getAcoes();
    }
}
