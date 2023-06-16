package classes;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;

import classes.Itens.*;
import classes.FakeNews.*;

public class MakeTurn {
  static int round = 20;
  static Board board;
  static ArrayList<Player> players_alive;
  static ArrayList<Fakenews> fakenews_alive;
  static ArrayList<Item> item_list;

  public static void onSetBoard() {
    /*
     * SET GAME BOARD
     * - Board size
     * - Retricted zone
     */

    board = new Board(9);
    int restricted_zones_number = 4;
    generateRestrictedZones(restricted_zones_number);

    /*
     * SET PIECES BOARD
     * - Players
     * - Fake News
     * - Itens
     */

    int players_numbers = getNumbersOfPlayers();
    createPlayer(players_numbers);
    createFakeNews();
    createItem();
    board.showBoard();

    /*
     * START LOOP GAME
     * Stop when
     * players alives = 0 -> fakenews victory
     * fakenews alives = 0 -> players victory
     * rounds played = 20 -> fakenews victory
     */

    for (int round_count = 0; round_count < round; round_count++) {
      if (onPlayersTurn(round_count + 1) == 0) {
        System.out.println("Vitória das Fake News");
        break;
      }
      if (onFakenewsTurn(2000) == 0) {
        System.out.println("Vitória dps Jogadores");
        break;
      }
      if (round_count == 20) {
        System.out.println("Turnos Finalizados - Vitória das Fake News");
        break;
      }
    }
  }

  static int onFakenewsTurn(long time_interval) {
    if (fakenews_alive.isEmpty()) {
      return 0;
    }
    System.out.println("******************");
    System.out.println("Turno das Fakenews");
    System.out.println("******************");
    for (Fakenews fakenews : fakenews_alive) {
      fakenews.moveFakeNews(board.getMatriz());
      try {
        TimeUnit.MILLISECONDS.sleep(time_interval);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        board.showBoard();
      }
    }

    // Remove dead fakenews
    Iterator<Fakenews> iterator = fakenews_alive.iterator();
    while (iterator.hasNext()) {
      Fakenews fakenews = iterator.next();
      int fakenews_status = fakenews.getType();
      if (fakenews_status == 0) {
        iterator.remove();
      }
    }
    checkForDuplicateFakenews();
    return 1;
  }

  /*
   * Verifica se nas posiççoes do item, existe uma fakenews
   * if true
   * deleta item, gera item em um novo local
   * gera uma nova faknews em uma posição adjacente
   * 
   */

  static void checkForDuplicateFakenews() {
    Iterator<Item> iterator_item = item_list.iterator();
    String[][] position_board = board.getMatriz();

    while (iterator_item.hasNext()) {
      Item item = iterator_item.next();
      int[] item_position = item.getCurrentPosition();
      String name = position_board[item_position[0]][item_position[1]];
      int type = 1;

      if (name.substring(5, 6) == "F1") {
        type = 1;
        generateNewItem(iterator_item);
        duplicateFakenews(type, item_position);
      } else if (name.substring(5, 6) == "F2") {
        type = 2;
        generateNewItem(iterator_item);
        duplicateFakenews(type, item_position);
      } else if (name.substring(5, 6) == "F3") {
        type = 3;
        generateNewItem(iterator_item);
        duplicateFakenews(type, item_position);
      }
    }
  }

  static void generateNewItem(Iterator<Item> iterator_item) {
    iterator_item.remove();
    int new_item_type = generateRandomPosition(1, 4)[0];
    createItemFactory(new_item_type, 4);
    System.out.println("Um novo item foi gerado");
  }

  static void duplicateFakenews(int type, int[] item_position) {
    int new_row = item_position[0] + 1;
    int new_col = item_position[1] + 1;
    int[] new_position = { new_row, new_col };
    createFakeNewsFactory(1, 4, new_position);
    System.out.println("Uma fake news foi duplicada");
  }

  static void checkDeadPlayers() {
    String[][] position_board = board.getMatriz();

    for (int row = 0; row < position_board.length; row++) {
      for (int col = 0; col < position_board.length; col++) {
        Iterator<Player> iterator_player = players_alive.iterator();
        String name = position_board[row][col];
        if (name == " ") {
          if (players_alive.size() == 1) {
            Player player = players_alive.get(0);
            if (player.getCurrentPosition()[0] == row && player.getCurrentPosition()[1] == col) {
              players_alive.clear();
              System.out.println(player.getPlayerName() + " eliminado!");
            }
          } else {

            while (iterator_player.hasNext()) {
              Player player = iterator_player.next();
              int[] players_position = player.getCurrentPosition();
              if (players_position[0] == row && players_position[1] == col) {
                iterator_player.remove();
                System.out.println(player.getPlayerName() + " eliminado!");
              }
            }
          }
        } else if (name.charAt(5) == 'F') {
          if (players_alive.size() == 1) {
            Player player = players_alive.get(0);
            if (player.getCurrentPosition()[0] == row && player.getCurrentPosition()[1] == col) {
              players_alive.clear();
              System.out.println(player.getPlayerName() + " eliminado!");
            }
          } else {
            while (iterator_player.hasNext()) {
              Player player = iterator_player.next();
              if (player.getCurrentPosition()[0] == row && player.getCurrentPosition()[1] == col) {
                iterator_player.remove();
                System.out.println(player.getPlayerName() + " eliminado!");
              }
            }
          }
        } else if (name.charAt(5) == 'X') {
          if (players_alive.size() == 1) {
            Player player = players_alive.get(0);
            if (player.getCurrentPosition()[0] == row && player.getCurrentPosition()[1] == col) {
              players_alive.clear();
              System.out.println(player.getPlayerName() + " eliminado!");
            }
          } else {
            while (iterator_player.hasNext()) {
              Player player = iterator_player.next();
              if (player.getCurrentPosition()[0] == row && player.getCurrentPosition()[1] == col) {
                iterator_player.remove();
                System.out.println(player.getPlayerName() + " eliminado!");
              }
            }
          }
        }
      }
    }
  }

