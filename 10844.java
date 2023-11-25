import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10];

        int divisor = 1_000_000_000;

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int n = 2; n <= N; n++) {
            for (int i = 0; i < 10; i++) {
                if (i - 1 >= 0) {
                    dp[n][i - 1] = (dp[n][i - 1] % divisor + dp[n - 1][i] % divisor) % divisor;
                }
                if (i + 1 < 10) {
                    dp[n][i + 1] = (dp[n][i + 1] % divisor + dp[n - 1][i] % divisor) % divisor;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum % divisor + dp[N][i] % divisor) % divisor;
        }

        bw.write(sum + "\n");
        bw.close();
    }
}