import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);
        int[] coins = new int[N];
        for(int i=0; i<N; i++)
            coins[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[K + 1];
        dp[0] = 1;

        for(int coin: coins) {
            for(int k=coin; k<=K; k++) {
                dp[k] += dp[k - coin];
            }
        }

        bw.write(dp[K] + "\n");

        bw.flush();
        bw.close();
    }
}