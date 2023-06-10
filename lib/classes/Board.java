public class Board {
    private String[][] matriz;

    public Board(int tamanho) {
        matriz = new String[tamanho][tamanho];
        startBoard();
    }

    public String[][] getMatriz() { /* TODO: trocar nome para representar melhor o método */
        return matriz;
    }

    public void setValue(int lin, int col, String value) {
        this.matriz[lin][col] = value;
    }

    private void startBoard() {
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = " ";

            }
        }
    }

    public void showBoard() {
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print("+----");
            }
            System.out.println("+");

            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print("| " + Cores.ANSI_RED + matriz[linha][coluna] + Cores.ANSI_RESET + "  ");
            }
            System.out.println("|");
        }

        for (int coluna = 0; coluna < matriz[0].length; coluna++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

    public static void main(String[] args) {
        Board tabuleiro = new Board(9);
        String[][] position_board = tabuleiro.getMatriz();
        position_board[0][0] = Cores.ANSI_YELLOW + "X"; // TODO: transformar cor em atributo da classe
                                                        // Jogador/item/fakenews
        // position_board[1][1] = 'X';
        // position_board[2][2] = 'X';
        // position_board[3][3] = 'X';
        // position_board[4][4] = 'X';
        // position_board[5][5] = 'X';
        // position_board[6][6] = 'X';
        // position_board[7][7] = 'X';
        // position_board[8][8] = 'X';
        // position_board[8][7] = 'X';

        tabuleiro.showBoard();
    }
}
