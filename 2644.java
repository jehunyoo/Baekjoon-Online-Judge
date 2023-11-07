import java.util.*;
import java.io.*;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int A = Integer.parseInt(in[0]);
        int B = Integer.parseInt(in[1]);
        int M = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]);
            int Y = Integer.parseInt(in[1]);

            graph[X][Y] = graph[Y][X] = true;
        }

        boolean[] visited = new boolean[N + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{A, 0});

        int answer = -1;

        while (!queue.isEmpty()) {
            int[] item = queue.remove();
            int node = item[0];
            int count = item[1];

            if (node == B) {
                answer = count;
                break;
            }

            if (!visited[node]) {
                visited[node] = true;

                for (int next = 1; next <= N; next++) {
                    if (graph[node][next]) {
                        queue.add(new int[]{next, count + 1});
                    }
                }
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}