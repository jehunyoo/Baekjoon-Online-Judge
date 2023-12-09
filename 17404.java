import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] paints = new int[N + 1][3];

        for (int n = 1; n <= N; n++) {
            String[] in = br.readLine().split(" ");
            int R = Integer.parseInt(in[0]);
            int G = Integer.parseInt(in[1]);
            int B = Integer.parseInt(in[2]);

            paints[n][0] = R;
            paints[n][1] = G;
            paints[n][2] = B;
        }

        int answer = Integer.MAX_VALUE;

        for (int color = 0; color < 3; color++) {

            int[][] dp = new int[N + 1][3];
            Arrays.fill(dp[1], 1_000_000);
            dp[1][color] = paints[1][color];

            for (int n = 2; n <= N; n++) {
                dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + paints[n][0];
                dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + paints[n][1];
                dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]) + paints[n][2];
            }

            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != color) {
                    answer = Math.min(answer, dp[N][lastColor]);
                }
            }
        }

        bw.write(answer +"\n");
        bw.close();
    }
}