package fiap.fintech.backend.domain.models;

import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Random;

public abstract class BaseModel{
    private int codigo;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAtualizacao;

    public BaseModel() {
        this.setCodigo((new Random().nextInt(100)) + 1);
    }

    public int getCodigo() { return this.codigo; }
    protected void setCodigo(int codigo) { this.codigo = codigo; }
    public LocalDateTime getDataHoraCadastro() { return dataHoraCadastro; }
    public LocalDateTime getDataHoraAtualizacao() { return dataHoraAtualizacao; }
    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) { this.dataHoraCadastro = dataHoraCadastro; }
    public void setDataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) { this.dataHoraAtualizacao = dataHoraAtualizacao; }

    public abstract String exibirResumo();
    public abstract String exibirDetalhado();
    public abstract Dictionary<Integer, String> getAcoes();

    public String obterAcao(int acao) throws EntradaDadoInvalidaException {
        var keys = this.getAcoes().keys();
        while (keys.hasMoreElements()){
            var key = keys.nextElement();
            if(key == acao){
                return this.getAcoes().get(key);
            }
        }

        throw new EntradaDadoInvalidaException("Ação inválida!");
    }
}
