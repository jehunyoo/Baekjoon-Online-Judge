import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] ascDp = new int[N];
        int[] descDp = new int[N];

        Arrays.fill(ascDp, 1);
        Arrays.fill(descDp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    ascDp[i] = Math.max(ascDp[i], ascDp[j] + 1);
                }
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i]) {
                    descDp[i] = Math.max(descDp[i], descDp[j] + 1);
                }
            }
        }

        int length = 1;
        for (int i = 0; i < N; i++) {
            length = Math.max(length, ascDp[i] + descDp[i] - 1);
        }

        bw.write(length + "\n");
        bw.close();
    }
}