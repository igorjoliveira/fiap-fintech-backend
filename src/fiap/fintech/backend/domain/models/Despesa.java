package fiap.fintech.backend.domain.models;

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
