package fiap.fintech.backend.domain.models;

public class Conta extends FormaPagamento {

    private String agencia;
    private String numero;
    private char tipo;

    public String getAgencia() {
        return agencia;
    }
    public String getNumero() {
        return numero;
    }
    public char getTipo() {
        return tipo;
    }

    public Conta(int codigoCateiraDigital, String agencia, String numero, char tipo) {
        super(codigoCateiraDigital, TipoPagamento.Transferencia);

        this.agencia = agencia;
        this.numero = numero;
        this.tipo = tipo;
    }
}
