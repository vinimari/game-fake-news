package classes;

import java.util.Random;

import classes.FakeNews.Fakenews;
import classes.FakeNews.FakenewsOne;
import classes.FakeNews.FakenewsThree;
import classes.FakeNews.FakenewsTwo;
import classes.Itens.Item;
import classes.Itens.ReadItem;
import classes.Itens.ReportItem;
import classes.Itens.RunItem;

public class MakeTurn {
  int round = 20;
  static Board board;

  static Player[] createPlayer() {
    int players_quantity = 4; // define by user
    String[] players_names = { "J1", "J2", "J3", "J4" };
    int[][] players_position = { { 0, 4 }, { 4, 8 }, { 8, 4 }, { 4, 0 } };
    Player[] player_list = new Player[players_quantity];
    String[][] position_board = board.getMatriz();

    for (int i = 0; i < players_quantity; i++) {
      player_list[i] = new Player(i + 1, players_names[i], players_position[i]);
      position_board[players_position[i][0]][players_position[i][1]] = Cores.ANSI_GREEN + players_names[i];
    }
    return player_list;
  }

  static Fakenews[] createFakeNews() {
    int fakenews_quantity = 6; // minimum value: 6
    Fakenews[] fakenews_list = new Fakenews[fakenews_quantity];
    int[] fakenews_type = { 1, 2, 3 };
    int count_fakenews = 0;

    while (count_fakenews < fakenews_quantity) {
      for (int i = 0; i < fakenews_type.length; i++) {
        int current_type = fakenews_type[i];
        fakenews_list[count_fakenews] = createFakeNewsFactory(current_type, count_fakenews);
        setFakenewsRandomPosition(fakenews_list[count_fakenews]);
        count_fakenews++;
      }
    }
    return fakenews_list;
  }

  static Fakenews createFakeNewsFactory(int type, int id) {
    switch (type) {
      case 1: {
        int[] random_position = generateRandomPosition(1, 7);
        String fakenews_name = "F1";
        return new FakenewsOne(id + 1, type, fakenews_name,
            random_position);
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
        + fakenews.getFakeNewName();
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

    position_board[item.getCurrentPosition()[0]][item.getCurrentPosition()[1]] = Cores.ANSI_YELLOW + item.getItemName();
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

  static void onSetBoard() {
    board = new Board(9);
    createPlayer();
    createFakeNews();
    createItem();
    board.showBoard();
  }

  public static void main(String[] args) {
    onSetBoard();
  }

}
