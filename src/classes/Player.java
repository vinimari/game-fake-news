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

  public void movePlayer(int new_direction) {

    int[] currentPosition = getCurrentPosition();
    int row = currentPosition[0];
    int col = currentPosition[1];

    switch (new_direction) {
      // Move North (decrease row one unity)
      case 1: {
        this.current_position[0] = this.current_position[0] - 1;
        break;
      }
      // Move South (increase row one unity)
      case 2: {
        this.current_position[0] = this.current_position[0] + 1;
        break;
      }
      // Move East / Right (increase col one unity)
      case 3: {
        this.current_position[1] = this.current_position[1] + 1;
        break;
      }
      // Move West / Left (decrease col one unity)
      case 4: {
        this.current_position[1] = this.current_position[1] - 1;
        break;
      }
    }

  }

  public void storeItem(String new_item) {
    this.stored_item = new_item;
  }

  public void useItem() {

  }
}
