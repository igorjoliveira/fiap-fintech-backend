package fiap.fintech.backend.domain.models;

import java.util.Dictionary;

public class ContaCorrente extends Conta {

    public ContaCorrente(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, agencia, numero, digito);
    }

    @Override
    public char getTipo() {
        return 'C';
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return super.getAcoes();
    }
}
