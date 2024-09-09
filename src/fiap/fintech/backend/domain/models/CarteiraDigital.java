package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.models.enums.InstituicaoFinanceira;

import java.time.LocalDateTime;
import java.util.*;

public class CarteiraDigital extends BaseModel {
    private int codigoParticipanteControleFinanceiro;
    private InstituicaoFinanceira instituicaoFinanceira;
    private Boolean ativo;
    private List<FormaPagamento> formaPagamentoLista;

    public Boolean getAtivo() {
        return ativo;
    }
    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }
    public int getCodigoParticipanteControleFinanceiro() {
        return codigoParticipanteControleFinanceiro;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
        this.setDataHoraAtualizacao(LocalDateTime.now());
    }

    private CarteiraDigital(){
        super();
        this.formaPagamentoLista = new ArrayList<>();
    }
    public CarteiraDigital(int codigoParticipanteControleFinanceiro, InstituicaoFinanceira instituicaoFinanceira) {
        this();
        this.codigoParticipanteControleFinanceiro = codigoParticipanteControleFinanceiro;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public FormaPagamento adicionarCartaoCredito(String numero, String nome, String dataVencimento, String codigoSeguranca){
        return this.adicionarFormaPagamento(new CartaoCredito(this.getCodigo(), numero, nome, dataVencimento, codigoSeguranca));
    }
    public FormaPagamento adicionarCartaoDebito(String numero, String nome, String dataVencimento){
        return this.adicionarFormaPagamento(new CartaoDebito(this.getCodigo(), numero, nome, dataVencimento));
    }
    public FormaPagamento adicionarContaCorrente(String agencia, String numero, char digito){
        return this.adicionarFormaPagamento(new ContaCorrente(this.getCodigo(), agencia, numero, digito));
    }
    public FormaPagamento adicionarContaPoupanca(String agencia, String numero, char digito){
        return this.adicionarFormaPagamento(new ContaPoupanca(this.getCodigo(), agencia, numero, digito));
    }
    public FormaPagamento adicionarCheque(int numeroTalao, int quantidadeFolha){
        return this.adicionarFormaPagamento(new Cheque(this.getCodigo(), numeroTalao, quantidadeFolha));
    }
    private FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamentoLista.add(formaPagamento);
        return formaPagamento;
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
        var data = new Hashtable<Integer, String>();
        data.put(1, "Exibir detalhes da carteira digitial");
        data.put(2, "Listar Formas de pagamento");
        data.put(3, "Selecionar forma de pagamento");
        data.put(4, "Adicionar Cartão");
        data.put(5, "Adicionar Conta");
        data.put(6, "Adicionar Cheque");
        data.put(99, "Voltar");

        return data;
    }
}
