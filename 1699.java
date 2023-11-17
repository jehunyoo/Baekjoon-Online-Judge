import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        List<Integer> squares = new ArrayList<>();

        for (int num = 1; num * num <= N; num++) {
            dp[num * num] = 1;
            squares.add(num * num);
        }

        for (int num = 1; num <= N; num++) {
            if (squares.contains(num)) {
                continue;
            }

            dp[num] = Integer.MAX_VALUE;
            for (int square : squares) {
                if (num - square > 0) {
                    dp[num] = Math.min(dp[num], dp[num - square] + 1);
                }
            }
        }
        
        bw.write(dp[N] + "\n");
        bw.close();
    }
}