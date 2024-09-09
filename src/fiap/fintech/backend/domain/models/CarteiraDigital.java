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
    public List<FormaPagamento> getFormaPagamentoLista() { return formaPagamentoLista; }

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
        this.ativo = true;
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
        return String.format("Código participante: %d - Banco: %s - Status: %s", this.getCodigoParticipanteControleFinanceiro(), this.getInstituicaoFinanceira(), this.getAtivo());
    }
    @Override
    public String exibirDetalhe() {
        var mensagem = String.format("Código participante: %d\n" +
                "Banco: %s\n" +
                "Status: %s\n" +
                "Qtde forma de pagamento: %d\n",
                this.getCodigoParticipanteControleFinanceiro(),
                this.getInstituicaoFinanceira(),
                this.getAtivo(),
                this.getFormaPagamentoLista().size());

        for(FormaPagamento formaPagamento : this.getFormaPagamentoLista()){
            mensagem = String.format("%s%s", mensagem, formaPagamento.exibirDetalhe());
        }
        return mensagem;
    }
}
