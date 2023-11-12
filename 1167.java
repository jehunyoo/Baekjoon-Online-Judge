import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int length;
    private static List<Pair>[] edges;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        int V = Integer.parseInt(br.readLine());
        edges = new List[V + 1];


        for (int i = 0; i < V; i++) {
            String[] in = br.readLine().split(" ");
            int v = Integer.parseInt(in[0]);

            edges[v] = new ArrayList<>();
            for (int j = 1; j < in.length - 1; j += 2) {
                edges[v].add(new Pair(Integer.parseInt(in[j]), Integer.parseInt(in[j + 1])));
            }
        }


        length = 0;
        visited = new boolean[V + 1];

        treeLength(1);

        bw.write(length + "\n");
        bw.close();
    }

    private static int treeLength(int root) {
        if (visited[root])
            return 0;

        visited[root] = true;
        int firstLongest = 0;
        int secondLongest = 0;

        for (Pair pair : edges[root]) {
            if (!visited[pair.next]) {
                int l = treeLength(pair.next) + pair.cost;
                length = Math.max(length, l);

                if (l > firstLongest) {
                    int temp = firstLongest;
                    firstLongest = l;
                    secondLongest = temp;
                } else if (l > secondLongest) {
                    secondLongest = l;
                }
            }
        }

        length = Math.max(length, firstLongest + secondLongest);

        return firstLongest;
    }

    private static class Pair {
        int next;
        int cost;

        public Pair(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
}