package classes.FakeNews;

public class FakenewsOne extends Fakenews {
  String fake_news_name;

  public FakenewsOne(int id, int type, String fake_news_name, int[] position) {
    super(id, type, fake_news_name, position);
    // TODO Auto-generated constructor stub
    this.fake_news_name = "F1";
  }

  @Override
  protected String moveFakeNews() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'moveFakeNews'");
  }

}
