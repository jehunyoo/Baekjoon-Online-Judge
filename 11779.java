import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus>[] graph = new List[N + 1];

        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");

            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);

            if (graph[A] == null) {
                graph[A] = new ArrayList<>();
            }

            graph[A].add(new Bus(B, C));
        }

        String[] in = br.readLine().split(" ");
        int from = Integer.parseInt(in[0]);
        int to = Integer.parseInt(in[1]);

        int[] prevCity = new int[N + 1];
        prevCity[from] = from;

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[from] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.add(new int[]{0, from, -1});

        while (!pq.isEmpty()) {
            int[] item = pq.remove();
            int cost = item[0];
            int city = item[1];
            int prev = item[2];

            if (costs[city] < cost)
                continue;

            prevCity[city] = prev;

            if (graph[city] != null) {
                for (Bus bus : graph[city]) {
                    if (cost + bus.cost < costs[bus.to]) {
                        costs[bus.to] = cost + bus.cost;
                        pq.add(new int[]{cost + bus.cost, bus.to, city});
                    }
                }
            }
        }

        int count = 0;
        Deque<Integer> route = new ArrayDeque<>();

        int path = to;

        while (path != -1) {
            route.push(path);
            count++;
            path = prevCity[path];
        }

        bw.write(costs[to] + "\n");
        bw.write(count + "\n");

        while (!route.isEmpty()) {
            bw.write(route.pop() + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static class Bus {
        int to;
        int cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}