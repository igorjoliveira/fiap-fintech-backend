package fiap.fintech.backend.domain.models;

public enum CategoriaDespesa {
    Alimentacao,
    Transporte,
    Estudo,
    Investimento,
    Diversao,
    Outros;

    public static CategoriaDespesa Converter(int item){
        return switch (item) {
            case 1 -> CategoriaDespesa.Alimentacao;
            case 2 -> CategoriaDespesa.Transporte;
            case 3 -> CategoriaDespesa.Estudo;
            case 4 -> CategoriaDespesa.Investimento;
            case 5 -> CategoriaDespesa.Diversao;
            case 6 -> CategoriaDespesa.Outros;
            default -> null;
        };
    }
}
