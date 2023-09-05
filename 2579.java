import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] steps = new int[N];
        for (int i = 0; i < N; i++)
            steps[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][2]; // continuous steps: 0, 1
        dp[0][0] = dp[0][1] = steps[0];
        if (N > 1) {
            dp[1][0] = steps[1];
            dp[1][1] = steps[0] + steps[1];
        }

        for (int i = 2; i < N; i++) {
            dp[i][0] = steps[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            dp[i][1] = steps[i] + dp[i - 1][0];
        }

        bw.write(Math.max(dp[N - 1][0], dp[N - 1][1]) + "\n");

        bw.flush();
        bw.close();
    }
}