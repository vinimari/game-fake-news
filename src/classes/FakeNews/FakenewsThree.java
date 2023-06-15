package classes.FakeNews;

import java.util.Random;

import classes.Cores;

public class FakenewsThree extends Fakenews {

    public FakenewsThree(int id, int type, String fake_news_name, int[] position) {
        super(id, type, fake_news_name, position);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void moveFakeNews(String[][] board) {
        int[] currentPosition = getPosition();
        int row = currentPosition[0];
        int col = currentPosition[1];

        // Gera um número aleatório de 0 a 3 para representar a direção do movimento
        // diagonal
        Random random = new Random();
        int direction = random.nextInt(4);

        // Define o número de casas que a fake news se move
        int distance_movement = 1;
        int initial_name_position = 5;

        switch (direction) {
            // Move Northwest / nort and left (decrease row and col one unity)
            case 0: {
                int new_row = row - distance_movement;
                int new_col = col - distance_movement;

                if (checkInsideBoard(new_row, new_col)) {
                    String piece_name = board[new_row][col];

                    if (piece_name == " ") {
                        displayMovementMessage(fake_news_name, "Noroeste");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                    } else if (piece_name.charAt(initial_name_position) == 'J') {
                        displayColisionMessage(fake_news_name, "jogador");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar jogador
                    } else if (piece_name.charAt(initial_name_position) == 'F') {
                        displayColisionMessage(fake_news_name, "fake news");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else if (piece_name.charAt(initial_name_position) == 'X') {
                        displayColisionMessage(fake_news_name, "zona restrita");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else {
                        displayColisionMessage(fake_news_name, "item");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar item
                        // Duplicar fake news

                    }
                } else {
                    System.out.println(fake_news_name + " saiu do tabuleiro!");
                    board[row][col] = " ";
                    // Eliminar fake news
                }
                break;
            }
            // Move Northwest / nort and right (decrease row and increase col one unity)
            case 1: {
                int new_row = row - distance_movement;
                int new_col = col + distance_movement;

                if (checkInsideBoard(new_row, new_col)) {
                    String piece_name = board[new_row][col];

                    if (piece_name == " ") {
                        displayMovementMessage(fake_news_name, "Nordeste");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                    } else if (piece_name.charAt(initial_name_position) == 'J') {
                        displayColisionMessage(fake_news_name, "jogador");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar jogador
                    } else if (piece_name.charAt(initial_name_position) == 'F') {
                        displayColisionMessage(fake_news_name, "fake news");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else if (piece_name.charAt(initial_name_position) == 'X') {
                        displayColisionMessage(fake_news_name, "zona restrita");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else {
                        displayColisionMessage(fake_news_name, "item");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar item
                        // Duplicar fake news
                    }
                } else {
                    System.out.println(fake_news_name + " saiu do tabuleiro!");
                    board[row][col] = " ";
                    // Eliminar fake news
                }
                break;
            }
            // Move south-west / south and left (increase row and decrease col one unity)
            case 2: {
                int new_row = row + distance_movement;
                int new_col = col - distance_movement;

                if (checkInsideBoard(new_row, new_col)) {
                    String piece_name = board[new_row][col];

                    if (piece_name == " ") {
                        displayMovementMessage(fake_news_name, "Sudoeste");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                    } else if (piece_name.charAt(initial_name_position) == 'J') {
                        displayColisionMessage(fake_news_name, "jogador");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar jogador
                    } else if (piece_name.charAt(initial_name_position) == 'F') {
                        displayColisionMessage(fake_news_name, "fake news");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else if (piece_name.charAt(initial_name_position) == 'X') {
                        displayColisionMessage(fake_news_name, "zona restrita");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else {
                        displayColisionMessage(fake_news_name, "item");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar item
                        // Duplicar fake news
                    }
                } else {
                    System.out.println(fake_news_name + " saiu do tabuleiro!");
                    board[row][col] = " ";
                    // Eliminar fake news
                }
                break;
            }
            // Move southeast / south and right (increase row and increase col one unity)
            case 3: {
                int new_row = row + distance_movement;
                int new_col = col + distance_movement;

                if (checkInsideBoard(new_row, new_col)) {
                    String piece_name = board[new_row][col];

                    if (piece_name == " ") {
                        displayMovementMessage(fake_news_name, "Sudeste");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                    } else if (piece_name.charAt(initial_name_position) == 'J') {
                        displayColisionMessage(fake_news_name, "jogador");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar jogador
                    } else if (piece_name.charAt(initial_name_position) == 'F') {
                        displayColisionMessage(fake_news_name, "fake news");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else if (piece_name.charAt(initial_name_position) == 'X') {
                        displayColisionMessage(fake_news_name, "zona restrita");
                        board[row][col] = " ";
                        // Eliminar fake news
                    } else {
                        displayColisionMessage(fake_news_name, "item");
                        board[row][col] = " ";
                        board[new_row][new_col] = Cores.ANSI_RED + fake_news_name + Cores.ANSI_RESET;
                        position[0] = new_row;
                        position[1] = new_col;
                        // Eliminar item
                        // Duplicar fake news
                    }
                } else {
                    System.out.println(fake_news_name + " saiu do tabuleiro!");
                    board[row][col] = " ";
                    // Eliminar fake news
                }
                break;
            }
        }
    }

    static void displayMovementMessage(String fakenews_name, String direction) {
        System.out.println(fakenews_name + " se moveu para: " + direction);
    }

    static void displayColisionMessage(String fakenews_name, String piece_name) {
        System.out.println(fakenews_name + " colidiu com: " + piece_name);
    }

    static boolean checkInsideBoard(int new_row, int new_col) {
        boolean is_inside = false;
        if (new_row >= 0 && new_row < 9 && new_col >= 0 && new_col < 9) {
            is_inside = true;
        }
        return is_inside;
    }
}