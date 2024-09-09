package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.TipoRenda;
import java.time.LocalDateTime;

public class HistoricoRenda extends BaseModel {
    private int codigoRenda;
    private TipoRenda tipoRenda;
    private double valorBruto;
    private Boolean ativo;

    public HistoricoRenda(int codigoRenda, TipoRenda tipoRenda, double valorBruto, Boolean ativo) {
        super();
        this.codigoRenda = codigoRenda;
        this.tipoRenda = tipoRenda;
        this.valorBruto = valorBruto;
        this.ativo = ativo;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public int getCodigoRenda() { return codigoRenda; }
    public TipoRenda getTipoRenda() { return tipoRenda; }
    public double getValorBruto() { return valorBruto; }
    public Boolean getAtivo() { return ativo; }

    @Override
    public String exibirResumo() {
        return "";
    }
    @Override
    public String exibirDetalhe() {
        return "";
    }
}
