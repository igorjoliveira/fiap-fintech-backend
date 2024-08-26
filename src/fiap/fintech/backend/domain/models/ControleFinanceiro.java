package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControleFinanceiro extends BaseModel {

    private int codigoProprietario;
    private String descricao;
    private Boolean ativo;
    private List<ParticipanteControleFinanceiro> participanteLista;

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
    public List<ParticipanteControleFinanceiro> getParticipanteLista() { return participanteLista; }

    public ControleFinanceiro adicionarParticipante(int codigoUsuario) throws EntradaDadoInvalidaException {
        for (var participante : this.participanteLista){
            if(participante.getCodigoUsuario() == codigoUsuario){
                throw new EntradaDadoInvalidaException(String.format("Usuario já faz parte do controle financeiro!"));
            }
        }

        participanteLista.add(new ParticipanteControleFinanceiro(codigoUsuario, this.getCodigo(), true));
        return this;
    }
    public ParticipanteControleFinanceiro obterParticipante(int codigoUsuario) throws EntradaDadoInvalidaException {
        for (var item : this.participanteLista){
            if(item.getCodigoUsuario() == codigoUsuario)
                return item;
        }

        throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum participante para o código %d", codigoUsuario));
    }
    public String listarParticipantes(){
        var mensagem = new StringBuilder();
        for (var participante : this.participanteLista){
            mensagem.append(participante.exibirResumo());
        }

        return mensagem.toString();
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Exibir detalhes do controle financeiro");
        data.put(2, "Listar participantes");
        data.put(3, "Adicionar participante");
        data.put(4, "Selecionar Participante");
        data.put(99, "Voltar");

        return data;
    }
    @Override
    public String exibirResumo() {
        return String.format("Resumo do controle financeiro:\n%d - %s", this.getCodigo(), this.descricao);
    }
    @Override
    public String exibirDetalhado() {
        var mensagem = new StringBuilder();
        mensagem.append("Detalhe do controle financeiro:\n")
                .append("Código: %d\n")
                .append("Código Proprietario: %d\n")
                .append("Descrição: %s\n")
                .append("Ativo: %s\n")
                .append("Data hora cadastro: %s\n")
                .append("Total de participantes: %d\n")
                .append("Participantes ativos: %d\n")
                .append("Participantes inativos: %d\n");

        return String.format(mensagem.toString(),
                this.getCodigo(),
                this.codigoProprietario,
                this.descricao,
                this.ativo,
                this.getDataHoraCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                this.participanteLista.size(),
                this.participanteLista.stream().filter(x -> x.getAtivo()).count(),
                this.participanteLista.stream().filter(x -> !x.getAtivo()).count());
    }
}
