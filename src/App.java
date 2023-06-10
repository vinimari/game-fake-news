import classes.Player;
import classes.Itens.Item;

public class App {
    static int[] position = { 0, 0 };
    static int[] new_position = { 0, 1 };
    static String item = "run";

    public static void main(String[] args) throws Exception {
        Player player = new Player(1, "J1", position);
        player.storeItem(item);

        System.out.println(player.getStoredItem());

;
    }
}
