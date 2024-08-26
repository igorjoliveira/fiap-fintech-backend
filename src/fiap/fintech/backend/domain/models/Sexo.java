package fiap.fintech.backend.domain.models;

public enum Sexo{
    Masculino,
    Feminino;

    public static Sexo Converter(int item){
        switch (item){
            case 0: return Sexo.Masculino;
            case 1: return Sexo.Feminino;
            default: return null;
        }
    }
}
