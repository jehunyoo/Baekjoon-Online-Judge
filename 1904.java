import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        final int divisor = 15746;

        for (int n = 2; n <= N; n++) {
            dp[n] = (dp[n] + dp[n - 1]) % divisor;
            dp[n] = (dp[n] + dp[n - 2]) % divisor;
        }

        bw.write(dp[N] + "\n");
        bw.close();
    }
}