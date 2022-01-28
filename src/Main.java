
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int qtdTemas = 5;
    static Integer[] qtdPvsTema = new Integer[50];

    public static void main(String[] args) {
        String[][] listaGeralStrings = new String[50][51];
        memoriaGravada(listaGeralStrings);
        Scanner ler = new Scanner(System.in);
        String menu;
        do {
            System.out.println(" 1. Gerenciar Temas\n 2. Gerenciar Palavras\n 3. Jogar\n 4. Sair");
            menu = ler.nextLine();
            menuP(menu, listaGeralStrings);
        } while (menu != "4");
    }

    public static void menuP(String menu, String[][] listaGeralStrings) {
        switch (menu) {
            case "1":
                Temas(listaGeralStrings);
                break;
            case "2":
                Palavras(listaGeralStrings);
                break;
            case "3":
                josgo(listaGeralStrings);
                break;
            case "4":
                System.out.println("Obrigado Por Jogar!");
                System.exit(0);
                break;
            default:
                System.out.println("Operaçao invalida");
                break;
        }
    }

    public static int Temas(String[][] listaGeralStrings) {
        System.out.println("1.Cadastrar Temas\n 2.Excluir Temas\n 3.Procurar Temas\n 4.voltar");
        String novoTema;
        Scanner ler = new Scanner(System.in);
        String TemasIn = ler.nextLine();
        switch (TemasIn) {
            case "1":
                do {
                    System.out.println("Escolha um novo tema para ser cadastrado ou aperte 4 para voltar");
                    novoTema = ler.nextLine();
                    if (!novoTema.equals("4")) {
                        int temaExistente = buscaTemasEspecial(listaGeralStrings, TemasIn);
                        if (temaExistente == -1) {
                            listaGeralStrings[qtdTemas][0] = novoTema;
                            qtdTemas++;
                        } else {
                            System.out.println("Tema ja cadastrado");
                        }
                    }
                } while (!novoTema.equals("4"));
                break;
            case "2":
                System.out.println("digite o tema a ser excluido");
                int temaAExcluir = buscaTemas(listaGeralStrings);
                if (temaAExcluir != -1) {
                    System.out.println("O tema foi excluido");
                    if (temaAExcluir == qtdTemas) {
                        listaGeralStrings[temaAExcluir][0] = null;
                    } else {
                        for (int i = temaAExcluir; i <= qtdTemas; i++) {
                            listaGeralStrings[i][0] = listaGeralStrings[i + 1][0];
                            qtdTemas--;
                        }
                    }
                } else {
                    System.out.println("Tema nao encontrado");
                }

                for (int i = 0; i <= qtdTemas; i++) {
                    System.out.println(listaGeralStrings[i][0]);
                }
                break;
            case "3":
                System.out.println("digite o tema a ser procurado");
                int temaEncontrado = buscaTemas(listaGeralStrings);
                if (temaEncontrado != -1) {
                    System.out.println("O tema " + listaGeralStrings[temaEncontrado][0] + " foi encontrado");
                } else {
                    System.out.println("Tema nao encontrado");
                }
                break;
            case "4":
                System.out.println("loading!");
                break;
            case "5":
                for (int i = 0; i < listaGeralStrings.length; i++) {
                    System.out.println(listaGeralStrings[i][0]);
                }
                break;
            default:
                System.out.println("opçao invalida");
                break;
        }
        return qtdTemas;
    }

    public static void Palavras(String[][] listaGeralStrings) {
        System.out.println(
                "1.Cadastrar palavras\n 2.Excluir palavras\n 3.Buscar Palavras\n 4.Ver palavras associadas a um tema\n 5.Voltar");
        Scanner ler = new Scanner(System.in);
        String escolha = ler.nextLine();
        switch (escolha) {
            case "1":
                System.out.println("digite o tema que se relacionara a palavra");
                String temaUsado = ler.nextLine();
                int temaEncontrado = buscaTemasEspecial(listaGeralStrings, temaUsado);
                if (temaEncontrado != -1) {
                    System.out.println("digite a nova Palavra ou 4 para cancelar");
                    String novaPalavra = ler.nextLine();
                    if (!novaPalavra.equals("4")) {
                        listaGeralStrings[temaEncontrado][qtdPvsTema[temaEncontrado] + 1] = novaPalavra;
                        qtdPvsTema[temaEncontrado]++;
                    }
                } else {
                    System.out.println("Tema nao encontrado");
                }
                break;
            case "2":
                System.out.println("digite o tema em que se encontra a palavra a ser excluida");
                int temaAExcluir = buscaTemas(listaGeralStrings);
                if (temaAExcluir != -1) {
                    System.out.println("digite a palavra a ser excluida");
                    int palavraEncontrada = buscaPalavras(listaGeralStrings, temaAExcluir);
                    if (palavraEncontrada != -1) {
                        if (palavraEncontrada == qtdPvsTema[temaAExcluir] - 1) {
                            listaGeralStrings[temaAExcluir][palavraEncontrada] = null;
                            qtdPvsTema[temaAExcluir]--;
                        } else {
                            for (int i = palavraEncontrada; i <= qtdPvsTema[temaAExcluir]; i++) {
                                listaGeralStrings[temaAExcluir][i] = listaGeralStrings[temaAExcluir][i + 1];
                            }
                        }
                        System.out.println("A palavra foi excluida");
                        qtdPvsTema[temaAExcluir]--;
                    } else {
                        System.out.println("palavra nao encontrada");
                    }
                } else {
                    System.out.println("Tema nao encontrado");
                }
                break;
            case "3":
                System.out.println("digite a palavra a ser procurada");
                String palavraProcurada = ler.nextLine();
                if (!buscaPalavrasGeral(listaGeralStrings, palavraProcurada)) {
                    System.out.println("nenhuma palavra encontrada");
                }
                break;
            case "4":
                System.out.println("digite o tema de interesse");
                String temaString = ler.nextLine();
                int temaProcurado = buscaTemasEspecial(listaGeralStrings, temaString);
                if (temaProcurado != -1) {
                    System.out.println("palavras encontradas no tema:");
                    for (int i = 1; i <= qtdPvsTema[temaProcurado]; i++) {
                        System.out.println(listaGeralStrings[temaProcurado][i]);
                    }
                } else {
                    System.out.println("Tema nao encontrado");
                }
                break;
            case "5":
                System.out.println("loading");
                break;
            default:
                System.out.println("Operaçao invalida");
                break;
        }
    }

    public static void josgo(String[][] listaGeralStrings) {
        System.out.println("1.Selecionar Tema e começar o jogo\n 2 para voltar");
        Scanner ler = new Scanner(System.in);
        String escolha = ler.nextLine();
        int maxErros = 5;
        switch (escolha) {
            case "1":
                System.out.println("Escolha um tema disponivel");
                String temaDoJogo = ler.nextLine();
                int k = buscaTemasEspecial(listaGeralStrings, temaDoJogo);
                int h = 0;
                int w = 0;
                int qtdDigitadas = 0;
                Random palavra = new Random();
                if (k != -1) {
                    String sorteada = listaGeralStrings[k][palavra.nextInt(qtdPvsTema[k])];
                    Character[] digitadas = new Character[sorteada.length() + 5];
                    if (listaGeralStrings[k][0].equals(temaDoJogo)) {
                        System.out.println("O tema selecionado foi: " + listaGeralStrings[k][0]
                                + " digite 1 para continuar ou 2 para voltar ao menu principal");
                        String jogo = ler.nextLine();
                        switch (jogo) {
                            case "1":
                                while (h < maxErros) {
                                    if (w == sorteada.length()) {
                                        System.out.println(
                                                "parabens! você ganhou, digite 1 para jogar novamente ou 0 para sair");
                                        String lw;
                                        do {
                                            lw = ler.next();

                                            switch (lw.charAt(0)) {
                                                case '1':
                                                    josgo(listaGeralStrings);
                                                    break;
                                                case '0':
                                                    System.out.println("bem jogado!");
                                                    break;
                                                default:
                                                    System.out.println("opçao invalida digite 1 ou 0");
                                                    break;
                                            }
                                        } while (lw.charAt(0) != '1' && lw.charAt(0) != '0');
                                    }
                                    System.out.println("escolha a letra que pode estar na palavra");
                                    char letra = ler.next().charAt(0);
                                    if (verificaRepetidas(digitadas, letra)) {
                                        System.out.println("letra repetida");
                                    } else {
                                        digitadas[qtdDigitadas] = letra;
                                        qtdDigitadas++;
                                        int l = 0;
                                        for (int i = 0; i < sorteada.length(); i++) {
                                            if (letra == sorteada.charAt(i)) {
                                                System.out.println("letra " + letra + " encontrada!");
                                                l = 1;
                                                w++;
                                            }
                                        }
                                        if (l == 0) {
                                            System.out.println("letra nao encontrada");
                                            h++;
                                        }
                                        if (h == maxErros) {
                                            System.out
                                                    .println(
                                                            "Você perdeu! digite 1 para tentar novamente ou 0 para sair");
                                            String wl;
                                            do {
                                                wl = ler.next();

                                                switch (wl.charAt(0)) {
                                                    case '1':
                                                        josgo(listaGeralStrings);
                                                        break;
                                                    case '0':
                                                        System.out.println("bem jogado!");
                                                        break;
                                                    default:
                                                        System.out.println("opçao invalida digite 1 ou 0");
                                                        break;
                                                }

                                            } while (wl.charAt(0) != '1' && wl.charAt(0) != '0');
                                        }
                                    }
                                }
                                break;
                            case "2":
                                break;
                            default:
                                System.out.println("opçao invalida");
                                break;
                        }
                    }
                } else {
                    System.out.println("tema nao encontrado");
                    josgo(listaGeralStrings);
                }
                break;
            case "2":
                System.out.println("loading");
                break;
            default:
                System.out.println("Operaçao invalida");
                break;
        }
    }

    public static int buscaPalavras(String[][] listaGeralStrings, int temaSelecionado) {
        Scanner ler = new Scanner(System.in);
        String buscar = ler.nextLine();
        int G = -1;
        for (int i = 1; i <= qtdPvsTema[temaSelecionado]; i++) {
            if (listaGeralStrings[temaSelecionado][i].equalsIgnoreCase(buscar)) {
                G = i;
                break;
            }
        }
        return G;

    }

    public static boolean buscaPalavrasGeral(String[][] listaGeralStrings, String palavras) {
        boolean P = false;
        for (int i = 0; i < qtdTemas; i++) {
            for (int j = 1; j <= qtdPvsTema[i]; j++) {
                if (palavras != null) {
                    if (listaGeralStrings[i][j].equals(palavras)) {
                        P = true;
                        System.out.println("palavra encontrada no tema: " + listaGeralStrings[i][0]);
                    }
                }
            }
        }
        return P;
    }

    public static int buscaTemas(String[][] listaGeralStrings) {
        Scanner ler = new Scanner(System.in);
        String buscar = ler.nextLine();
        int T = -1;
        for (int i = 0; i < qtdTemas; i++) {
            if (listaGeralStrings[i][0].equalsIgnoreCase(buscar)) {
                T = i;
                break;
            }
        }
        return T;
    }

    public static int buscaTemasEspecial(String[][] listaGeralStrings, String buscar) {
        int T = -1;
        for (int i = 0; i < qtdTemas; i++) {
            if (listaGeralStrings[i][0].equalsIgnoreCase(buscar)) {
                T = i;
                break;
            }
        }
        return T;
    }

    public static boolean verificaRepetidas(Character[] digitadas, Character outro) {
        int i = 0;
        while (digitadas[i] != null) {
            if (digitadas[i].equals(outro)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static void memoriaGravada(String[][] listaGeralStrings) {
        for (int i = 0; i < qtdPvsTema.length; i++) {
            qtdPvsTema[i] = 0;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 10; j++) {
                listaGeralStrings[i][j] = "palavra" + j;
                qtdPvsTema[i]++;
            }
            listaGeralStrings[i][0] = "tema" + i;
        }
    }
}