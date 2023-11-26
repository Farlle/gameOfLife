package com.company;

import java.util.Scanner;

public class Main {
    final static int START_GAME = 1;
    final static int EXIT_GAME = 9;
    final static int TIME_DELAY = 3000;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Для старта игра необходимо ввести размер матрицы и старотовые позиции живых клеток \n" +
                "Игра закончится, если не останется живых клеток или после цикла не будет никаких изменений");
        int x = 0;
        Scanner scanner = new Scanner(System.in);
        String s = "";
        while (!"9".equals(s)) {
            System.out.println("Для старта нажмите 1");
            System.out.println("Для выхода нажмите 9");
            s = scanner.next();
            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
            switch (x) {
                case START_GAME -> game(scanner);
                case EXIT_GAME -> s = "9";
            }


        }
    }

    public static void game(Scanner scanner) {
        boolean inGame = true;
        System.out.println("Введите размерность матрицы");

        Game game = initGame(scanner);

        while (inGame) {
            System.out.print(game.matrixToString(game.getMatrixGame()) + "\n");
            game.lifeCycle();
            inGame = game.checkchecUniqueness(game.getMatrixGame());
            if (inGame) {
                try {
                    Thread.sleep(TIME_DELAY);
                    System.out.println("Следующая итерация ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.print("Результат: \n" + game.matrixToString(game.getMatrixGame()) + "\n");
        System.out.println("Игра окончена");
    }

    public static Game initGame(Scanner scanner) {
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int liveRows;
        int liveColumn;
        int count = 0;

        String scanStr;

        Game game = new Game(rows, columns);

        System.out.println("Укажите живые клетки (Строка и Столбец)" +
                "для окончания ввода введите 'end' ");
        while (count < (rows * columns)) {

            System.out.println("Строка");
            scanStr = scanner.next();

            if (scanStr.equals("end")) {
                break;
            }
            liveRows = Integer.parseInt(scanStr);

            System.out.println("Столбец");
            scanStr = scanner.next();
            liveColumn = Integer.parseInt(scanStr);

            game.setStartPosition(liveRows, liveColumn);

            count++;

        }
        return game;
    }


}
