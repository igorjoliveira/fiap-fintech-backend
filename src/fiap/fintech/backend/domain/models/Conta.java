package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoPagamento;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class Conta extends FormaPagamento {

    private String agencia;
    private String numero;
    private char digito;

    public String getAgencia() {
        return agencia;
    }
    public String getNumero() {
        return numero;
    }

    public char getDigito() {
        return digito;
    }

    public abstract char getTipo();

    public Conta(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, TipoPagamento.Transferencia);

        this.agencia = agencia;
        this.numero = numero;
        this.digito = digito;
    }

    @Override
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Adicionar Conta corrente");
        data.put(2, "Adicionar Conta poupança");
        data.put(99, "Voltar");

        return data;
    }
}
