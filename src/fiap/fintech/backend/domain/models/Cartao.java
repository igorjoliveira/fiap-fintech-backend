package fiap.fintech.backend.domain.models;

import java.time.LocalDateTime;

public class Cartao extends FormaPagamento {

    private String numero;
    private String nome;
    private String dataVencimento;
    private String codigoSeguranca;
    private char tipoCartao;
    private double valorLimite;

    public String getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public String getDataVencimento() {
        return dataVencimento;
    }
    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }
    public char getTipoCartao() {
        return tipoCartao;
    }

    public Cartao(int codigoCateiraDigital, String numero, String nome, String dataVencimento, String codigoSeguranca, char tipoCartao) {
        super(codigoCateiraDigital, TipoPagamento.Cartao);

        this.numero = numero;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
        this.codigoSeguranca = codigoSeguranca;
        this.tipoCartao = tipoCartao;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Cartao adicionarLimiteCredito(double valorLimite){
        if(this.tipoCartao == 'C')
            this.valorLimite = valorLimite;

        return this;
    }
}
