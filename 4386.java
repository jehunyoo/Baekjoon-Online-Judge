import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Star> stars = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            double x = Double.parseDouble(in[0]);
            double y = Double.parseDouble(in[1]);

            stars.add(new Star(n, x, y));
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int n = 0; n < N; n++) {
            Star starA = stars.get(n);
            for (int m = n + 1; m < N; m++) {
                Star starB = stars.get(m);
                double distance = Math.sqrt(Math.pow(starA.x - starB.x, 2) + Math.pow(starA.y - starB.y, 2));
                edges.add(new Edge(starA.id, starB.id, distance));
            }
        }

        int[] root = new int[N];
        for (int n = 0; n < N; n++) {
            root[n] = n;
        }

        int count = 0;
        double answer = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.remove();

            int src = edge.src;
            int dest = edge.dest;

            if (find(root, src) != find(root, dest)) {
                union(root, src, dest);
                answer += edge.distance;
                count++;

                if (count == N - 1) {
                    break;
                }
            }
        }

        bw.write(String.format("%.2f", answer) + "\n");
        bw.close();
    }

    private static int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }

        return root[x];
    }

    private static void union(int[] root, int x, int y) {
        x = find(root, x);
        y = find(root, y);

        if (x < y) {
            root[y] = x;
        } else {
            root[x] = y;
        }
    }

    private static class Star {
        int id;
        double x;
        double y;

        public Star(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge>{
        int src;
        int dest;
        double distance;

        public Edge(int src, int dest, double distance) {
            this.src = src;
            this.dest = dest;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge other) {
            return this.distance >= other.distance ? 1 : -1;
        }
    }
}