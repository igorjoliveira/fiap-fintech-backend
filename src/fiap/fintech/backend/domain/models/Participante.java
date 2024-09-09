package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.InstituicaoFinanceira;
import fiap.fintech.backend.domain.models.enums.TipoRenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
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

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Renda adicionarRenda(TipoRenda tipoRenda, double valorBruto, Boolean ativo){
        var renda = new Renda(this.getCodigo(), tipoRenda, valorBruto, ativo);
        this.rendaLista.add(renda);
        return renda;
    }
    public CarteiraDigital adicionarCarteiraDigital(InstituicaoFinanceira instituicaoFinanceira){
        var carteira = new CarteiraDigital(this.getCodigo(), instituicaoFinanceira);
        this.carteiraDigitalLista.add(carteira);
        return carteira;
    }

    @Override
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Exibir detalhes do participante");
        data.put(2, "Ativar");
        data.put(3, "Desativar");
        data.put(4, "Listar renda");
        data.put(5, "Adicionar renda");
        data.put(6, "Selecionar renda");
        data.put(99, "Voltar");

        return data;
    }
    @Override
    public String exibirResumo() {
        return String.format("Participante: %d - Usuario: %d - Ativo: %s\n", this.getCodigo(), this.codigoUsuario, this.ativo);
    }
    @Override
    public String exibirDetalhado() {
        var mensagem = new StringBuilder();
        mensagem.append("Codigo: %d\n")
                .append("Codigo: %d\n")
                .append("Codigo Usuário: %d\n")
                .append("Ativo: %s\n")
                .append("Total renda: %d\n")
                .append("Total renda ativa: %d\n")
                .append("Total renda inativo: %d\n")
                .append("Valor bruto R$%.2f\n");

        return String.format(mensagem.toString(),
                this.getCodigo(),
                this.codigoControleFinanceiro,
                this.codigoUsuario,
                this.ativo,
                this.rendaLista.size(),
                this.rendaLista.stream().filter(x -> x.getAtivo()).count(),
                this.rendaLista.stream().filter(x -> !x.getAtivo()).count(),
                this.rendaLista.stream().filter(x -> x.getAtivo()).mapToDouble(Renda::getValorBruto).sum());
    }
}
