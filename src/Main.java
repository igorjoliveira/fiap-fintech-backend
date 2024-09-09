
import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;
import fiap.fintech.backend.domain.models.*;
import fiap.fintech.backend.domain.models.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        try {
            var participante = criarObjetos();

            System.out.println(participante.exibirDetalhe());
            System.out.printf("Total de renda ativa: R$%.2f\n", participante.getTotalRendaAtiva());
            System.out.printf("Total de despesa: R$%.2f\n", participante.getTotalDespesa());
            System.out.printf("Total de despesa (%s): R$%.2f\n", CategoriaDespesa.Alimentacao, participante.getTotalDespesa(CategoriaDespesa.Alimentacao));

            for(var despesa : participante.getDespesas(CategoriaDespesa.Diversao)){
                System.out.println(despesa.exibirDetalhe());
            }

            for(var carteira : participante.getCarteiraDigitalLista()){
                System.out.println(carteira.exibirDetalhe());
            }

            var carteira = participante.getCarteiraDigital(InstituicaoFinanceira.Santander);
            for (var formaPagamento : carteira.getFormaPagamentoLista()){
                for(var despesa : participante.getDespesas(formaPagamento)){
                    System.out.println(despesa.exibirDetalhe());
                }
            }
        } catch (EntradaDadoInvalidaException e) {
            throw new RuntimeException(e);
        }
    }

    private static Participante criarObjetos() throws EntradaDadoInvalidaException {
        var usuario = new Usuario("igor", "oliveira", Sexo.Masculino, LocalDate.parse("23/06/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "igorjoliveira@fiap.com");
        usuario.definirAutenticador(Autenticador.Externo, null);

        usuario.criarControleFinanceiro("controle 2024");
        var controleFinanceiro = usuario.getControleFinanceiroLista().getFirst();

        var participante = controleFinanceiro.obterParticipante(usuario.getCodigo())
                .adicionarRenda(TipoRenda.Salario, 2500, true)
                .adicionarRenda(TipoRenda.ValeAlimentacao, 500, true)
                .adicionarRenda(TipoRenda.ValeRestaurante, 300, true);

        var renda = participante.getRendaLista().get(1);
        renda.setAtivo(false);

        renda = participante.getRendaLista().get(2);
        renda.setValorBruto(600);
        renda.setTipoRenda(TipoRenda.Outros);

        participante.adicionarCarteiraDigital(InstituicaoFinanceira.Itau);
        participante.adicionarCarteiraDigital(InstituicaoFinanceira.Santander);

        var instituicao = participante.getCarteiraDigital(InstituicaoFinanceira.Itau);
        var contaCorrente = instituicao.adicionarContaCorrente("0004", "0012345", '6');
        var cheque = (Cheque)instituicao.adicionarCheque(1, 20);

        instituicao = participante.getCarteiraDigital(InstituicaoFinanceira.Santander);
        var cartaoDebito = instituicao.adicionarCartaoDebito("1234-5678-9012-3456", "JOAO M SILVA", "062034");
        var cartaoCredito = (CartaoCredito)instituicao.adicionarCartaoCredito("1234-5678-9012-3456", "JOAO M SILVA", "062034", "378");

        cheque.habilitarTalao();
        cartaoCredito.atualizarValorLimite(10000);

        participante.adicionarDespesa("Pizza", contaCorrente, 140, CategoriaDespesa.Alimentacao, LocalDateTime.now());
        participante.adicionarDespesa("FIAP - Graduação", cartaoCredito, 2500, CategoriaDespesa.Estudo, LocalDateTime.now());
        participante.adicionarDespesa("Futebol", cartaoDebito, 200, CategoriaDespesa.Diversao, LocalDateTime.now());
        participante.adicionarDespesa("Televisão", cartaoCredito, 5000, CategoriaDespesa.Outros, LocalDateTime.now(), 10);

        return participante;
    }
}