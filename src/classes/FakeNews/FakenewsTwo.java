package classes.FakeNews;

import java.util.Random;

import classes.Cores;

public class FakenewsTwo extends Fakenews {

  public FakenewsTwo(int id, int type, String fake_news_name, int[] position) {
    super(id, type, fake_news_name, position);
    // TODO Auto-generated constructor stub
  }

  @Override
  public String moveFakeNews(String[][] matriz) {
    int[] currentPosition = getPosition();
    int row = currentPosition[0];
    int col = currentPosition[1];

    // Gera um número aleatório de 0 a 3 para representar a direção do movimento
    Random random = new Random();
    int direction = random.nextInt(4);

    // Define o número de casas a serem movidas (no caso, 2)
    int numMoves = 2;

    // Realiza o movimento com base na direção gerada aleatoriamente
    switch (direction) {
      case 0: // Norte
        if (row - numMoves >= 0) {
          if (matriz[row - numMoves][col].equals(" ")) {
            matriz[row][col] = " ";
            matriz[row - numMoves][col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
            position[0] = row - numMoves;
            return "F2 fez movimento para o Norte de " + numMoves + " casas";
          } else {
            // A posição de destino está ocupada por outra fakenews
            // Remover a fakenews que está tentando se mover
            matriz[row][col] = " ";
            return "F2 tentou se mover para o Norte, mas a posição está ocupada. F2 foi removida.";
          }
        }
        break;
      case 1: // Sul
        if (row + numMoves < matriz.length) {
          if (matriz[row + numMoves][col].equals(" ")) {
            matriz[row][col] = " ";
            matriz[row + numMoves][col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
            position[0] = row + numMoves;
            return "F2 fez movimento para o Sul de " + numMoves + " casas";
          } else {
            // A posição de destino está ocupada por outra fakenews
            // Remover a fakenews que está tentando se mover
            matriz[row][col] = " ";
            return "F2 tentou se mover para o Sul, mas a posição está ocupada. F2 foi removida.";
          }
        }
        break;
      case 2: // Leste
        boolean canMoveEast = true;
        if (col + numMoves >= matriz[row].length) {
          canMoveEast = false;
        } else {
          for (int i = 1; i <= numMoves; i++) {
            if (!matriz[row][col + i].equals(" ")) {
              canMoveEast = false;
              break;
            }
          }
        }
        if (canMoveEast) {
          matriz[row][col] = " ";
          matriz[row][col + numMoves] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
          position[1] = col + numMoves;
          return "F2 fez movimento para o Leste de " + numMoves + " casas";
        } else {
          matriz[row][col] = " ";
          return "F2 tentou se mover para o Leste, mas a posição está ocupada. F2 foi removida.";
        }

      case 3: // Oeste
        boolean canMoveWest = true;
        if (col - numMoves < 0) {
          canMoveWest = false;
        } else {
          for (int i = 1; i <= numMoves; i++) {
            if (!matriz[row][col - i].equals(" ")) {
              canMoveWest = false;
              break;
            }
          }
        }
        if (canMoveWest) {
          matriz[row][col] = " ";
          matriz[row][col - numMoves] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
          position[1] = col - numMoves;
          return "F2 fez movimento para o Oeste de " + numMoves + " casas";
        } else {
          matriz[row][col] = " ";
          return "F2 tentou se mover para o Oeste, mas a posição está ocupada. F2 foi removida.";
        }
    }
    matriz[row][col] = " ";
    return "Movimento inválido, logo, foi removida";

  }
}
