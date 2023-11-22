import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        if (N % 2 == 0) {
            dp[2] = 3;

            for (int n = 4, sum = 0; n <= N; n += 2) {
                dp[n] = 2 + 3 * dp[n - 2] + 2 * sum;
                sum += dp[n - 2];
            }
        }

        bw.write(dp[N] + "\n");
        bw.close();
    }
}