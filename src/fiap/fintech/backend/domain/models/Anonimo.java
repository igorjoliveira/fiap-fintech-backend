package fiap.fintech.backend.domain.models;

import java.util.Dictionary;
import java.util.Hashtable;

public class Anonimo extends BaseModel {
    @Override
    public Dictionary<Integer, String> getAcoes() {
        var data = new Hashtable<Integer, String>();
        data.put(1, "Cadastrar usuário");
        data.put(2, "Realizar Login");
        data.put(99, "Encerrar programa");

        return data;
    }
    @Override
    public String exibirResumo() {
        return "Seja bem vindo!";
    }
    @Override
    public String exibirDetalhado() {
        return "";
    }
}
