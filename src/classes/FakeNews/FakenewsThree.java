package classes.FakeNews;

import java.util.Random;

import classes.Cores;

public class FakenewsThree extends Fakenews {

    public FakenewsThree(int id, int type, String fake_news_name, int[] position) {
        super(id, type, fake_news_name, position);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String moveFakeNews(String[][] matriz) {
        int[] currentPosition = getPosition();
        int row = currentPosition[0];
        int col = currentPosition[1];

        // Gera um número aleatório de 0 a 3 para representar a direção do movimento
        // diagonal
        Random random = new Random();
        int direction = random.nextInt(4);

        // Realiza o movimento com base na direção gerada aleatoriamente
        switch (direction) {
            case 0: // Noroeste
                if (row > 0 && col > 0) {
                    if (matriz[row - 1][col - 1].equals(" ")) {
                        matriz[row][col] = " ";
                        matriz[row - 1][col - 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = row - 1;
                        position[1] = col - 1;
                        return "F3 fez movimento para o Noroeste";
                    } else {
                        matriz[row][col] = " ";
                        return "F3 tentou se mover para o Noroeste, mas a posição está ocupada. F3 foi removida.";
                    }
                }
                break;
            case 1: // Nordeste
                if (row > 0 && col < matriz[row].length - 1) {
                    if (matriz[row - 1][col + 1].equals(" ")) {
                        matriz[row][col] = " ";
                        matriz[row - 1][col + 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = row - 1;
                        position[1] = col + 1;
                        return "F3 fez movimento para o Nordeste";
                    } else {
                        matriz[row][col] = " ";
                        return "F3 tentou se mover para o Nordeste, mas a posição está ocupada. F3 foi removida."; // TODO:
                                                                                                                   // esse
                                                                                                                   // return
                                                                                                                   // pra
                                                                                                                   // fakenews
                                                                                                                   // removida
                                                                                                                   // n
                                                                                                                   // é
                                                                                                                   // necessario
                    }
                }
                break;
            case 2: // Sudoeste
                if (row < matriz.length - 1 && col > 0) {
                    if (matriz[row + 1][col - 1].equals(" ")) {
                        matriz[row][col] = " ";
                        matriz[row + 1][col - 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = row + 1;
                        position[1] = col - 1;
                        return "F3 fez movimento para o Sudoeste";
                    } else {
                        matriz[row][col] = " ";
                        return "F3 tentou se mover para o Sudoeste, mas a posição está ocupada. F3 foi removida.";
                    }
                }
                break;
            case 3: // Sudeste
                if (row < matriz.length - 1 && col < matriz[row].length - 1) {
                    if (matriz[row + 1][col + 1].equals(" ")) {
                        matriz[row][col] = " ";
                        matriz[row + 1][col + 1] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = row + 1;
                        position[1] = col + 1;
                        return "F3 fez movimento para o Sudeste";
                    } else {
                        matriz[row][col] = " ";
                        return "F3 tentou se mover para o Sudeste, mas a posição está ocupada. F3 foi removida.";
                    }
                }
                break;
        }
        return "Movimento inválido";
    }

}