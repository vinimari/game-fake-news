package classes.Itens;

public class ReportItem extends Item {

  ReportItem(int id, int[] current_position) {
    super(id, 1, current_position);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void onUse() {
    // kill all adjacents fakenews
    throw new UnsupportedOperationException("Unimplemented method 'onUse'");
  }

}
