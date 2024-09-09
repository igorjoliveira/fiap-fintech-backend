package fiap.fintech.backend.domain.models.enums;

public enum InstituicaoFinanceira {
    C6,
    XP,
    Itau,
    Nubank,
    Bradesco,
    Santander;

    public static InstituicaoFinanceira Converter(int item){
        return switch (item) {
            case 1 -> InstituicaoFinanceira.C6;
            case 2 -> InstituicaoFinanceira.XP;
            case 3 -> InstituicaoFinanceira.Itau;
            case 4 -> InstituicaoFinanceira.Nubank;
            case 5 -> InstituicaoFinanceira.Bradesco;
            case 6 -> InstituicaoFinanceira.Santander;
            default -> null;
        };
    }
}
