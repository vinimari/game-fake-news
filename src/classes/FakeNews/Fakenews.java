package classes.FakeNews;

public abstract class Fakenews {
  int id;
  int type;
  String fake_news_name;
  int[] position;

  Fakenews(int id, int type, String fake_news_name, int[] position) {
    this.id = id;
    this.type = type;
    this.fake_news_name = fake_news_name;
    this.position = position;
  }

  protected int getId() {
    return this.id;
  }

  protected int getType() {
    return this.type;
  }

  protected String getFakeNewName() {
    return this.fake_news_name;
  }

  protected int[] getPosition() {
    return this.position;
  }

  protected abstract String moveFakeNews();
}
