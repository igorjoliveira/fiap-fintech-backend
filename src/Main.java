
import fiap.fintech.backend.domain.common.EntradaDadoInvalidaException;
import fiap.fintech.backend.domain.models.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private static List<Usuario> usuarioLista;
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        usuarioLista = new ArrayList<>();
        var sc = new Scanner(System.in);
        try {
            var anonimmo = new Anonimo();
            navegar(sc, anonimmo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
    private static void navegar(Scanner sc, BaseModel model) {

        var keyEscolhida = 99;
        do {
            System.out.println("Escolha uma das ações a seguir.");

            Map<Integer, String> map = new HashMap<>();
            Enumeration<Integer> keys = model.getAcoes().keys();
            while (keys.hasMoreElements()) {
                Integer key = keys.nextElement();
                map.put(key, model.getAcoes().get(key));
            }

            Map<Integer, String> sortedMap = new TreeMap<>(map);
            for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
                System.out.printf("%d - %s%n", entry.getKey(), entry.getValue());
            }

            try {
                keyEscolhida = sc.nextInt();
                sc.nextLine();
                System.out.println(model.obterAcao(keyEscolhida));

                switch (model){
                    case Anonimo ignored -> {
                        if(keyEscolhida == 1)
                            cadastrarUsuario(sc);
                        else if(keyEscolhida == 2)
                            realizarLogin(sc);
                    }
                    case Usuario ignored -> {
                        if(keyEscolhida == 1) System.out.println(model.exibirDetalhado());
                        else if(keyEscolhida == 2) System.out.println(usuarioLogado.listarControleFinanceiro());
                        else if(keyEscolhida == 3) adicionarControleFinanceiro(sc);
                        else if(keyEscolhida == 4) removerControleFinanceiro(sc);
                        else if(keyEscolhida == 5) selecionarControleFinanceiro(sc);
                        else if(keyEscolhida == 99) usuarioLogado = null;
                    }
                    case ControleFinanceiro ignored -> {
                        if(keyEscolhida == 1) System.out.println(model.exibirDetalhado());
                        else if(keyEscolhida == 2) System.out.println(((ControleFinanceiro)model).listarParticipantes());
                        else if(keyEscolhida == 3) adicionarParticipante(sc, model);
                        else if(keyEscolhida == 4) selecionarParticipante(sc, model);
                    }
                    case ParticipanteControleFinanceiro ignored -> {
                        if(keyEscolhida == 1) System.out.println(model.exibirDetalhado());
                    }
                    default -> { }
                }
            } catch (EntradaDadoInvalidaException ex) {
                keyEscolhida = -1;
                System.out.println(ex.getMessage());
            }
        } while (keyEscolhida != 99);
    }
    private static void cadastrarUsuario(Scanner sc){
        System.out.println("Seja bem vindo! por favor informar os dados solicitados.");

        System.out.println("Qual o seu primeiro nome?");
        var nome = sc.nextLine();

        System.out.println("Qual o seu sobrenome?");
        var sobrenome = sc.nextLine();

        System.out.println("Qual o seu sexo?");
        var codigoSexo = 0;
        for(var item : Sexo.values()){
            System.out.printf("%d - %s \n", ++codigoSexo, item);
        }
        var sexo = Sexo.Converter(sc.nextInt());
        sc.nextLine();

        System.out.println("Qual a sua data de nascimento (dd/mm/yyyy)?");
        var dataNascimento = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println("Qual o seu e-mail?");
        var email = sc.nextLine();

        var usuario = new Usuario(nome, sobrenome, sexo, dataNascimento, email);
        System.out.println("Escolha a forma de autenticação:");
        var codigoAutenticador = 0;
        for (var item : Autenticador.values()){
            System.out.printf("%d - %s \n", ++codigoAutenticador, item);
        }
        var autenticador = Autenticador.Converter(sc.nextInt());
        sc.nextLine();

        String senha = null;
        if(autenticador == Autenticador.Interno){
            System.out.println("Qual a sua senha?");
            senha = sc.nextLine();
        }

        usuario.definirAutenticador(autenticador, senha);

        System.out.println(usuario.exibirResumo());
        usuarioLista.add(usuario);
    }
    private static void realizarLogin(Scanner sc){

        System.out.println("Escolha a forma de autenticação:");
        var codigoAutenticador = 0;
        for (var item : Autenticador.values()){
            System.out.printf("%d - %s \n", ++codigoAutenticador, item);
        }
        var autenticador = Autenticador.Converter(sc.nextInt());
        sc.nextLine();

        System.out.println("Digite o seu e-mail:");
        var email = sc.nextLine();

        String senha = null;
        if(autenticador == Autenticador.Interno){
            System.out.println("Digite a sua senha:");
            senha = sc.nextLine();
        }

        for (var usuario : usuarioLista){
            if(usuario.validarCredencial(email, senha))
                usuarioLogado = usuario;
        }

        if(usuarioLogado != null){
            System.out.println("Autenticação realizada com sucesso!");
            System.out.printf("Seja bem vindo (a) %s%n", usuarioLogado.getNome());
            navegar(sc, usuarioLogado);
        }
        else
            System.out.println("Não foi possível realizar login!");
    }
    private static void adicionarControleFinanceiro(Scanner sc) throws EntradaDadoInvalidaException {
        System.out.println("Digite o nome do controle financeiro");
        usuarioLogado.criarControleFinanceiro(sc.nextLine());
        System.out.println("Controle financeiro adicionado com sucesso!");
    }
    private static void removerControleFinanceiro(Scanner sc) throws EntradaDadoInvalidaException {
        System.out.println("Digite o código do controle financeiro");
        usuarioLogado.removerFinanceiro(sc.nextInt());
        sc.nextLine();
        System.out.println("Controle financeiro excluído com sucesso!");
    }
    private static void selecionarControleFinanceiro(Scanner sc) throws EntradaDadoInvalidaException {
        System.out.println("Digite o código do controle financeiro");
        var controleFinanceiro = usuarioLogado.obterControleFinanceiro(sc.nextInt());
        sc.nextLine();
        navegar(sc, controleFinanceiro);
    }
    private static void adicionarParticipante(Scanner sc, BaseModel model) throws EntradaDadoInvalidaException {
        System.out.println("Digite o código do usuário que deseja adicionar");
        ((ControleFinanceiro)model).adicionarParticipante(sc.nextInt());
        sc.nextLine();
        System.out.println("Participante adicionado com sucesso!");
    }
    private static void selecionarParticipante(Scanner sc, BaseModel model) throws EntradaDadoInvalidaException {
        System.out.println("Digite o código do usuário");
        var participante = ((ControleFinanceiro)model).obterParticipante(sc.nextInt());
        sc.nextLine();
        navegar(sc, participante);
    }
}