package fiap.fintech.backend.domain.models;

import java.util.Dictionary;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, agencia, numero, digito);
    }

    @Override
    public char getTipo() {
        return 'P';
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return super.getAcoes();
    }
}
