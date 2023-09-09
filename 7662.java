import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            DoubleEndedPriorityQueue depq = new DoubleEndedPriorityQueue();

            for (int k = 0; k < K; k++) {
                String[] in = br.readLine().split(" ");
                char operator = in[0].charAt(0);
                int n = Integer.parseInt(in[1]);

                if (operator == 'D') {
                    if (n == 1) {
                        depq.popMax();
                    } else if (n == -1) {
                        depq.popMin();
                    }
                } else if (operator == 'I') {
                    depq.push(n);
                }
            }

            String answer = depq.size() == 0 ? "EMPTY" : String.format("%d %d", depq.peekMax(), depq.peekMin());
            bw.write(answer + "\n");
        }

        bw.close();
    }
}

class DoubleEndedPriorityQueue {

    private TreeMap<Integer, Integer> map;

    public DoubleEndedPriorityQueue() {
        this.map = new TreeMap<>();
    }

    public void push(int n) {
        if (map.containsKey(n)) {
            map.put(n, map.get(n) + 1);
        } else {
            map.put(n, 1);
        }
    }

    public void popMax() {
        if (!map.isEmpty()) {
            int max = map.lastKey();
            int count = map.get(max);

            if (count > 1)
                map.put(max, count - 1);
            else
                map.remove(max);
        }
    }

    public void popMin() {
        if (!map.isEmpty()) {
            int min = map.firstKey();
            int count = map.get(min);

            if (count > 1)
                map.put(min, count - 1);
            else
                map.remove(min);
        }
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int peekMin() {
        return map.firstKey();
    }

    public int size() {
        return map.size();
    }
}