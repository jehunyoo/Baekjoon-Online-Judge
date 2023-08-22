import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);
        int[] weight = new int[N];
        int[] value = new int[N];
        for(int i=0; i<N; i++) {
            in = br.readLine().split(" ");
            int W = Integer.parseInt(in[0]), V = Integer.parseInt(in[1]);
            weight[i] = W;
            value[i] = V;
        }

        int[][] dp = new int[N + 1][K + 1];
        for(int n=0; n<=N; n++) {
            for(int k=0; k<=K; k++) {
                if(n == 0 || k == 0)
                    dp[n][k] = 0;
                else if(weight[n - 1] <= k)
                    dp[n][k] = Math.max(value[n - 1] + dp[n - 1][k - weight[n - 1]], dp[n - 1][k]);
                else
                    dp[n][k] = dp[n - 1][k];
            }
        }

        bw.write(dp[N][K] + "\n");

        bw.flush();
        bw.close();
    }
}