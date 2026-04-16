package TicTacToe;

import java.util.*;

interface Iobserver {
    void update(String msg);
}


class ConsoleNotifier implements Iobserver {

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

    public void incrementScore() {
        score++;
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

    boolean checkDrawCondition(Board board);
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
    public boolean checkDrawCondition(Board board) {
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


//Game Class --> Observable
class TicTacToeGame {
    private Board board;
    private Deque<TicTacToePlayer> players = new ArrayDeque<>();
    private TicTacToeRules rules;
    private List<Iobserver> observers;
    private boolean gameOver;

    public TicTacToeGame(int size) {
        this.board = new Board(size);
        observers = new ArrayList<>();
        rules = new StandardTicTacToeRules();
        observers = new ArrayList<>();
        gameOver = false;
    }

    public void addPlayer(TicTacToePlayer player) {
        players.add(player);
    }

    public void addObserver(Iobserver observer) {
        observers.add(observer);
    }

    public void clearObservers() {
        observers.clear();
    }

    public void notify(String msg) {
        for (Iobserver observer : observers) {
            observer.update(msg);
        }
    }

    public void play() {
        if (players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }

        notify("Tic Tac Toe Game Started!");

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            board.display();

            // Take out the current player from dequeue
            TicTacToePlayer currentPlayer = players.peekFirst();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getSymbol().getmark() + ") - Enter row and column: ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // check if move is valid
            if (rules.isValidMove(board, row, col)) {
                board.placeMark(row, col, currentPlayer.getSymbol());
                notify(currentPlayer.getName() + " played (" + row + "," + col + ")");

                if (rules.checkWinCondition(board, currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify(currentPlayer.getName() + " wins!");

                    gameOver = true;
                } else if (rules.checkDrawCondition(board)) {
                    board.display();

                    System.out.println("It's a draw!");
                    notify("Game is Draw!");

                    gameOver = true;
                } else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

}

enum GameType {
    STANDARD
}

class TicTacToeGameFactory {
    public static TicTacToeGame createGame(GameType gt, int boardSize) {
        if (GameType.STANDARD == gt) {
            return new TicTacToeGame(boardSize);
        }
        return null;
    }
}


public class TicTacToe {

    public static void main(String[] args) {
        System.out.println("=== TIC TAC TOE GAME ===");

        // Create game with custom board size
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();

        TicTacToeGame game = TicTacToeGameFactory.createGame(GameType.STANDARD, boardSize);

        // Add observer
        Iobserver notifier = new ConsoleNotifier();
        game.addObserver(notifier);

        // Create players with custom symbols
        TicTacToePlayer player1 = new TicTacToePlayer(1, "Aditya", new Symbol('X'));
        TicTacToePlayer player2 = new TicTacToePlayer(2, "Harshita", new Symbol('O'));

        game.addPlayer(player1);
        game.addPlayer(player2);

        // Play the game
        game.play();

        scanner.close();
    }


}
