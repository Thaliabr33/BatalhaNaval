import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {

    // ============================================================
    // CONSTANTES DO JOGO
    // ============================================================
    final static int TAM_MAT = 10;  // Tamanho da matriz do tabuleiro
    final static int MAX_TIROS = 20;  // Limite máximo de tiros
    // IDs dos navios
    final static int LIVRE = 0;  // Posição de água ou espaço livre
    final static int PORTA_AVIOES = 1;  // Identificador do Porta-Aviões
    final static int FRAGATA = 2;  // Identificador da Fragata
    final static int SUBMARINO = 3;  // Identificador do Submarino
    // Resultado do jogo
    final static int EM_ANDAMENTO = 0;  // Jogo em andamento
    final static int VITORIA = 1;  // Vitória do jogador
    final static int DERROTA = 2;  // Derrota do jogador
    // Quantidade de navios (conforme PDF)
    final static int NUM_PORTA_AVIOES = 1;  // Quantidade de porta-aviões
    final static int NUM_FRAGATAS = 5;  // Quantidade de fragatas
    final static int NUM_SUBMARINOS = 5;  // Quantidade de submarinos
    // Pontuação
    final static int PONTOS_PORTA_AVIOES = 100;  // Pontuação por porta-aviões
    final static int PONTOS_FRAGATA = 20;  // Pontuação por fragata
    final static int PONTOS_SUBMARINO = 50;  // Pontuação por submarino

    // ============================================================
    // VARIÁVEIS DO JOGO
    // ============================================================
    static int[][] zonaDeGuerra = new int[TAM_MAT][TAM_MAT];  // Tabuleiro do jogo
    static int contadorTiros = 0;  // Contador de tiros
    static int pontuacao = 0;  // Pontuação total
    static final Random rand = new Random();  // Gerador de números aleatórios
    static final Scanner teclado = new Scanner(System.in);  // Leitor de entrada
    // Ativa ou desativa visualização dos navios
    static boolean modoDebug = false;  // Modo de depuração (visualiza os navios)

    // ============================================================
    // FUNÇÕES AUXILIARES
    // ============================================================
    // Entrada segura
    static int lerInteiro(String msg) {
        while (true) {
            System.out.print(msg);
            String entrada = teclado.next();
            if (entrada.matches("\\d+")) return Integer.parseInt(entrada);  // Verifica se a entrada é um número
            System.out.println("Entrada inválida! Digite um número.");
        }
    }

    // Validação de posições
    static boolean posicaoValida(int x, int y) {
        return x >= 0 && x < TAM_MAT && y >= 0 && y < TAM_MAT;  // Verifica se as coordenadas estão dentro dos limites da matriz
    }

    static boolean posicaoJaAlvejada(int x, int y) {
        return zonaDeGuerra[x][y] >= 10;  // Verifica se a posição já foi alvejada
    }

    static boolean todosNaviosAtingidos() {
        // Verifica se todos os navios foram atingidos
        for (int i = 0; i < TAM_MAT; i++)
            for (int j = 0; j < TAM_MAT; j++)
                if (zonaDeGuerra[i][j] == PORTA_AVIOES ||
                        zonaDeGuerra[i][j] == FRAGATA ||
                        zonaDeGuerra[i][j] == SUBMARINO)
                    return false;
        return true;
    }

    static void atualizarPontuacao(int id) {
        // Atualiza a pontuação com base no tipo de navio atingido
        switch (id) {
            case PORTA_AVIOES: pontuacao += PONTOS_PORTA_AVIOES; break;
            case FRAGATA: pontuacao += PONTOS_FRAGATA; break;
            case SUBMARINO: pontuacao += PONTOS_SUBMARINO; break;
        }
    }

    // ============================================================
    // IMPRESSÃO DO TABULEIRO
    // ============================================================
    public static void imprimeMatriz() {
        // Imprime o tabuleiro com os navios ocultos ou revelados no modo debug
        System.out.print(" ");
        for (int y = 0; y < TAM_MAT; y++) System.out.printf(" %2d", y);  // Cabeçalho das colunas
        System.out.println("\n " + " --".repeat(TAM_MAT));  // Linha divisória
        for (int x = 0; x < TAM_MAT; x++) {
            System.out.printf("%2d |", x);  // Cabeçalho das linhas
            for (int y = 0; y < TAM_MAT; y++) {
                int v = zonaDeGuerra[x][y];
                char simbolo;
                if (v < 10) {
                    if (!modoDebug)
                        simbolo = '~';  // Água não alvejada
                    else
                        simbolo = (char)(v + '0');  // Exibe o ID real no modo debug
                } else {
                    int id = v - 10;
                    simbolo = (id == LIVRE ? '*' : 'X');  // '*' para espaços livres e 'X' para acertos
                }
                System.out.printf(" %2c", simbolo);
            }
            System.out.println();
        }
    }

    // ============================================================
    // POSICIONAMENTO DE NAVIOS
    // ============================================================
    static void posicionarNavios(int tipo, int qtd) {
        while (qtd > 0) {
            int x = rand.nextInt(TAM_MAT);  // Gera uma posição aleatória para o navio
            int y = rand.nextInt(TAM_MAT);  // Gera uma posição aleatória para o navio
            if (zonaDeGuerra[x][y] == LIVRE) {  // Verifica se a posição está livre
                zonaDeGuerra[x][y] = tipo;  // Posiciona o navio na matriz
                qtd--;  // Decrementa a quantidade de navios a serem posicionados
            }
        }
    }

    static void posicionarTodosOsNavios() {
        // Posiciona todos os tipos de navios
        posicionarNavios(PORTA_AVIOES, NUM_PORTA_AVIOES);
        posicionarNavios(FRAGATA, NUM_FRAGATAS);
        posicionarNavios(SUBMARINO, NUM_SUBMARINOS);
    }

    // ============================================================
    // MÉTODO TIRO()
    // ============================================================
    static int tiro() {
        // ESTE MÉTODO FICARÁ PARA OS ALUNOS IMPLEMENTAREM
        // O esqueleto abaixo é apenas a estrutura:
        System.out.println("\n--- TENTATIVA DE TIRO ---");
        // LER COORDENADAS COM PROTEÇÃO
        int x = lerInteiro("Digite a linha (0-9): ");
        int y = lerInteiro("Digite a coluna (0-9): ");
        // TODO: validar posição
        // TODO: verificar repetição
        // TODO: aplicar acerto/erro
        // TODO: atualizar contadorTiros
        // TODO: atualizar pontuacao
        // TODO: verificar vitória/derrota
        return EM_ANDAMENTO;  // Retorna a situação do jogo (ainda em andamento)
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("=== BATALHA NAVAL ===");
        teclado.nextLine();  // Limpar buffer do teclado
        System.out.print("Digite seu nome: ");
        String nome = teclado.nextLine();  // Lê o nome do jogador
        System.out.print("Ativar MODO DEBUG (s/n)? ");
        modoDebug = teclado.next().equalsIgnoreCase("s");  // Ativa o modo debug se o jogador escolher
        posicionarTodosOsNavios();  // Posiciona os navios aleatoriamente
        imprimeMatriz();  // Imprime o tabuleiro inicial (não revela navios no modo normal)
        int situacao;
        do {
            situacao = tiro();  // Chama o método de tiro
            System.out.println("\nPontuação: " + pontuacao);  // Exibe a pontuação
            System.out.println("Tiros restantes: " + (MAX_TIROS - contadorTiros));  // Exibe os tiros restantes
            imprimeMatriz();  // Imprime o tabuleiro atualizado
        } while (situacao == EM_ANDAMENTO);  // Enquanto o jogo estiver em andamento

        // ======================================================
        // Código final do jogo – deixado para os alunos
        // ======================================================
        // Deve exibir:
        // - nome do jogador
        // - tiros utilizados
        // - vitória ou derrota
        // - pontuação final
    }
}
