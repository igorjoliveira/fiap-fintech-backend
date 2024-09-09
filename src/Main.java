
import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;
import fiap.fintech.backend.domain.models.*;
import fiap.fintech.backend.domain.models.Anonimo;
import fiap.fintech.backend.domain.models.Participante;
import fiap.fintech.backend.domain.models.Usuario;
import fiap.fintech.backend.domain.models.enums.Autenticador;
import fiap.fintech.backend.domain.models.enums.InstituicaoFinanceira;
import fiap.fintech.backend.domain.models.enums.Sexo;
import fiap.fintech.backend.domain.models.enums.TipoRenda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            var usuario = new Usuario("igor", "oliveira", Sexo.Masculino, LocalDate.parse("23/06/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "igorjoliveira@outlook.com");
            usuario.definirAutenticador(Autenticador.Externo, null);
            System.out.println(usuario.exibirDetalhado());

            usuario.criarControleFinanceiro("controle 2024");
            var controleFinanceiro = usuario.getControleFinanceiroLista().get(0);
            System.out.println(controleFinanceiro.exibirDetalhado());

            var participante = controleFinanceiro.obterParticipante(usuario.getCodigo());
            participante.adicionarRenda(TipoRenda.Salario, 2500, true);
            participante.adicionarRenda(TipoRenda.ValeAlimentacao, 500, true);
            participante.adicionarRenda(TipoRenda.ValeRestaurante, 300, true);
            System.out.println(participante.exibirDetalhado());

            var renda = participante.getRendaLista().get(1);
            renda.setAtivo(false);
            System.out.println(renda.exibirDetalhado());

            renda = participante.getRendaLista().get(2);
            renda.setValorBruto(600);
            renda.setTipoRenda(TipoRenda.Outros);
            System.out.println(renda.exibirDetalhado());

            var carteiraItau = participante.adicionarCarteiraDigital(InstituicaoFinanceira.Itau);
            //carteiraItau.adicionarContaCorrente("3004", "01087607", '6');
            //carteiraItau.adicionarCartaoDebito()
            //((CartaoCredito)carteiraItau.adicionarCartaoCredito()).atualizarValorLimite(5000);
            //carteiraItau.adicionarCheque()

            var carteiraSantader = participante.adicionarCarteiraDigital(InstituicaoFinanceira.Santander);
            //carteiraSantader.adicionarContaCorrente("3004", "01087607", '6');
            //carteiraSantader.adicionarCartaoDebito()
            //((CartaoCredito)carteiraSantader.adicionarCartaoCredito()).atualizarValorLimite(5000);
            //carteiraSantader.adicionarCheque()
            
            System.out.println(participante.exibirDetalhado());

        } catch (EntradaDadoInvalidaException e) {
            throw new RuntimeException(e);
        }
    }
}