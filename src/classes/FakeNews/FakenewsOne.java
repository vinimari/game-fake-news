package classes.FakeNews;

import java.util.Random;

import classes.Cores;


public class FakenewsOne extends Fakenews {
  String fake_news_name;
  String[][] Board;

  public FakenewsOne(int id, int type, String fake_news_name, int[] position, String [][] Board) {
    super(id, type, fake_news_name, position);
    // TODO Auto-generated constructor stubS
    this.fake_news_name = "F1";
    this.Board = Board;
  }


public String moveFakeNews(String[][] matriz) {
    int[] currentPosition = getPosition();
    int row = currentPosition[0];
    int col = currentPosition[1];

    // Gera um número aleatório de 0 a 3 para representar a direção do movimento
    Random random = new Random();
    int direction = random.nextInt(4);

    // Realiza o movimento com base na direção gerada aleatoriamente
    switch (direction) {
        case 0: // Norte
            if (row > 0) {
                if (matriz[row - 1][col].equals(" ")) {
                    matriz[row][col] = " ";
                    matriz[row - 1][col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                    position[0] = row - 1;
                    return "F1 fez movimento para Norte";
                } else if (matriz[row - 1][col].equals(fake_news_name)) {
                    matriz[row][col] = " ";
                    // position[0] = row - 1;
                    return "F1 tentou mover para Norte, mas outra fake news estava lá";
                }
            }
            break;
        case 1: // Sul
            if (row < matriz.length - 1) {
                if (matriz[row + 1][col].equals(" ")) {
                    matriz[row][col] = " ";
                    matriz[row + 1][col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                    position[0] = row + 1;
                    return "F1 fez movimento para Sul";
                } else if (matriz[row + 1][col].equals(fake_news_name)) {
                    matriz[row][col] = " ";
                    // position[0] = row + 1;
                    return "F1 tentou mover para Sul, mas outra fake news estava lá";
                }
            }
            break;
        case 2: // Leste
            if (col < matriz[row].length - 1) {
                if (matriz[row][col + 1].equals(" ")) {
                    matriz[row][col] = " ";
                    matriz[row][col + 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                    position[1] = col + 1;
                    return "F1 fez movimento para Leste";
                } else if (matriz[row][col + 1].equals(fake_news_name)) {
                    matriz[row][col] = " ";
                    // position[1] = col + 1;
                    return "F1 tentou mover para Leste, mas outra fake news estava lá";
                }
            }
            break;
        case 3: // Oeste
            if (col > 0) {
                if (matriz[row][col - 1].equals(" ")) {
                    matriz[row][col] = " ";
                    matriz[row][col - 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                    position[1] = col - 1;
                    return "F1 fez movimento para Oeste";
                } else if (matriz[row][col - 1].equals(fake_news_name)) {
                    matriz[row][col] = " ";
                    // position[1] = col - 1;
                    return "F1 tentou mover para Oeste, mas outra fake news estava lá";
                }
            }
            break;
    }
    return "Movimento inválido";
}




  

  

}
