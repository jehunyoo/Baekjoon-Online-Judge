import java.util.Map.Entry;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int K = Integer.parseInt(in[2]);

        Map<Integer, Integer>[] roads = new Map[N + 1];
        for (int n = 1; n <= N; n++) {
            roads[n] = new HashMap<>();
        }

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);

            roads[A].merge(B, C, Math::min);
            roads[B].merge(A, C, Math::min);
        }

        long[][] times = new long[N + 1][K + 1];

        for (int n = 1; n <= N; n++) {
            Arrays.fill(times[n], Long.MAX_VALUE);
        }

        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(1, 0, 0));
        times[1][0] = 0;

        while (!pq.isEmpty()) {
            State state = pq.remove();
            int city = state.city;
            long time = state.time;
            int pavement = state.pavement;

            if (time > times[city][pavement]) {
                continue;
            }

            for (Entry<Integer, Integer> entry : roads[city].entrySet()) {
                int nextCity = entry.getKey();
                int nextTime = entry.getValue();

                if (time + nextTime < times[nextCity][pavement]) {
                    pq.add(new State(nextCity, time + nextTime, pavement));
                    times[nextCity][pavement] = time + nextTime;
                }

                if (pavement < K && time < times[nextCity][pavement + 1]) {
                    pq.add(new State(nextCity, time, pavement + 1));
                    times[nextCity][pavement + 1] = time;
                }
            }
        }

        long answer = Long.MAX_VALUE;

        for (long time : times[N]) {
            answer = Math.min(answer, time);
        }

        bw.write(answer + "\n");
        bw.close();
    }

    private static class State implements Comparable<State> {
        int city;
        long time;
        int pavement;

        public State(int city, long time, int pavement) {
            this.city = city;
            this.time = time;
            this.pavement = pavement;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.time, other.time);
        }
    }
}