import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int[][] canvas = new int[N][M];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                canvas[i][j] = Integer.parseInt(in[j]);
            }
        }

        boolean[][] visited = new boolean[N][M];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int count = 0;
        int maxSize = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && canvas[i][j] == 1) {
                    count++;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    int size = 0;

                    while (queue.size() > 0) {
                        int[] where = queue.remove();
                        int x = where[0], y = where[1];

                        if (!visited[x][y]) {
                            visited[x][y] = true;
                            size++;

                            for (int d = 0; d < 4; d++) {
                                int nx = x + dx[d];
                                int ny = y + dy[d];

                                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && canvas[nx][ny] == 1) {
                                    queue.add(new int[]{nx, ny});
                                }
                            }
                        }
                    }

                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        bw.write(count + "\n");
        bw.write(maxSize + "\n");
        bw.close();
    }
}