import java.io.*;

public class Main {

    static int[][] board;
    static int N, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        count = 0;

        for(int col=0; col<N; col++) {
            findCase(0, col);
        }

        bw.write(count + "\n");

        bw.flush();
        bw.close();
    }

    public static void findCase(int row, int col) {
        if(row == N - 1) {
            count++;
            return;
        }

        changeCellStatus(row, col, Cell.UNSAFE);
        for(int c=0; c<N; c++) {
            if(board[row + 1][c] == 0) {
                findCase(row + 1, c);
            }
        }
        changeCellStatus(row, col, Cell.SAFE);
    }

    public static void changeCellStatus(int row, int col, Cell cell) {
        int delta = cell == Cell.SAFE? -1: 1;

        for(int r=row+1; r<N; r++) {
            // column
            board[r][col] += delta;

            // top left to bottom right diagonal
            int c = r + (col - row);
            if(0 <= c && c < N)
                board[r][c] += delta;

            // top right to bottom left diagonal
            c = (row + col) - r;
            if(0 <= c && c < N)
                board[r][c] += delta;
        }
    }

    enum Cell {SAFE, UNSAFE}
}