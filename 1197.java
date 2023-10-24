import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int V = Integer.parseInt(in[0]), E = Integer.parseInt(in[1]);

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]), B = Integer.parseInt(in[1]), C = Integer.parseInt(in[2]);
            edges[i] = new Edge(C, A, B);
        }

        int path = 0;
        int[] root = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            root[i] = i;
        }

        Arrays.sort(edges);
        for (Edge edge : edges) {
            if (find(edge.x, root) != find(edge.y, root)) {
                union(edge.x, edge.y, root);
                path += edge.weight;
            }
        }

        bw.write(path + "\n");
        bw.close();
    }

    private static int find(int node, int[] root) {
        if (root[node] != node) {
            root[node] = find(root[node], root);
        }

        return root[node];
    }


    private static void union(int node1, int node2, int[] root) {
        node1 = find(node1, root);
        node2 = find(node2, root);

        if (node1 < node2) {
            root[node2] = node1;
        } else {
            root[node1] = node2;
        }
    }

    private static class Edge implements Comparable<Edge> {

        int weight;
        int x;
        int y;

        public Edge(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
}