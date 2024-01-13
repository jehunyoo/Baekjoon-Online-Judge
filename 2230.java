import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        int[] A = new int[N];

        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(A);

        int answer = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;

        while (left <= right && right < N) {
            int diff = A[right] - A[left];

            if (diff == M) {
                answer = M;
                break;
            } else if (diff < M) {
                right++;
            } else {
                answer = Math.min(answer, diff);
                left++;
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}