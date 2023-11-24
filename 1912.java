import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N];
        int max = dp[0] = seq[0];

        for (int i = 1; i < seq.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] += dp[i - 1] + seq[i];
            } else {
                dp[i] = seq[i];
            }

            max = Math.max(max, dp[i]);
        }

        bw.write(max + "\n");
        bw.close();
    }
}