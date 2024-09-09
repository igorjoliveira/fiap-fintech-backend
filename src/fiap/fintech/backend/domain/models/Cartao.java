package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoPagamento;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;

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
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Adicionar Cartão de crédito");
        data.put(2, "Adicionar Conta de débito");
        data.put(99, "Voltar");

        return data;
    }
}
