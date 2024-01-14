import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];

        for (int n = 0; n < N; n++) {
            sequence[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(sequence);

        int sum = N > 1 ? 0 : sequence[0];

        for (int n = 0; n < N && N > 1; n += 2) {
            if (sequence[n] >= 0) {
                break;
            } else if (n == N - 1 || (n + 1 < N && sequence[n + 1] > 0)) {
                sum += sequence[n];
                break;
            }

            if (sequence[n] * sequence[n + 1] >= 0) {
                sum += sequence[n] * sequence[n + 1];
            }
        }

        for (int n = N - 1; n >= 0 && N > 1; n -= 2) {
            if (sequence[n] <= 0) {
                break;
            } else if (n == 0 || (n - 1 >= 0 && sequence[n - 1] <= 0)) {
                sum += sequence[n];
                break;
            }

            if (sequence[n] == 1 || sequence[n - 1] == 1) {
                sum += sequence[n] + sequence[n - 1];
            } else if (sequence[n] * sequence[n - 1] > 0) {
                sum += sequence[n] * sequence[n - 1];
            }
        }

        bw.write(sum + "\n");
        bw.close();
    }
}