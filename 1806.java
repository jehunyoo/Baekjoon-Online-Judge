import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int S = Integer.parseInt(in[1]);
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] sums = new int[N];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            sums[i] = sum;
        }

        int length = N + 1;
        int left = -1, right = 0;

        while (left < right && right < N) {
            int subSum = left >= 0 ? sums[right] - sums[left] : sums[right];

            if (subSum >= S) {
                length = Math.min(length, right - left);
                left++;
            } else {
                right++;
            }
        }

        bw.write((length <= N ? length : 0) + "\n");
        bw.close();
    }
}