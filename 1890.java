import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int[][] board;
    private static long[][] count;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(in[j]);
            }
        }

        count = new long[N][N];
        count[0][0] = 1;

        countPath(0);

        bw.write(count[N - 1][N - 1] + "\n");
        bw.close();
    }

    private static void countPath(int level) {
        if (level >= board.length) {
            return;
        }

        for (int c = 0; c < level; c++) {
            countPathOf(c, level);
            countPathOf(level, c);
        }
        countPathOf(level, level);

        countPath(level + 1);
    }

    private static void countPathOf(int x, int y) {
        for (int col = 0; col < y; col++) {
            if (col + board[x][col] == y) {
                count[x][y] += count[x][col];
            }
        }

        for (int row = 0; row < x; row++) {
            if (row + board[row][y] == x) {
                count[x][y] += count[row][y];
            }
        }
    }
}
