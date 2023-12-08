import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long[] values = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long small = Integer.MAX_VALUE;
        long large = Integer.MAX_VALUE;

        loop: for (int i = 0; i < values.length; i++) {
            int left = 0;
            int right = values.length - 1;

            long value = values[i];
            long target = -value;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (values[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (left != i && Math.abs(value + values[left]) < Math.abs(small + large)) {
                small = Math.min(value, values[left]);
                large = Math.max(value, values[left]);
            }

            if (left > 0 && left - 1 != i && Math.abs(value + values[left - 1]) < Math.abs(small + large)) {
                small = Math.min(value, values[left - 1]);
                large = Math.max(value, values[left - 1]);
            }

            if (left < values.length - 1 && left + 1 != i && Math.abs(value + values[left + 1]) < Math.abs(small + large)) {
                small = Math.min(value, values[left + 1]);
                large = Math.max(value, values[left + 1]);
            }
        }


        bw.write(small + " " + large + "\n");
        bw.close();
    }
}