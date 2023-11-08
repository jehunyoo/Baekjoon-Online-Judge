import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        int[][] graph = new int[N + 1][N + 1];
        for (int[] g : graph) {
            Arrays.fill(g, Integer.MAX_VALUE);
        }

        List<int[]> edges = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);

            if (graph[A][B] < C) {
                continue;
            }

            graph[A][B] = C;
            edges.add(new int[]{A, B, C});
        }



        int start = 1;
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[start] = 0;

        boolean hasCycle = false;

        for (int n = 1; n <= N; n++) {
            for (int[] edge : edges) {
                int src = edge[0];
                int dest = edge[1];
                int cost = edge[2];

                if (distance[src] != Long.MAX_VALUE && distance[src] + cost < distance[dest]) {
                    distance[dest] = distance[src] + cost;
                    if (n == N) {
                        hasCycle = true;
                    }
                }
            }
        }

        if (hasCycle) {
            bw.write("-1\n");
        } else {
            for (int node = 2; node <= N; node++) {
                long dist = distance[node] != Long.MAX_VALUE ? distance[node] : -1;
                bw.write(dist + "\n");
            }
        }

        bw.close();
    }
}