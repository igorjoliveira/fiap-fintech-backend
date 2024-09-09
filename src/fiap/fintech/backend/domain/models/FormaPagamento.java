package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoPagamento;

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
        return String.format("Código Forma Pagamento: %d - Tipo Pagamento: %s\n",
                this.getCodigoCateiraDigital(),
                this.getCodigoTipoPagamento());
    }
    @Override
    public String exibirDetalhe() {
        return String.format("Código Forma Pagamento: %s\n" + "Tipo Pagamento: %s\n",
                this.getCodigo(),
                this.getCodigoTipoPagamento());
    }
}
