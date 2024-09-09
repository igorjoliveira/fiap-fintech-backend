package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.CategoriaDespesa;

import java.time.LocalDateTime;
import java.util.Dictionary;

public class Despesa extends BaseModel {

    private CategoriaDespesa categoriaDespesa;
    private int codigoFormaPagamento;
    private int codigoParticipante;
    private String descricao;
    private double valor;
    private Boolean parcelado;
    private int quantidadeParcela;
    private LocalDateTime dataHoraRealizada;

    public CategoriaDespesa getCategoriaDespesa() {
        return categoriaDespesa;
    }
    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }
    public int getCodigoParticipante() {
        return codigoParticipante;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor() {
        return valor;
    }
    public Boolean getParcelado() {
        return parcelado;
    }
    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }
    public LocalDateTime getDataHoraRealizada() {
        return dataHoraRealizada;
    }

    public Despesa(int codigoParticipante, String descricao, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada) {
        super();
        this.codigoParticipante = codigoParticipante;
        this.descricao = descricao;
        this.valor = valor;
        this.categoriaDespesa = categoriaDespesa;
        this.dataHoraRealizada = dataHoraRealizada;
        this.parcelado = false;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Despesa setCodigoFormaPagamento(int codigoFormaPagamento){
        this.codigoFormaPagamento = codigoFormaPagamento;
        return this;
    }

    public Despesa setQuantidadeParcela(int quantidadeParcela){
        if(quantidadeParcela > 0) {
            this.parcelado = true;
            this.quantidadeParcela = quantidadeParcela;
        }

        return this;
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
