import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] T;
    private static boolean[] visited;
    private static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        T = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                T[i][j] = Integer.parseInt(in[j]);
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
                    }
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;

        backtrack(K, 0, 1);

        bw.write(minTime + "\n");
        bw.close();
    }

    private static void backtrack(int now, int time, int count) {
        if (minTime <= time) {
            return;
        }

        if (count == T.length) {
            minTime = time;
        }

        for (int next = 0; next < T.length; next++) {
            if (next != now && !visited[next]) {
                visited[next] = true;
                backtrack(next, time + T[now][next], count + 1);
                visited[next] = false;
            }
        }
    }
}