import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        int[][] array = new int[N + 1][M + 1];

        for (int n = 1; n <= N; n++) {
            in = br.readLine().split(" ");
            for (int m = 1; m <= M; m++) {
                array[n][m] = Integer.parseInt(in[m - 1]);
            }
        }

        int[][] sum = new int[N + 1][M + 1];

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                sum[n][m] = array[n][m];

                if (0 <= n - 1 && 0 <= m - 1) {
                    sum[n][m] -= sum[n - 1][m - 1];
                }

                if (0 <= n - 1) {
                    sum[n][m] += sum[n - 1][m];
                }

                if (0 <= m - 1) {
                    sum[n][m] += sum[n][m - 1];
                }
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            in = br.readLine().split(" ");
            int i = Integer.parseInt(in[0]);
            int j = Integer.parseInt(in[1]);
            int x = Integer.parseInt(in[2]);
            int y = Integer.parseInt(in[3]);

            int answer = sum[x][y] - sum[x][j - 1] - sum[i - 1][y] + sum[i - 1][j - 1];

            bw.write(answer + "\n");
        }

        bw.close();
    }
}