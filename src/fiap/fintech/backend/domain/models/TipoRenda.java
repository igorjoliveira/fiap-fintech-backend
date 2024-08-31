package fiap.fintech.backend.domain.models;

public enum TipoRenda {
    Salario,
    ValeAlimentacao,
    ValeRestaurante,
    Outros;

    public static TipoRenda Converter(int item){
        return switch (item) {
            case 1 -> TipoRenda.Salario;
            case 2 -> TipoRenda.ValeAlimentacao;
            case 3 -> TipoRenda.ValeRestaurante;
            case 4 -> TipoRenda.Outros;
            default -> null;
        };
    }
}
