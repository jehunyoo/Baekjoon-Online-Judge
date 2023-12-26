import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        LinkedList<Gem> gems = new LinkedList<>();

        for (int n = 0; n < N; n++) {
            in = br.readLine().split(" ");
            int M = Integer.parseInt(in[0]);
            int V = Integer.parseInt(in[1]);

            gems.add(new Gem(M, V));
        }

        int[] bags = new int[K];

        for (int k = 0; k < K; k++) {
            int C = Integer.parseInt(br.readLine());

            bags[k] = C;
        }

        Collections.sort(gems);
        Arrays.sort(bags);

        long answer = 0L;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int bag : bags) {
            while (!gems.isEmpty() && gems.peek().M <= bag) {
                pq.add(-gems.remove().V);
            }

            if (!pq.isEmpty()) {
                answer += -pq.remove();
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }

    private static class Gem implements Comparable<Gem> {
        int M;
        int V;

        public Gem(int M, int V) {
            this.M = M;
            this.V = V;
        }

        @Override
        public int compareTo(Gem other) {
            if (this.M == other.M) {
                return this.V - other.V;
            }

            return this.M - other.M;
        }
    }
}