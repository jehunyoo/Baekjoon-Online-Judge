import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] P = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N + 1];

        for (int n = 1; n <= N; n++) {
            for (int i = 0; i < n; i++) {
                dp[n] = Math.max(dp[n], P[n - i] + dp[i]);
            }
        }

        bw.write(dp[N] + "\n");
        bw.close();
    }
}