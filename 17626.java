import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        for (int i = 1; i * i <= N; i++) {
            dp[i * i] = 1;
        }

        for (int num = 1; num <= N; num++) {
            if (dp[num] == 0) {
                for (int i = 1; i * i <= num; i++) {
                    int count = dp[i * i] + dp[num - (i * i)];
                    dp[num] = dp[num] > 0 ? Math.min(dp[num], count) : count;
                }
            }
        }

        bw.write(dp[N] + "\n");
        bw.close();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        loop: for (int a = 0; a < Math.sqrt(N) + 1; a++) {
            for (int b = 0; b < Math.sqrt(N) + 1; b++) {
                for (int c = 0; c < Math.sqrt(N) + 1; c++) {
                    for (int d = 0; d < Math.sqrt(N) + 1; d++) {
                        if (a * a + b * b + c * c + d * d == N) {
                            answer += a > 0 ? 1 : 0;
                            answer += b > 0 ? 1 : 0;
                            answer += c > 0 ? 1 : 0;
                            answer += d > 0 ? 1 : 0;
                            break loop;
                        }
                    }
                }
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}