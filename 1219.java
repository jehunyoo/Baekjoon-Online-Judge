import java.util.Map.Entry;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] graph;
    private static Map<Edge, Integer> edges;
    private static int[] money;


    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int A = Integer.parseInt(in[1]);
        int B = Integer.parseInt(in[2]);
        int M = Integer.parseInt(in[3]);

        graph = new int[N][N];
        edges = new HashMap<>();

        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int start = Integer.parseInt(in[0]);
            int end = Integer.parseInt(in[1]);
            int cost = Integer.parseInt(in[2]);
            Edge edge = new Edge(start, end);

            graph[start][end] = cost;
            edges.merge(edge, cost, Math::min);
        }

        money = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bellmanFord(N, A, B);

        bw.close();
    }

    private static void bellmanFord(int N, int start, int end) throws IOException {
        long[] loss = new long[N];
        Arrays.fill(loss, Long.MAX_VALUE);
        loss[start] = -money[start];

        boolean hasCycle = false;
        List<Edge> cyclicEdges = new ArrayList<>();

        for (int n = 1; n <= N; n++) {
            for (Entry<Edge, Integer> entry : edges.entrySet()) {
                Edge edge = entry.getKey();
                int cost = entry.getValue();

                if (loss[edge.start] != Long.MAX_VALUE
                        && loss[edge.start] + (cost - money[edge.end]) < loss[edge.end]) {
                    loss[edge.end] = loss[edge.start] + (cost - money[edge.end]);

                    if (n == N) {
                        cyclicEdges.add(edge);
                        hasCycle = true;
                    }
                }
            }
        }

        // <-- BFS
        boolean isConnectedToCycle = false;

        if (hasCycle) {
            boolean[] visited = new boolean[N];

            for (Edge cyclicEdge : cyclicEdges) {

                Queue<Integer> queue = new ArrayDeque<>();
                queue.add(cyclicEdge.start);

                while (!queue.isEmpty()) {
                    int city = queue.remove();

                    if (city == end) {
                        isConnectedToCycle = true;
                        break;
                    }

                    if (visited[city]) {
                        continue;
                    }

                    visited[city] = true;
                    for (int next = 0; next < N; next++) {
                        if (graph[city][next] >= 0 && !visited[next]) {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        // --> BFS

        if (loss[end] == Long.MAX_VALUE) {
            bw.write("gg\n");
        } else if (hasCycle && isConnectedToCycle) {
            bw.write("Gee\n");
        } else {
            bw.write(-loss[end] + "\n");
        }
    }

    private static class Edge {
        final int start;
        final int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return start == edge.start && end == edge.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}