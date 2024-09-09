package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.CategoriaDespesa;
import fiap.fintech.backend.domain.models.enums.InstituicaoFinanceira;
import fiap.fintech.backend.domain.models.enums.TipoRenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Participante extends BaseModel {
    private int codigoUsuario;
    private int codigoControleFinanceiro;
    private Boolean ativo;
    private List<Renda> rendaLista;
    private List<Despesa> despesaLista;
    private List<CarteiraDigital> carteiraDigitalLista;

    private Participante(){
        super();
        this.rendaLista = new ArrayList<>();
        this.despesaLista = new ArrayList<>();
        this.carteiraDigitalLista = new ArrayList<>();
    }
    public Participante(int codigoUsuario, int codigoControleFinanceiro, Boolean ativo) {
        this();
        this.codigoUsuario = codigoUsuario;
        this.codigoControleFinanceiro = codigoControleFinanceiro;
        this.ativo = ativo;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public int getCodigoUsuario() { return codigoUsuario; }
    public int getCodigoControleFinanceiro() { return codigoControleFinanceiro; }
    public Boolean getAtivo() { return ativo; }
    public List<Renda> getRendaLista() { return rendaLista; }
    public List<Despesa> getDespesaLista() { return despesaLista; }
    public List<CarteiraDigital> getCarteiraDigitalLista() { return carteiraDigitalLista; }

    public Participante adicionarRenda(TipoRenda tipoRenda, double valorBruto, Boolean ativo){
        var renda = new Renda(this.getCodigo(), tipoRenda, valorBruto, ativo);
        this.rendaLista.add(renda);
        return this;
    }
    public Participante adicionarCarteiraDigital(InstituicaoFinanceira instituicaoFinanceira){
        var carteira = new CarteiraDigital(this.getCodigo(), instituicaoFinanceira);
        this.carteiraDigitalLista.add(carteira);
        return this;
    }
    public Participante adicionarDespesa(String descricao, FormaPagamento formaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada){
        var despesa = new Despesa(this.getCodigo(), descricao, formaPagamento.getCodigo(), valor, categoriaDespesa, dataHoraRealizada);
        this.despesaLista.add(despesa);
        return this;
    }
    public Participante adicionarDespesa(String descricao, FormaPagamento formaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada, int quantidadeParcela){
        var despesa = new Despesa(this.getCodigo(), descricao, formaPagamento.getCodigo(), valor, categoriaDespesa, dataHoraRealizada, quantidadeParcela);
        this.despesaLista.add(despesa);
        return this;
    }

    public CarteiraDigital getCarteiraDigital(InstituicaoFinanceira instituicaoFinanceira){
        var items = this.getCarteiraDigitalLista().stream().filter(item -> item.getInstituicaoFinanceira() == instituicaoFinanceira).findFirst();
        return items.orElse(null);
    }
    public double getTotalRendaAtiva() { return this.getRendaLista().stream().filter(Renda::getAtivo).mapToDouble(Renda::getValorBruto).sum(); }
    public double getTotalDespesa(){
        return this.getDespesaLista().stream().mapToDouble(Despesa::getValor).sum();
    }
    public double getTotalDespesa(CategoriaDespesa categoriaDespesa){ return this.getDespesaLista().stream().filter(item -> item.getCategoriaDespesa() == categoriaDespesa).mapToDouble(Despesa::getValor).sum();}
    public List<Despesa> getDespesas(CategoriaDespesa categoriaDespesa){ return this.getDespesaLista().stream().filter(item -> item.getCategoriaDespesa() == categoriaDespesa).toList();}
    public List<Despesa> getDespesas(FormaPagamento formaPagamento){ return this.getDespesaLista().stream().filter(item -> item.getCodigoFormaPagamento() == formaPagamento.getCodigo()).toList();}

    @Override
    public String exibirResumo() {
        return String.format("Participante: %d - Usuario: %d - Ativo: %s\n", this.getCodigo(), this.codigoUsuario, this.ativo);
    }
    @Override
    public String exibirDetalhe() {
        var mensagem = "Codigo Usuário: %d\n" +
                "Codigo Participante: %d\n" +
                "Codigo Controle Financeiro: %d\n" +
                "Ativo: %s\n" +
                "Total renda: %d\n" +
                "Total despesa: %d\n" +
                "Total carteira: %d\n";

        return String.format(mensagem,
                this.getCodigo(),
                this.getCodigoControleFinanceiro(),
                this.getCodigoUsuario(),
                this.getAtivo(),
                this.getRendaLista().size(),
                this.getDespesaLista().size(),
                this.getCarteiraDigitalLista().size());
    }
}
