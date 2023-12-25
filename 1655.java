import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int n = 1; n <= N; n++) {
            int I = Integer.parseInt(br.readLine());

            if (n % 2 == 1) {
                maxHeap.add(-I);
            } else {
                minHeap.add(I);
            }

            if (!minHeap.isEmpty() && -maxHeap.peek() > minHeap.peek()) {
                int max = -maxHeap.remove();
                int min = minHeap.remove();

                maxHeap.add(-min);
                minHeap.add(max);
            }

            bw.write(-maxHeap.peek() + "\n");
        }

        bw.close();
    }
}