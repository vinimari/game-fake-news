package classes.Itens;

public abstract class Item {
  int id;
  int type;
  String item_name = "??";
  int[] current_position;

  Item(int id, int type, int[] current_position) {
    this.id = id;
    this.type = type;
    this.current_position = current_position;
  }

  public int getId() {
    return id;
  }

  public String getItemName() {
    return item_name;
  }

  public int[] getCurrentPosition() {
    return current_position;
  }

  protected abstract void onUse();
}
