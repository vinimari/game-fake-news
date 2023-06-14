package classes;
import java.util.TimerTask;
import classes.FakeNews.FakenewsOne;
import java.util.Timer;

public class MoveFakeNewsTask extends TimerTask {
    private FakenewsOne fakenewsOne;
    private String[][] matriz;

    public MoveFakeNewsTask(FakenewsOne fakenewsOne, String[][] matriz) {
        this.fakenewsOne = fakenewsOne;
        this.matriz = matriz;
    }

    @Override
    public void run() {
        fakenewsOne.moveFakeNews(matriz);
        // Atualize a exibição do tabuleiro ou execute outras ações necessárias após o movimento da FakeNews
        // board.showBoard();
    }

    public void startMoving() {
        Timer timer = new Timer();
        // Agende a tarefa para ser executada repetidamente a cada 2 segundos
        timer.scheduleAtFixedRate(this, 0, 2000);
    }
}
