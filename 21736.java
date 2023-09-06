import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        char[][] graph = new char[N][M];

        int[] start = {-1, -1};

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'I') {
                    start = new int[]{i, j};
                }
            }
        }

        int count = 0;
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);

        while (queue.size() > 0) {
            int[] pos = queue.remove();
            int x = pos[0], y = pos[1];

            if (graph[x][y] != 'X') {
                if (graph[x][y] == 'P') {
                    count++;
                }
                graph[x][y] = 'X';
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && graph[nx][ny] != 'X') {
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if (count > 0)
            bw.write(count + "\n");
        else
            bw.write("TT\n");

        bw.close();
    }
}