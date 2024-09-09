package fiap.fintech.backend.domain.models;

import java.time.LocalDateTime;
import java.util.Dictionary;

public class CartaoCredito extends Cartao {

    private String codigoSeguranca;
    private double valorLimite;

    public double getValorLimite() {
        return valorLimite;
    }
    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public CartaoCredito(int codigoCateiraDigital, String numero, String nome, String dataVencimento, String codigoSeguranca) {
        super(codigoCateiraDigital, numero, nome, dataVencimento);

        this.codigoSeguranca = codigoSeguranca;
    }

    public CartaoCredito atualizarValorLimite(double valorLimite){
        this.valorLimite = valorLimite;
        this.setDataHoraAtualizacao(LocalDateTime.now());
        return this;
    }

    @Override
    public char getTipoCartao() {
        return 'C';
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return super.getAcoes();
    }
}
