package game;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import logging.AppLogger;

public class Process {
    private final Board board;
    private final Scanner scan;
    private final Map<Character, Boolean> players;
    private final byte boardMaxCount;
    private final byte boardMinCount;
    private byte winner;

    public Process(Board board, Map<Character, Boolean> players) {
        this.board = board;
        this.scan = new Scanner(System.in);
        this.players = players;
        this.boardMaxCount = board.getMaxCount();
        this.boardMinCount = board.getMinCount();
        this.winner = 0;
    }

    public byte start() {
        if (winner != 0) {
            return winner;
        }

        AppLogger.log("Enter box number to select. Enjoy!\n");
        board.show();
        board.clear();

        while (winner == 0) {
            for (Map.Entry<Character, Boolean> player : players.entrySet()) {
                char symbol = player.getKey();
                boolean isUser = player.getValue();

                if (isUser) {
                    board.show();
                    userTurn(symbol);
                } else {
                    aiTurn(symbol);
                }

                checkWinner(symbol, isUser);

                if (winner != 0) {
                    break;
                }
            }
        }
        scan.close();
        board.show();
        return winner;
    }

    private void userTurn(char symbol) {
        byte input;
        boolean isWaitingTurn = true;

        while (isWaitingTurn) {
            try {
                if (scan.hasNextByte()) {
                    input = scan.nextByte();
                    if (input < boardMinCount || input > boardMaxCount) {
                        throw new InputMismatchException();
                    }
                } else {
                    scan.next();
                    throw new InputMismatchException();
                }
            } catch (Exception e) {
                AppLogger.log("Invalid input. Enter again.");
                continue;
            }

            if (board.setCell(input, symbol)) {
                isWaitingTurn = false;
            } else {
                AppLogger.log("That one is already in use. Enter another.");
            }
        }
    }

    private void aiTurn(char symbol) {
        boolean isWaitingTurn = true;
        while (isWaitingTurn) {
            byte rand = (byte) (Math.random() * boardMaxCount + 1);
            if (board.setCell(rand, symbol)) {
                isWaitingTurn = false;
            }
        }
    }

    private void checkWinner(char symbol, boolean isUser) {
        if (board.checkLines(symbol)) {
            winner = (byte) (isUser ? 1 : 2);
            return;
        }

        if (board.isFilled()) {
            winner = 3;
        }
    }
}
