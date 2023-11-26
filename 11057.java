import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10];

        int divisor = 10_007;

        Arrays.fill(dp[1], 1);

        for (int n = 2; n <= N; n++) {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                dp[n][i] = (dp[n - 1][i] + sum) % divisor;
                sum = (sum + dp[n - 1][i]) % divisor;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N][i]) % divisor;
        }

        bw.write(answer + "\n");
        bw.close();
    }
}