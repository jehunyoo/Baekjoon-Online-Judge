import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int X = Integer.parseInt(in[2]), Y = Integer.parseInt(in[3]);
        int K = Integer.parseInt(in[4]);

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(in[j]);
            }
        }

        String[] commands = br.readLine().split(" ");
        Dice dice = new Dice(X, Y, board);

        for (String command : commands) {
            int value = dice.move(command);
            if (value >= 0)
                bw.write(value + "\n");
        }

        bw.close();
    }
}

class Dice {

    private int x;
    private int y;
    private int[] values = new int[7];
    private int[][] board;

    private int center = 1;
    private int east = 3;
    private int south = 5;

    public Dice(int x, int y, int[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public int move(String direction) {
        int value = -1;

        switch (direction) {
            case "1":
                if (0 <= y + 1 && y + 1 < board[0].length) {
                    y++;
                    value = moveEast();
                }
                break;
            case "2":
                if (0 <= y - 1 && y - 1 < board[0].length) {
                    y--;
                    value = moveWest();
                }
                break;
            case "3":
                if (0 <= x - 1 && x - 1 < board.length) {
                    x--;
                    value = moveNorth();
                }
                break;
            case "4":
                if (0 <= x + 1 && x + 1 < board.length) {
                    x++;
                    value = moveSouth();
                }
                break;
        }

        return value;
    }

    private int moveEast() {
        int newEast = center;
        center = 7 - east;
        east = newEast;

        copy();

        return values[center];
    }

    private int moveWest() {
        int newWest = center;
        center = east;
        east = 7 - newWest;

        copy();

        return values[center];
    }

    private int moveNorth() {
        int newNorth = center;
        center = south;
        south = 7 - newNorth;

        copy();

        return values[center];
    }

    private int moveSouth() {
        int newSouth = center;
        center = 7 - south;
        south = newSouth;

        copy();

        return values[center];
    }

    private void copy() {
        if (board[x][y] == 0)
            board[x][y] = values[7 - center];
        else {
            values[7 - center] = board[x][y];
            board[x][y] = 0;
        }
    }
}