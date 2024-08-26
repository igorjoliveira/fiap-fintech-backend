package fiap.fintech.backend.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Renda extends BaseModel {
    private int codigoParticipante;
    private TipoRenda tipoRenda;
    private double valorBruto;
    private Boolean ativo;
    private List<HistoricoRenda> historicoRendaLista;

    public Renda() {
        super();
        this.historicoRendaLista = new ArrayList<>();
    }
    public Renda(int codigoParticipante, TipoRenda tipoRenda, double valorBruto, Boolean ativo) {
        this();
        this.codigoParticipante = codigoParticipante;
        this.tipoRenda = tipoRenda;
        this.valorBruto = valorBruto;
        this.ativo = ativo;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public int getCodigoParticipante() { return codigoParticipante; }
    public TipoRenda getTipoRenda() { return tipoRenda; }
    public double getValorBruto() { return valorBruto; }
    public Boolean getAtivo() { return ativo; }
    public List<HistoricoRenda> getHistoricoRendaLista() { return historicoRendaLista; }

    public Renda setValorBruto(double valorBruto){
        this.valorBruto = valorBruto;
        return inserirHistorico();
    }
    public Renda setTipoRenda(TipoRenda tipoRenda){
        this.tipoRenda = tipoRenda;
        return  inserirHistorico();
    }
    public Renda setAtivo(Boolean ativo){
        this.ativo = ativo;
        return inserirHistorico();
    }

    private Renda inserirHistorico(){
        this.historicoRendaLista.add(new HistoricoRenda(this.getCodigo(), this.tipoRenda, this.valorBruto, this.ativo));
        this.setDataHoraAtualizacao(LocalDateTime.now());
        return this;
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        return null;
    }
    @Override
    public String exibirResumo() {
        return String.format("Codigo: %d - Participante: %d - Tipo renda: %s - Valor bruto R$%.2f - Ativo: %s\n", this.getCodigo(), this.codigoParticipante, this.tipoRenda, this.valorBruto, this.ativo);
    }
    @Override
    public String exibirDetalhado() {
        var mensagem = new StringBuilder();
        mensagem.append("Codigo: %d\n")
                .append("Codigo Participante: %d\n")
                .append("Tipo renda: %s\n")
                .append("Valor bruto R$%.2f\n")
                .append("Ativo: %s\n")
                .append("Total historico: %d\n");

        return String.format(mensagem.toString(),
                this.getCodigo(),
                this.codigoParticipante,
                this.tipoRenda,
                this.valorBruto,
                this.ativo,
                this.historicoRendaLista.size());
    }
}
