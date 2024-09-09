package fiap.fintech.backend.domain.models.enums;

public enum TipoPagamento {
    Cartao,
    Cheque,
    Transferencia,
    Outros;

    public static TipoPagamento Converter(int item){
        return switch (item) {
            case 1 -> TipoPagamento.Cartao;
            case 2 -> TipoPagamento.Cheque;
            case 3 -> TipoPagamento.Transferencia;
            case 4 -> TipoPagamento.Outros;
            default -> null;
        };
    }
}
