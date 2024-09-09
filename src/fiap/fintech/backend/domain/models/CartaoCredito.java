package fiap.fintech.backend.domain.models;

import java.time.LocalDateTime;

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
    public String exibirDetalhe() {
        return String.format("%sValor limite R$%.2f\nCódigo Segurança: %s\n",
                super.exibirDetalhe(),
                this.getValorLimite(),
                this.getCodigoSeguranca());
    }
    @Override
    public String exibirResumo() {
        return super.exibirResumo();
    }
}
