

interface Iobserver {
    void update(String msg);
}


class ConcreteObserver implements Iobserver {

    @Override
    public void update(String msg) {
        System.out.println("[Notification]" + msg);
    }
}


class Symbol {
    private char mark;

    public Symbol(char mark) {
        this.mark = mark;
    }

    public char getmark() {
        return this.mark;
    }
}


class Board {
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;

    Board(int size) {
        size = size;
        emptyCell = new Symbol('_');
        grid = new Symbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = emptyCell;
            }
        }
    }

    public boolean isCellEmpty(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return false;
        }
        return grid[r][c] == emptyCell;
    }

    public boolean placeMark(int r, int c, Symbol mark) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return false;
        }
        if (!isCellEmpty(r, c)) {
            return false;
        }
        grid[r][c] = mark;
        return true;
    }

    public Symbol getCell(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return emptyCell;
        }
        return grid[r][c];
    }

    public int getsize() {
        return size;
    }

    public Symbol getEmptyCell() {
        return emptyCell;
    }

    public void display() {
        System.out.print("\n ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getmark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

// ## Player Class
class TicTacToePlayer {

    private int playerId;
    private String name;
    private Symbol symbol;
    private int score;

    public TicTacToePlayer(int playerId, String name, Symbol symbol) {
        this.playerId = playerId;
        this.name = name;
        this.symbol = symbol;
        score = 0;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getScore() {
        return score;
    }
}


// Strategy Pattern for game Rule
interface TicTacToeRules {
    boolean isValidMove(Board board, int r, int c);

    boolean checkWinCondition(Board board, Symbol symbol);

    boolean checkDrawCondition(Board board, Symbol symbol);
}


// Standard Tic Tac Toe Rule
class StandardTicTacToeRules implements TicTacToeRules {

    @Override
    public boolean isValidMove(Board board, int r, int c) {
        return board.isCellEmpty(r, c);
    }

    @Override
    public boolean checkWinCondition(Board board, Symbol symbol) {

        int n = board.getsize();

        //check Row
        for (int i = 0; i < n; i++) {
            boolean win = true;
            for (int j = 0; j < n; j++) {
                if (board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        //check for column
        for (int j = 0; j < n; j++) {
            boolean win = true;
            for (int i = 0; i < n; i++) {
                if (board.getCell(i, j) == symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        //check for diagonal
        boolean win = true;
        for (int i = 0; i < n; i++) {
            if (board.getCell(i, i) != symbol) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }
        //check for anti-diagonal
        for (int i = 0; i < n; i++) {
            if (board.getCell(i, n - i - 1) != symbol) {
                win = false;
                break;
            }
        }

        return win;
    }


    // if all cell are filled and no Winner
    @Override
    public boolean checkDrawCondition(Board board, Symbol symbol) {
        int n = board.getsize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.getCell(i, j) == board.getEmptyCell()) {
                    return false;
                }
            }
        }
        return true;
    }
}


public class TicTacToe {


}
