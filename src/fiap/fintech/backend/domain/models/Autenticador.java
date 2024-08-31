package fiap.fintech.backend.domain.models;

public enum Autenticador {
    Interno,
    Externo;

    public static Autenticador Converter(int item){
        return switch (item) {
            case 1 -> Autenticador.Interno;
            case 2 -> Autenticador.Externo;
            default -> null;
        };
    }
}
