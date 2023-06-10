package classes;

public class Player {
  int id;
  String player_name;
  int[] current_position;
  String stored_item;

  public Player(int id, String player_name, int[] current_position) {
    this.id = id;
    this.player_name = player_name;
    this.current_position = current_position;
  }

  public int getId() {
    return this.id;
  }

  public String getPlayerName() {
    return this.player_name;
  }

  public int[] getCurrentPosition() {
    return this.current_position;
  }

  public String getStoredItem() {
    return this.stored_item; 
  }

  public void movePlayer(int[] new_position) {
    this.current_position = new_position;
  }

  public void storeItem(String new_item) {
    this.stored_item = new_item;
  }

  public void useItem() {

  }
}
