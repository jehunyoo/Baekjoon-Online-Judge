import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = in[0], B = in[1];
        int count = 1;

        while (A < B) {
            if (B % 2 == 0)
                B /= 2;
            else if (B % 10 == 1)
                B /= 10;
            else
                break;

            count++;
        }

        bw.write((A == B ? count : -1) + "\n");
        bw.close();
    }

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = in[0], B = in[1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{A, 0});
        int answer = -1;

        while (queue.size() > 0) {
            int[] item = queue.remove();
            int num = item[0], count = item[1];

            if (num == B) {
                answer = count + 1;
                break;
            }

            if (2 * num <= B)
                queue.add(new int[]{2 * num, count + 1});
            if (Long.parseLong(num + "1") <= B)
                queue.add(new int[]{Integer.parseInt(num + "1"), count + 1});
        }

        bw.write(answer + "\n");
        bw.close();
    }
}