  static int onPlayersTurn(int round_count) {
    if (round_count > 1) {
      checkDeadPlayers();
      if (players_alive.isEmpty()) {
        return 0;
      }
    }
    System.out.println("###################");
    System.out.println("Turno " + round_count + "/20 dos Jogadores");
    System.out.println("###################");
    for (Player player : players_alive) {
      String player_name = player.getPlayerName();
      System.out.println("Jogador " + player_name + ", escolha a direção para se mover:");
      System.out.println("------------------------------");
      System.out.println("               ^              ");
      System.out.println("          W: Norte            ");
      System.out.println("< A: Oeste         D: Leste > ");
      System.out.println("          S:  Sul             ");
      System.out.println("               v              ");
      System.out.println("------------------------------");
      Scanner scanner = new Scanner(System.in);
      String player_movement_direction = scanner.nextLine().toUpperCase();
      player.movePlayer(player_movement_direction, board.getMatriz());
      board.showBoard();
    }
    return 1;
  }

  static void createPlayer(int players_quantity) {
    String[] players_names = { "J1", "J2", "J3", "J4" };
    int[][] players_position = { { 0, 4 }, { 4, 8 }, { 8, 4 }, { 4, 0 } };
    players_alive = new ArrayList<Player>(players_quantity);
    String[][] position_board = board.getMatriz();

    for (int i = 0; i < players_quantity; i++) {
      Player player = new Player(i + 1, players_names[i], players_position[i]);
      players_alive.add(i, player);
      position_board[players_position[i][0]][players_position[i][1]] = Cores.ANSI_GREEN + players_names[i]
          + Cores.ANSI_RESET;
    }
  }

  static void createFakeNews() {
    int fakenews_number = 6, count_fakenews = 0; // fakenews_number - minimum value: 6
    fakenews_alive = new ArrayList<Fakenews>(fakenews_number);
    int[] fakenews_type = { 1, 2, 3 };

    while (count_fakenews < fakenews_number) {
      for (int i = 0; i < fakenews_type.length; i++) {
        int[] randon_position = generateRandomPosition(1, 7);
        int current_type = fakenews_type[i];
        fakenews_alive.add(i, createFakeNewsFactory(current_type, count_fakenews, randon_position));
        setFakenewsRandomPosition(fakenews_alive.get(i));
        count_fakenews++;
      }
    }
  }

  static ArrayList<Item> createItem() {
    int item_quantity = 2;
    // Generate radom item type
    int min = 1, max = 4;
    Random rand = new Random();
    int randon_item_type = rand.nextInt(max - min + 1) + min;

    item_list = new ArrayList<Item>(item_quantity);
    for (int i = 0; i < item_quantity; i++) {
      item_list.add(i, createItemFactory(randon_item_type, i));
      setItemRandomPosition(item_list.get(i));
    }
    return item_list;
  }

  static Fakenews createFakeNewsFactory(int type, int id, int[] random_position) {
    switch (type) {
      case 1: {
        String[][] Board = board.getMatriz();
        String fakenews_name = "F1";
        return new FakenewsOne(id + 1, type, fakenews_name,
            random_position, Board);
      }
      case 2: {
        String fakenews_name = "F2";
        return new FakenewsTwo(id + 1, type, fakenews_name,
            random_position);
      }
      case 3: {
        String fakenews_name = "F3";
        return new FakenewsThree(id + 1, type, fakenews_name,
            random_position);
      }
      default: {
        throw new IllegalArgumentException();
      }
    }
  }

  static Item createItemFactory(int type, int id) {
    int min = 0, max = 8;
    switch (type) {
      case 1: {
        int[] random_position = generateRandomPosition(min, max);
        return new ListenItem(id + 1, random_position);
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

  static void setFakenewsRandomPosition(Fakenews fakenews) {
    String[][] position_board = board.getMatriz();

    position_board[fakenews.getPosition()[0]][fakenews.getPosition()[1]] = Cores.ANSI_RED
        + fakenews.getFakeNewName() + Cores.ANSI_RESET;
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

  static void generateRestrictedZones(int zones_number) {
    String[][] position_board = board.getMatriz();

    for (int i = 0; i < zones_number; i++) {
      int[] randomPosition = generateRandomPosition(1, 5);
      int row = randomPosition[0];
      int col = randomPosition[1];
      position_board[row][col] = Cores.ANSI_WHITE + "XX" + Cores.ANSI_RESET;
    }
  }

  static int getNumbersOfPlayers() {
    System.out.println("Digite a quantidade de jogadores [1 até 4]: 1");
    return 2;
  }
}
