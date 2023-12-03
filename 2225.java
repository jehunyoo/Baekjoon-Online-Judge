import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        int divisor = 1_000_000_000;

        int[][] dp = new int[N + 1][K + 1];
        dp[0][0] = 1;

        for (int k = 1; k <= K; k++) {
            int sum = 0;

            for (int n = 0; n <= N; n++) {
                sum = (sum + dp[n][k - 1] % divisor) % divisor;
                dp[n][k] = sum;
            }
        }

        bw.write(dp[N][K] + "\n");
        bw.close();
    }
}