import java.util.function.*;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int X = Integer.parseInt(in[2]);

        int[][] graph = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int T = Integer.parseInt(in[2]);
            graph[A][B] = T;
        }

        int[] outTimes = new int[N + 1];
        dijkstra(outTimes, X, (city, nextCity) -> graph[city][nextCity]);

        int[] inTimes = new int[N + 1];
        dijkstra(inTimes, X, (city, nextCity) -> graph[nextCity][city]);

        int time = 0;

        for (int city = 1; city <= N; city++) {
            time = Math.max(time, inTimes[city] + outTimes[city]);
        }

        bw.write(time + "\n");
        bw.close();
    }

    private static void dijkstra(int[] times, int X, IntBinaryOperator intBinaryOperator) {
        int N = times.length - 1;

        Arrays.fill(times, Integer.MAX_VALUE);
        times[X] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.add(new int[]{0, X});

        while (!pq.isEmpty()) {
            int[] item = pq.remove();
            int time = item[0];
            int city = item[1];

            if (times[city] < time)
                continue;

            for (int nextCity = 1; nextCity <= N; nextCity++) {
                int nextTime = intBinaryOperator.applyAsInt(city, nextCity);
                if (nextTime > 0 && time + nextTime < times[nextCity]) {
                    times[nextCity] = time + nextTime;
                    pq.add(new int[]{time + nextTime, nextCity});
                }
            }
        }
    }
}