package classes.Itens;

public abstract class Item {
  int id;
  String item_name;
  int[] current_position;

  Item(int id, String item_name, int[] current_position) {
    this.id = id;
    this.item_name = item_name;
    this.current_position = current_position;
  }

  protected int getId() {
    return id;
  }

  public String getItemName() {
    return item_name;
  }

  protected int[] getCurrentPosition() {
    return current_position;
  }

  protected abstract void onUse();
}
