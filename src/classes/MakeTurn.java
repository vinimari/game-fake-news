package classes;

import java.io.Console;
import java.util.Random;
import java.util.Scanner;

import classes.Itens.*;
import classes.FakeNews.*;

// import classes.FakeNews.Fakenews;
// import classes.FakeNews.FakenewsOne;
// import classes.FakeNews.FakenewsThree;
// import classes.FakeNews.FakenewsTwo;
// import classes.Itens.Item;
// import classes.Itens.ReadItem;
// import classes.Itens.ReportItem;
// import classes.Itens.RunItem;

public class MakeTurn {
  int round = 20;
  static Board board;
  static FakenewsOne fakenewsOne;
  static Player[] players_alive;

  static void createPlayer(int players_quantity) { // TODO: colocar os jogadores nas posicoes e os nomes de acordo
                                                   // com o n
    // int players_quantity = 4; // define by user
    String[] players_names = { "J1", "J2", "J3", "J4" };
    int[][] players_position = { { 0, 4 }, { 4, 8 }, { 8, 4 }, { 4, 0 } };
    players_alive = new Player[players_quantity];
    String[][] position_board = board.getMatriz();

    for (int i = 0; i < players_quantity; i++) {
      players_alive[i] = new Player(i + 1, players_names[i], players_position[i]);
      position_board[players_position[i][0]][players_position[i][1]] = Cores.ANSI_GREEN + players_names[i]
          + Cores.ANSI_RESET;
    }
  }

  static Fakenews[] createFakeNews() {
    int fakenews_quantity = 6; // minimum value: 6
    Fakenews[] fakenews_list = new Fakenews[fakenews_quantity];
    int[] fakenews_type = { 1, 2, 3 };
    int count_fakenews = 0;

    while (count_fakenews < fakenews_quantity) {
      for (int i = 0; i < fakenews_type.length; i++) {
        int current_type = fakenews_type[i];
        // fakenews_list[count_fakenews] = createFakeNewsFactory(current_type,
        // count_fakenews);
        fakenews_list[count_fakenews] = createFakeNewsFactory(current_type, count_fakenews);
        setFakenewsRandomPosition(fakenews_list[count_fakenews]);
        count_fakenews++;
      }
      // Atribua a instância de FakenewsOne à variável fakenewsOne
    }
    for (Fakenews fakenews : fakenews_list) {
      if (fakenews instanceof FakenewsOne) {
        fakenewsOne = (FakenewsOne) fakenews;
        break;
      }
    }
    return fakenews_list;
  }

  static Fakenews createFakeNewsFactory(int type, int id) {
    switch (type) {
      case 1: {
        int[] random_position = generateRandomPosition(1, 7);
        String[][] Board = board.getMatriz();
        String fakenews_name = "F1";
        return new FakenewsOne(id + 1, type, fakenews_name,
            random_position, Board);
      }
      case 2: {
        int[] random_position = generateRandomPosition(1, 7);
        String fakenews_name = "F2";
        return new FakenewsTwo(id + 1, type, fakenews_name,
            random_position);
      }
      case 3: {
        int[] random_position = generateRandomPosition(1, 7);
        String fakenews_name = "F3";
        return new FakenewsThree(id + 1, type, fakenews_name,
            random_position);
      }
      default: {
        throw new IllegalArgumentException();
      }
    }
  }

  static void setFakenewsRandomPosition(Fakenews fakenews) {
    String[][] position_board = board.getMatriz();

    position_board[fakenews.getPosition()[0]][fakenews.getPosition()[1]] = Cores.ANSI_RED
        + fakenews.getFakeNewName() + Cores.ANSI_RESET;
  }

  static Item[] createItem() {
    int item_quantity = 2; // minimum value: 2
    Item[] item_list = new Item[item_quantity];

    // Generate radom item type
    int min = 1, max = 4;
    Random rand = new Random();
    int randon_item_type = rand.nextInt(max - min + 1) + min;

    for (int i = 0; i < item_quantity; i++) {
      item_list[i] = createItemFactory(randon_item_type, i);
      setItemRandomPosition(item_list[i]);
    }
    return item_list;
  }

