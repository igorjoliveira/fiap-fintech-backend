package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControleFinanceiro extends BaseModel {

    private int codigoProprietario;
    private String descricao;
    private Boolean ativo;
    private List<Participante> participanteLista;

    private ControleFinanceiro(){
        super();
        this.participanteLista = new ArrayList<>();
    }
    public ControleFinanceiro(int codigoProprietario, String descricao) {
        this();
        this.codigoProprietario = codigoProprietario;
        this.descricao = descricao;
        this.ativo = true;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Boolean getAtivo() { return ativo; }
    public String getDescricao() {
        return descricao;
    }
    public int getCodigoProprietario() { return codigoProprietario; }
    public List<Participante> getParticipanteLista() { return participanteLista; }

    public ControleFinanceiro adicionarParticipante(int codigoUsuario) throws EntradaDadoInvalidaException {
        for (var participante : this.participanteLista){
            if(participante.getCodigoUsuario() == codigoUsuario){
                throw new EntradaDadoInvalidaException("Usuario já faz parte do controle financeiro!");
            }
        }

        participanteLista.add(new Participante(codigoUsuario, this.getCodigo(), true));
        return this;
    }
    public Participante obterParticipante(int codigoUsuario) throws EntradaDadoInvalidaException {
        for (var item : this.participanteLista){
            if(item.getCodigoUsuario() == codigoUsuario)
                return item;
        }

        throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum participante para o código %d", codigoUsuario));
    }

    @Override
    public String exibirResumo() {
        return String.format("Resumo do controle financeiro:\n%d - %s", this.getCodigo(), this.descricao);
    }
    @Override
    public String exibirDetalhe() {
        String mensagem = "Detalhe do controle financeiro:\n" +
                "Código: %d\n" +
                "Código Proprietario: %d\n" +
                "Descrição: %s\n" +
                "Ativo: %s\n" +
                "Data hora cadastro: %s\n" +
                "Total de participantes: %d\n" +
                "Participantes ativos: %d\n" +
                "Participantes inativos: %d\n";

        return String.format(mensagem,
                this.getCodigo(),
                this.getCodigoProprietario(),
                this.getDescricao(),
                this.getAtivo(),
                this.getDataHoraCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                this.getParticipanteLista().size(),
                this.getParticipanteLista().stream().filter(Participante::getAtivo).count(),
                this.getParticipanteLista().stream().filter(x -> !x.getAtivo()).count());
    }
}
