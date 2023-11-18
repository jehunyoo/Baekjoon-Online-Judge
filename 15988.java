import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1_000_000 + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        int divisor = 1_000_000_009;

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            if (dp[N] > 0) {
                bw.write(dp[N] + "\n");
                continue;
            }

            for (int n = 4; n <= N; n++) {
                if (dp[n] > 0) {
                    continue;
                }

                dp[n] = (dp[n - 1] + dp[n - 2] + dp[n - 3]) % divisor;
            }

            bw.write(dp[N] + "\n");
        }


        bw.close();
    }
}