import java.util.*;
import java.io.*;

public class Main {
    // BFS
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int A, B;
        boolean[][] graph = new boolean[N + 1][N + 1];

        for(int m=0; m<M; m++) {
            in = br.readLine().split(" ");
            A = Integer.parseInt(in[0]);
            B = Integer.parseInt(in[1]);
            graph[A][B] = true;
            graph[B][A] = true;
        }

        int min_sum = 5000 * 5000;
        int answer = -1;

        for(int i=1; i<=N; i++) {
            boolean[] visited = new boolean[N + 1];
            Queue<Integer[]> queue = new LinkedList<>();
            queue.add(new Integer[]{i, 0});
            int user, dist;
            int sum = 0;

            while(!queue.isEmpty()) {
                Integer[] arr = queue.remove();
                user = arr[0];
                dist = arr[1];

                if(!visited[user]) {
                    visited[user] = true;
                    sum += dist;
                    for(int j=1; j<=N; j++) {
                        if(graph[user][j] && !visited[j]) {
                            queue.add(new Integer[]{j, dist + 1});
                        }
                    }
                }
            }
            if(sum < min_sum) {
                answer = i;
                min_sum = sum;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    // floyd-warshall
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int A, B;
        int[][] distance = new int[N + 1][N + 1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i != j)
                    distance[i][j] = (int)1e9;
            }
        }

        for(int m=0; m<M; m++) {
            in = br.readLine().split(" ");
            A = Integer.parseInt(in[0]);
            B = Integer.parseInt(in[1]);
            distance[A][B] = 1;
            distance[B][A] = 1;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    distance[i][j] = distance[i][j] < distance[i][k] + distance[k][j]? distance[i][j]: distance[i][k] + distance[k][j];
                }
            }
        }

        int answer = -1;
        int min_sum = (int)1e9;
        for(int user=1; user<=N; user++) {
            int sum = 0;
            for(int other=1; other<=N; other++) {
                sum += distance[user][other];
            }
            if(sum < min_sum) {
                min_sum = sum;
                answer = user;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}