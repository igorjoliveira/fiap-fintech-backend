package fiap.fintech.backend.domain.models;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.List;

public class CarteiraDigital extends BaseModel {
    private int codigoParticipanteControleFinanceiro;
    private InstituicaoFinanceira instituicaoFinanceira;
    private Boolean ativo;
    private List<FormaPagamento> formaPagamentos;

    public Boolean getAtivo() {
        return ativo;
    }
    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }
    public int getCodigoParticipanteControleFinanceiro() {
        return codigoParticipanteControleFinanceiro;
    }

    public CarteiraDigital(int codigoParticipanteControleFinanceiro, InstituicaoFinanceira instituicaoFinanceira) {
        super();
        this.codigoParticipanteControleFinanceiro = codigoParticipanteControleFinanceiro;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public CarteiraDigital adicionarFormaPagamento(){ return this; }

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
