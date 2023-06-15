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

  public int getId() {
    return this.id;
  }

  public int getType() {
    return this.type;
  }

  public String getFakeNewName() {
    return this.fake_news_name;
  }

  public int[] getPosition() {
    return this.position;
  }

  public abstract void moveFakeNews(String[][] matriz);
}
