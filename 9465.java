import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];
            sticker[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sticker[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] dp = new int[3][N];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for (int i = 1; i < N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[2][i - 1]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[2][i - 1]) + sticker[1][i];
                dp[2][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
            }

            bw.write(Math.max(dp[0][N - 1], dp[1][N - 1]) + "\n");
        }

        bw.close();
    }
}