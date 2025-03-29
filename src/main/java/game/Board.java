package game;

import logging.AppLogger;

public class Board {
    private final char[][] field = new char[3][3];
    private static final byte SIZE = 3;
    private static final byte MAX_COUNT = 9;
    private static final byte MIN_COUNT = 1;
    private byte cellFilled;

    public Board() {
        initializeField(true);
        cellFilled = 0;
    }

    private void initializeField(boolean isInfo) {
        byte counter = 0;
        for (byte i = 0; i < SIZE; i++) {
            for (byte j = 0; j < SIZE; j++) {
                counter++;
                field[i][j] = isInfo ? (char) ('0' + counter) : ' ';
            }
        }
    }

    public void clear() {
        initializeField(false);
        cellFilled = 0;
    }

    public void show() {
        char verticalDelimiter = '|';
        String horizontalDelimiter = "-----------";
        StringBuilder sb = new StringBuilder();

        for (byte i = 0; i < SIZE; i++) {
            for (byte j = 0; j < SIZE; j++) {
                sb.append(" ").append(field[i][j]).append(" ");
                if (j + 1 != SIZE) {
                    sb.append(verticalDelimiter);
                }
            }
            String row = sb.toString();
            AppLogger.log(row);
            if (i + 1 != SIZE) {
                AppLogger.log(horizontalDelimiter);
            }
            sb.delete(0, sb.length());
        }
        AppLogger.log("");
    }

    public boolean setCell(byte selected, char symbol) {
        byte counter = 0;
        for (byte i = 0; i < SIZE; i++) {
            for (byte j = 0; j < SIZE; j++) {
                counter++;
                if (counter == selected) {
                    if (field[i][j] == ' ') {
                        field[i][j] = symbol;
                        cellFilled++;
                        return true;
                    } else {
                        return false;
                    }

                }
            }
        }
        return false;
    }

    public boolean isFilled() {
        return cellFilled == MAX_COUNT;
    }

    public boolean checkLines(char symbol) {
        return (checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol));
    }

    private boolean checkRows(char symbol) {
        boolean isAllMatch = true;

        for (byte i = 0; i < SIZE; i++) {
            for (byte j = 0; j < SIZE; j++) {
                if (field[i][j] != symbol) {
                    isAllMatch = false;
                    break;
                }
            }
            if (i + 1 != SIZE) {
                if (isAllMatch) {
                    return true;
                } else {
                    isAllMatch = true;
                }
            }
        }

        return isAllMatch;
    }

    private boolean checkColumns(char symbol) {
        boolean isAllMatch = true;

        for (byte j = 0; j < SIZE; j++) {
            for (byte i = 0; i < SIZE; i++) {
                if (field[i][j] != symbol) {
                    isAllMatch = false;
                    break;
                }
            }

            if (j + 1 != SIZE) {
                if (isAllMatch) {
                    return true;
                } else {
                    isAllMatch = true;
                }
            }
        }
        return isAllMatch;
    }

    private boolean checkDiagonals(char symbol) {
        boolean isAllMatch = true;
        for (byte i = 0; i < SIZE; i++) {
            if (field[i][i] != symbol) {
                isAllMatch = false;
                break;
            }
        }
        if (isAllMatch) {
            return true;
        }

        isAllMatch = true;
        for (byte i = 0; i < SIZE; i++) {
            if (field[SIZE - 1 - i][i] != symbol) {
                isAllMatch = false;
                break;
            }
        }

        return isAllMatch;
    }

    public byte getMinCount() {
        return MIN_COUNT;
    }

    public byte getMaxCount() {
        return MAX_COUNT;
    }
}
