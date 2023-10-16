import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Map<Integer, Map<Integer, Integer>> busMap = new HashMap<>();

        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            int from = Integer.parseInt(in[0]), to = Integer.parseInt(in[1]), cost = Integer.parseInt(in[2]);

            if (from == to)
                continue;

            Map<Integer, Integer> costMap;
            if (!busMap.containsKey(from))
                costMap = new HashMap<>();
            else
                costMap = busMap.get(from);

            if (!costMap.containsKey(to))
                costMap.put(to, cost);
            else
                costMap.put(to, Math.min(costMap.get(to), cost));

            busMap.put(from, costMap);
        }

        String[] in = br.readLine().split(" ");
        int start = Integer.parseInt(in[0]), end = Integer.parseInt(in[1]);

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(((o1, o2) -> o1[0] - o2[0]));
        heap.add(new int[]{0, start});

        while (heap.size() > 0) {
            int[] item = heap.remove();
            int cost = item[0], city = item[1];

            if (costs[city] < cost)
                continue;

            if (busMap.containsKey(city)) {
                for (Map.Entry<Integer, Integer> entry : busMap.get(city).entrySet()) {
                    int neighbor = entry.getKey(), c = entry.getValue();
                    if (c != Integer.MAX_VALUE && cost + c < costs[neighbor]) {
                        costs[neighbor] = cost + c;
                        heap.add(new int[]{cost + c, neighbor});
                    }
                }
            }

        }

        bw.write(costs[end] + "\n");
        bw.close();
    }
}