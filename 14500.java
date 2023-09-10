import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(in[j]);
        }

        Tetromino[] tetrominos = new Tetromino[]{
                // ㅁ형
                new Tetromino(new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, 1}),
                // 1형
                new Tetromino(new int[]{0, 0, 0, 0}, new int[]{0, 1, 2, 3}),
                new Tetromino(new int[]{0, 1, 2, 3}, new int[]{0, 0, 0, 0}),
                // z형 대칭
                new Tetromino(new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, -1}),
                new Tetromino(new int[]{0, 0, 1, 1}, new int[]{0, 1, 1, 2}),
                // z형
                new Tetromino(new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1}),
                new Tetromino(new int[]{0, 0, -1, -1}, new int[]{0, 1, 1, 2}),
                // ㄴ형
                new Tetromino(new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, 1}),
                new Tetromino(new int[]{0, 0, 0, -1}, new int[]{0, 1, 2, 2}),
                new Tetromino(new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 1}),
                new Tetromino(new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 0}),
                // ㄴ형 대칭
                new Tetromino(new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, -1}),
                new Tetromino(new int[]{0, 1, 1, 1}, new int[]{0, 0, 1, 2}),
                new Tetromino(new int[]{0, 0, 1, 2}, new int[]{0, 1, 0, 0}),
                new Tetromino(new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 2}),
                // ㅜ형
                new Tetromino(new int[]{0, 0, 0, 1}, new int[]{0, -1, 1, 0}),
                new Tetromino(new int[]{0, -1, 1, 0}, new int[]{0, 0, 0, 1}),
                new Tetromino(new int[]{0, 0, 0, -1}, new int[]{0, -1, 1, 0}),
                new Tetromino(new int[]{0, 0, -1, 1}, new int[]{0, -1, 0, 0})
        };

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (Tetromino tetromino : tetrominos) {
                    answer = Math.max(answer, tetromino.calculateScore(board, i, j));
                }
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}

class Tetromino {

    private int[] dx;
    private int[] dy;

    public Tetromino(int[] dx, int[] dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int calculateScore(int[][] board, int x, int y) {
        int score = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];

            if (0 <= nx && nx < board.length && 0 <= ny && ny < board[0].length) {
                score += board[nx][ny];
            } else {
                break;
            }
        }

        return score;
    }
}