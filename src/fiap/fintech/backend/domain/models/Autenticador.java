package fiap.fintech.backend.domain.models;

public enum Autenticador {
    Interno,
    Externo;

    public static Autenticador Converter(int item){
        switch (item){
            case 0: return Autenticador.Interno;
            case 1: return Autenticador.Externo;
            default: return null;
        }
    }
}
