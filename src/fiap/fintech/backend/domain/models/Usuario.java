package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;
import fiap.fintech.backend.domain.models.enums.Autenticador;
import fiap.fintech.backend.domain.models.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Usuario extends BaseModel {
    private Sexo sexo;
    private String nome;
    private String email;
    private String senha;
    private String sobreNome;
    private LocalDate dataNascimento;
    private Autenticador autenticador;
    private List<ControleFinanceiro> controleFinanceiroLista;

    private Usuario(){
        super();
        this.controleFinanceiroLista = new ArrayList<>();
    }
    public Usuario(String nome, String sobreNome, Sexo sexo, LocalDate dataNascimento, String email){
        this();
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Sexo getSexo() { return sexo; }
    public String getNome() {
        return nome;
    }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getSobreNome() { return sobreNome; }
    public Autenticador getAutenticador() { return autenticador; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public List<ControleFinanceiro> getControleFinanceiroLista() { return controleFinanceiroLista; }

    public Usuario definirAutenticador(Autenticador autenticador, String senha){
        this.autenticador = autenticador;
        this.senha = senha;
        return this;
    }
    public Boolean validarCredencial(String email, String senha){
        return this.email.equals(email)
                && (this.autenticador == Autenticador.Interno && this.senha.equals(senha)
                || this.autenticador == Autenticador.Externo && senha == null);
    }
    public Usuario criarControleFinanceiro(String descricao) throws EntradaDadoInvalidaException {
        for (var controleFinanceiro : this.controleFinanceiroLista) {
            if(controleFinanceiro.getDescricao().equalsIgnoreCase(descricao)){
                throw new EntradaDadoInvalidaException("Já existe um controle financeiro com essa descrição!");
            }
        }

        var controleFinanceiro = new ControleFinanceiro(this.getCodigo(), descricao);
        controleFinanceiro.adicionarParticipante(this.getCodigo());

        controleFinanceiroLista.add(controleFinanceiro);
        return this;
    }
    public Boolean validarControleFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        return obterControleFinanceiro(codigo) != null;
    }
    public ControleFinanceiro obterControleFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        for (var item : this.controleFinanceiroLista){
            if(item.getCodigo() == codigo)
                return item;
        }

        throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum controle para o código %d", codigo));
    }
    public void removerFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        if(!validarControleFinanceiro(codigo))
            throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum controle para o código %d", codigo));

        this.controleFinanceiroLista.removeIf(item -> item.getCodigo() == codigo);
    }
    public String listarControleFinanceiro(){
        var mensagem = new StringBuilder();
        for (var item : this.controleFinanceiroLista) {
            mensagem.append(item.exibirResumo());
        }

        return mensagem.toString();
    }
    @Override
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Exibir detalhes usuário");
        data.put(2, "Listar controle financeiro");
        data.put(3, "Adicionar controle financeiro");
        data.put(4, "Remover controle financeiro");
        data.put(5, "Selecionar controle financeiro");
        data.put(99, "Sair");

        return data;
    }
    @Override
    public String exibirResumo() {
        return String.format("Resumo do usuário:\n%d - %s - %s", this.getCodigo(), this.nome, this.sexo);
    }
    @Override
    public String exibirDetalhado() {
        var detalhe = new StringBuilder();
        detalhe.append("Detalhe do usuário:\n")
                .append("Código: %d\n")
                .append("Nome completo: %s %s\n")
                .append("Email: %s\n")
                .append("Sexo: %s\n")
                .append("Data de nascimento: %s\n")
                .append("Autenticador: %s\n")
                .append("Total de controle financeiro: %d\n");

        return String.format(detalhe.toString(),
                this.getCodigo(),
                this.nome,
                this.sobreNome,
                this.email,
                this.sexo,
                this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                this.autenticador,
                this.controleFinanceiroLista.size());
    }
}