  static Item createItemFactory(int type, int id) {
    int min = 0, max = 8;
    switch (type) {
      case 1: {
        int[] random_position = generateRandomPosition(min, max);
        return new ReportItem(id + 1, random_position);
      }
      case 2: {
        int[] random_position = generateRandomPosition(min, max);
        return new RunItem(id + 1, random_position);
      }
      case 3: {
        int[] random_position = generateRandomPosition(min, max);
        return new ReadItem(id + 1, random_position);
      }
      case 4: {
        int[] random_position = generateRandomPosition(min, max);
        return new ReadItem(id + 1, random_position);
      }
    }
    return null;
  }

  static void setItemRandomPosition(Item item) {
    String[][] position_board = board.getMatriz();

    position_board[item.getCurrentPosition()[0]][item.getCurrentPosition()[1]] = Cores.ANSI_YELLOW + item.getItemName()
        + Cores.ANSI_RESET;
  }

  static int[] generateRandomPosition(int min, int max) {
    String[][] position_board = board.getMatriz();
    Random rand = new Random();
    int row, col;

    do {
      row = rand.nextInt(max - min + 1) + min;
      col = rand.nextInt(max - min + 1) + min;
    } while (position_board[row][col] != " ");

    int[] position = { row, col };
    return position;
  }

  static void generateZones(int n) {
    String[][] position_board = board.getMatriz();

    for (int i = 0; i < n; i++) {
      int[] randomPosition = generateRandomPosition(1, 5);
      int row = randomPosition[0];
      int col = randomPosition[1];
      position_board[row][col] = Cores.ANSI_WHITE + "XX" + Cores.ANSI_RESET;

    }
  }

  static void onSetBoard() {
    board = new Board(9);
    int restriction_zones = 4;
    generateZones(restriction_zones);
    Fakenews[] fakenewsList = createFakeNews();
    createPlayer(4);
    createItem();
    board.showBoard();
    onPlayersTurn();
    ;
    // createFakeNews();
    // board.showBoard();
    // Movendo todas as FakenewsOne
    // MOVE FAKENEWS 1
    // for (Fakenews fakenews : fakenewsList) {
    // if (fakenews instanceof FakenewsOne) {
    // FakenewsOne fakenewsOne = (FakenewsOne) fakenews;
    // String resultadoMovimento = fakenewsOne.moveFakeNews(board.getMatriz());
    // // fakenewsOne.moveFakeNews(board.getMatriz());
    // System.out.println(resultadoMovimento);
    // }
    // }
    // MOVE FAKENEWS 2
    // for (Fakenews fakenews : fakenewsList) {
    // if (fakenews instanceof FakenewsTwo) {
    // FakenewsTwo fakenewsTwo = (FakenewsTwo) fakenews;
    // String resultadoMovimento = fakenewsTwo.moveFakeNews(board.getMatriz());
    // // fakenewsOne.moveFakeNews(board.getMatriz());
    // System.out.println(resultadoMovimento);
    // }
    // }
    // MOVE FAKENEWS 3
    for (Fakenews fakenews : fakenewsList) {
      if (fakenews instanceof FakenewsThree) {
        FakenewsThree fakenewsThree = (FakenewsThree) fakenews;
        String resultadoMovimento = fakenewsThree.moveFakeNews(board.getMatriz());
        // fakenewsOne.moveFakeNews(board.getMatriz());
        System.out.println(resultadoMovimento);
      }
    }
    board.showBoard();
  }

  static void clearPlayerLastPosition(Player player) {
    int[] player_last_position = player.getCurrentPosition();
    int row = player_last_position[0], col = player_last_position[1];
    board.setValue(row, col, " ");
  }

  static void setPlayerNewPosition(Player player) {
    String[][] position_board = board.getMatriz();
    int[] player_position = player.getCurrentPosition();
    String player_name = player.getPlayerName();

    position_board[player_position[0]][player_position[1]] = Cores.ANSI_GREEN + player_name
        + Cores.ANSI_RESET;
  }

  static void onPlayersTurn() {
    System.out.println("VEZ DOS JOGADORES");
    for (Player player : players_alive) {
      System.out.println(player);
      String player_name = player.getPlayerName();
      System.out.println("É a vez do jogador " + player_name + ", escolha sua ação:");
      System.out.println("1: Norte | 2: Sul | 3: Leste (Direita) | 4: Oeste (Esquerda) | 5: Utilizar item");
      Scanner scanner = new Scanner(System.in);
      int player_movement_direction = scanner.nextInt();
      clearPlayerLastPosition(player);
      player.movePlayer(player_movement_direction);
      setPlayerNewPosition(player);
      board.showBoard();
    }
  }

  public static void main(String[] args) {
    onSetBoard();
  }

}
