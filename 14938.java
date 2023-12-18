import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int R = Integer.parseInt(in[2]);

        int[] items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] distances = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(distances[i], 100);
            distances[i][i] = 0;
        }

        for (int r = 0; r < R; r++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int I = Integer.parseInt(in[2]);

            distances[A][B] = distances[B][A] = Math.min(distances[A][B], I);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    distances[i][j] = distances[j][i] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int count = 0;

            for (int j = 1; j <= N; j++) {
                if (distances[i][j] <= M) {
                    count += items[j - 1];
                }
            }

            answer = Math.max(answer, count);
        }

        bw.write(answer + "\n");
        bw.close();
    }
}