import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());

        State[][] dp = new State[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = State.NULL;
            }
        }

        for (int i = 1; i <= N; i++) {
            dp[i][i] = State.TRUE;
        }

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");
            int S = Integer.parseInt(in[0]);
            int E = Integer.parseInt(in[1]);

            if (dp[S][E] == State.NULL) {
                if ((E - S + 1) % 2 == 0) {
                    int midLeft = (S + E) / 2;
                    int midRight = midLeft + 1;

                    boolean seed = numbers[midLeft] == numbers[midRight];
                    dp[midLeft][midRight] = seed ? State.TRUE : State.FALSE;

                    for (int i = 1; S <= midLeft - i && midRight + 1 <= E; i++) {
                        if (seed && numbers[midLeft - i] == numbers[midRight + 1]) {
                            seed = true;
                            dp[midLeft - i][midRight + i] = State.TRUE;
                        } else {
                            seed = false;
                            dp[midLeft - i][midRight + i] = State.FALSE;
                        }
                    }

                } else {
                    int mid = (S + E) / 2;

                    for (int i = 1; S <= mid - i && mid + i <= E; i++) {
                        if (dp[mid - i + 1][mid + i - 1] == State.TRUE && numbers[mid - i] == numbers[mid + i]) {
                            dp[mid - i][mid + i] = State.TRUE;
                        } else {
                            dp[mid - i][mid + i] = State.FALSE;
                        }
                    }
                }
            }

            bw.write((dp[S][E] == State.TRUE ? 1 : 0) + "\n");
        }

        bw.close();
    }

    private enum State {
        TRUE, FALSE, NULL;
    }
}