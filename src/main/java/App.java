import java.util.LinkedHashMap;
import java.util.Map;

import game.Board;
import game.Process;

import logging.Greeting;


public class App {
    private static final char SYMBOL_X = 'X';
    private static final char SYMBOL_O = '0';

    public static void main(String[] args) {

        Board board = new Board();

        // players map contain player symbol and isUser indication for each player
        Map<Character, Boolean> players = new LinkedHashMap<>();
        players.put(SYMBOL_X, true);
        players.put(SYMBOL_O, false);

        Process process = new Process(board, players);
        byte winner = process.start();

        Greeting.greet(winner);
    }
}