package fiap.fintech.backend.domain.models;

import java.util.Dictionary;

public abstract class FormaPagamento extends BaseModel {
    private int codigoCateiraDigital;
    private TipoPagamento codigoTipoPagamento;

    public int getCodigoCateiraDigital() {
        return codigoCateiraDigital;
    }
    public TipoPagamento getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public FormaPagamento(int codigoCateiraDigital, TipoPagamento codigoTipoPagamento) {
        super();
        this.codigoCateiraDigital = codigoCateiraDigital;
        this.codigoTipoPagamento = codigoTipoPagamento;
    }

    @Override
    public String exibirResumo() {
        return "";
    }
    @Override
    public String exibirDetalhado() {
        return "";
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return null;
    }
}
