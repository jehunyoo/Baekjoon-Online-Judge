import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int m = coin; m <= M; m++) {
                    dp[m] += dp[m - coin];
                }
            }

            bw.write(dp[M] + "\n");
        }

        bw.close();
    }
}