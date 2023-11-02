import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] S;
    private static Set<Integer> visited;
    private static int combination;
    private static int answer = 100 * 20;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                S[i + 1][j + 1] = Integer.parseInt(in[j]);
            }
        }

        visited = new HashSet<>();
        combination = 0;
        combine(N, 0, 0b10);

        bw.write(answer + "\n");
        bw.close();
    }

    private static void combine(int N, int R, int prevMask) {
        if (R == N / 2) {
            int reverseMask = ((int) Math.pow(2, N + 1)) - 2;

            if (visited.contains(combination)) {
                return;
            }

            visited.add(combination);
            visited.add(combination ^ reverseMask);

            int diff = Math.abs(sumAbilities(combination) - sumAbilities(combination ^ reverseMask));
            answer = Math.min(answer, diff);
        }

        for (int mask = prevMask; mask <= (1 << N); mask <<= 1) {
            combination ^= mask;
            combine(N, R + 1, mask << 1);
            combination ^= mask;
        }
    }

    private static int sumAbilities(int combination) {
        int sum = 0;
        for (int i = combination >> 1, x = 1; i > 0; i >>= 1, x++) {
            for (int j = combination >> 1, y = 1; j > 0; j >>= 1, y++) {
                if ((i & 1) == 1 && (j & 1) == 1) {
                    sum += S[x][y];
                }
            }
        }

        return sum;
    }
}