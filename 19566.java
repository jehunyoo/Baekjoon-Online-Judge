import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        long K = Long.parseLong(in[1]);
        long[] A = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long[] sum = new long[N + 1];

        for (int n = 1; n <= N; n++) {
            sum[n] = sum[n - 1] + A[n - 1];
        }

        Map<Long, Integer> counter = new HashMap<>();

        for (int n = 0; n <= N; n++) {
            counter.merge(sum[n] - (K * n), 1, Integer::sum);
        }
        
        long count = 0L;
        dp = new long[N + 2][3];
        for (int value : counter.values()) {
            if (value > 1) {
                count += combine(value, 2);
            }
        }

        bw.write(count + "\n");
        bw.close();
    }

    private static long combine(int n, int r) {
        if (r == 0 || r == n) {
            return 1L;
        }

        if (dp[n][r] > 0L) {
            return dp[n][r];
        }

        return dp[n][r] = combine(n - 1, r - 1) + combine(n - 1, r);
    }
}