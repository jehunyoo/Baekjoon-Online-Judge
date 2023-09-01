import java.util.*;
import java.io.*;

public class Main {

    static int N, M, D;
    static int[][] battlefield;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        D = Integer.parseInt(in[2]);

        battlefield = new int[N][M];
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                battlefield[i][j] = Integer.parseInt(in[j]);
            }
        }

        int answer = 0;

        for (int archer1 = 0; archer1 < M; archer1++) {
            for (int archer2 = archer1 + 1; archer2 < M; archer2++) {
                for (int archer3 = archer2 + 1; archer3 < M; archer3++) {
                    answer = Math.max(answer, playGame(new int[]{archer1, archer2, archer3}));
                }
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    public static int playGame(int[] archers) {
        int[][] field = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                field[i][j] = battlefield[i][j];
            }
        }

        int count = 0;

        for (int row = N - 1; row >= 0; row--) {
            List<int[]> targets = new ArrayList<>();
            for (int archer : archers) { // find targets
                int[] target = findTarget(field, row, archer);
                if (target.length > 0) {
                    targets.add(target);
                }
            }
            for (int[] target : targets) { // attack targets at the same time
                int r = target[0], c = target[1];
                count += field[r][c];
                field[r][c] = 0;
            }
        }

        return count;
    }

    public static int[] findTarget(int[][] field, int row, int col) {
        for (int d = 0; d < D; d++) {
            int nrow = row, ncol = col - d;
            while (ncol <= col + d) {
                if (0 <= nrow && nrow < N && 0 <= ncol && ncol < M && field[nrow][ncol] == 1)
                    return new int[]{nrow, ncol};
                ncol++;
                nrow = row - (d - Math.abs(ncol - col));
            }
        }
        return new int[]{};
    }
}