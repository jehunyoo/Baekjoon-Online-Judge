import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int t = 0; t < T; t++) {
            int L = Integer.parseInt(br.readLine());
            String[] in = br.readLine().split(" ");
            int i = Integer.parseInt(in[0]), j = Integer.parseInt(in[1]);
            in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]), Y = Integer.parseInt(in[1]);

            boolean[][] visited = new boolean[L][L];

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{i, j, 0});

            while (!queue.isEmpty()) {
                int[] point = queue.remove();
                int x = point[0], y = point[1];
                int count = point[2];

                if (x == X && y == Y) {
                    bw.write(count + "\n");
                    break;
                }

                if (!visited[x][y]) {
                    visited[x][y] = true;

                    for (int d = 0; d < dx.length; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (0 <= nx && nx < L && 0 <= ny && ny < L && !visited[nx][ny]) {
                            queue.add(new int[]{nx, ny, count + 1});
                        }
                    }
                }
            }
        }

        bw.close();
    }
}