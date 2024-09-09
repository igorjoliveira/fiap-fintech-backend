package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoPagamento;
import java.time.LocalDateTime;

public abstract class Cartao extends FormaPagamento {

    private String numero;
    private String nome;
    private String dataVencimento;

    public String getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public String getDataVencimento() {
        return dataVencimento;
    }

    public abstract char getTipoCartao();

    public Cartao(int codigoCateiraDigital, String numero, String nome, String dataVencimento) {
        super(codigoCateiraDigital, TipoPagamento.Cartao);

        this.numero = numero;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    @Override
    public String exibirDetalhe() {
        String mensagem = "%sTipo cartão: %s\n" +
                "Número: %s\n" +
                "Data Vencimento: %s\n" +
                "Nome: %s\n";

        return String.format(mensagem,
                super.exibirDetalhe(),
                this.getTipoCartao(),
                this.getNumero(),
                this.getDataVencimento(),
                this.getNome());
    }
    @Override
    public String exibirResumo() {
        return String.format("%s - Tipo cartão: %s - Número: %s - Data Vencimento: %s\n",
                super.exibirResumo(),
                this.getTipoCartao(),
                this.getNumero(),
                this.getDataVencimento());
    }
}
