import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new LinkedList[N + 1];
        int[] parents = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int A = in[0], B = in[1];

            if (graph[A] == null)
                graph[A] = new LinkedList<>();
            if (graph[B] == null)
                graph[B] = new LinkedList<>();

            graph[A].add(B);
            graph[B].add(A);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int parent = queue.remove();
            for (int child : graph[parent]) {
                if (child != parents[parent]) {
                    parents[child] = parent;
                    queue.add(child);
                }
            }
        }

        for (int node = 2; node <= N; node++)
            bw.write(parents[node] + "\n");

        bw.close();
    }
}