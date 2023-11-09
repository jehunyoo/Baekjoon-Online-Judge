import java.util.Map.Entry;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<Edge, Integer> edges;

    public static void main(String[] args) throws IOException {

        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            String[] in = br.readLine().split(" ");
            int N = Integer.parseInt(in[0]);
            int M = Integer.parseInt(in[1]);
            int W = Integer.parseInt(in[2]);

            edges = new HashMap();

            for (int m = 0; m < M; m++) {
                in = br.readLine().split(" ");
                int S = Integer.parseInt(in[0]);
                int E = Integer.parseInt(in[1]);
                int T = Integer.parseInt(in[2]);

                Edge edge1 = new Edge(S, E);
                Edge edge2 = new Edge(E, S);

                edges.merge(edge1, T, Math::min);
                edges.merge(edge2, T, Math::min);
            }

            for (int w = 0; w < W; w++) {
                in = br.readLine().split(" ");
                int S = Integer.parseInt(in[0]);
                int E = Integer.parseInt(in[1]);
                int T = Integer.parseInt(in[2]);

                Edge edge = new Edge(S, E);
                edges.merge(edge, -T, Math::min);
            }

            String answer = "NO";

            long[] distance = new long[N + 1];

            int start = 0;

            for (int end = 1; end <= N; end++) {
                Edge edge = new Edge(start, end);
                edges.put(edge, 0);
            }

            boolean hasCycle = bellmanFord(distance, start);
            if (hasCycle) {
                answer = "YES";
            }

            bw.write(answer + "\n");
        }

        bw.close();
    }

    private static boolean bellmanFord(long[] distance, int start) {
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[start] = 0;

        for (int n = 1; n < distance.length; n++) {
            for (Entry<Edge, Integer> entry : edges.entrySet()) {
                Edge edge = entry.getKey();
                int time = entry.getValue();

                if (distance[edge.start] != Long.MAX_VALUE && distance[edge.start] + time < distance[edge.end]) {
                    distance[edge.end] = distance[edge.start] + time;
                    if (n == distance.length - 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Edge {
        int start;
        int end;

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