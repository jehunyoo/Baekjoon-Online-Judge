import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][3];

        int divisor = 9901;

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for (int n = 2; n <= N; n++) {
            dp[n][0] = (dp[n - 1][0] + (dp[n - 1][1] + dp[n - 1][2]) % divisor) % divisor;
            dp[n][1] = (dp[n - 1][0] + dp[n - 1][2]) % divisor;
            dp[n][2] = (dp[n - 1][0] + dp[n - 1][1]) % divisor;
        }

        bw.write((dp[N][0] + dp[N][1] + dp[N][2]) % divisor + "\n");
        bw.close();
    }
}