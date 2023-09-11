import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] price = new int[N][3];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                price[i][j] = Integer.parseInt(in[j]);
            }
        }

        int[][] dp = new int[N][3];
        dp[0][0] = price[0][0];
        dp[0][1] = price[0][1];
        dp[0][2] = price[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = price[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = price[i][1] + Math.min(dp[i - 1][2], dp[i - 1][0]);
            dp[i][2] = price[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }


        bw.write(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])) + "\n");
        bw.close();
    }
}