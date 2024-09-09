package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.CategoriaDespesa;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Despesa(int codigoParticipante, String descricao, int codigoFormaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada) {
        super();
        this.codigoParticipante = codigoParticipante;
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.descricao = descricao;
        this.valor = valor;
        this.categoriaDespesa = categoriaDespesa;
        this.dataHoraRealizada = dataHoraRealizada;
        this.parcelado = false;
        this.setDataHoraCadastro(LocalDateTime.now());
    }
    public Despesa(int codigoParticipante, String descricao, int codigoFormaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada, int quantidadeParcela) {
        this(codigoParticipante, descricao, codigoFormaPagamento, valor, categoriaDespesa, dataHoraRealizada);
        this.parcelado = true;
        this.quantidadeParcela = quantidadeParcela;
    }

    @Override
    public String exibirResumo() {
        return String.format("Descrição: %s - Valor: R$%.2f - Categoria: %s", this.getDescricao(), this.getValor(), this.getCategoriaDespesa());
    }
    @Override
    public String exibirDetalhe() {
        var mensagem = "Descrição: %s\n" +
                "Valor: R$%.2f\n" +
                "Categoria: %s\n" +
                "Forma de Pagamento: %d\n" +
                "Data hora compra: %s\n";

        return String.format(mensagem,
                this.getDescricao(),
                this.getValor(),
                this.getCategoriaDespesa(),
                this.getCodigoFormaPagamento(),
                this.dataHoraRealizada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
