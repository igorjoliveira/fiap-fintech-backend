package fiap.fintech.backend.domain.models;

public enum Sexo{
    Masculino,
    Feminino;

    public static Sexo Converter(int item){
        return switch (item) {
            case 1 -> Sexo.Masculino;
            case 2 -> Sexo.Feminino;
            default -> null;
        };
    }
}
