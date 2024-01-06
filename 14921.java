import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = Integer.MAX_VALUE;

        int left = 0;
        int right = values.length - 1;

        while (left < right) {
            int sum = values[left] + values[right];
            if (Math.abs(sum) < Math.abs(answer)) {
                answer = sum;
            }

            if (sum == 0) {
                break;
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}