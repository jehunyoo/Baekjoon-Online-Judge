import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int K = Integer.parseInt(in[2]);

        Space[][] space = new Space[N][M];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                space[i][j] = in[j].equals("0") ? Space.EMPTY : Space.WALL;
            }
        }

        int distance = -1;
        boolean[][][] visited = new boolean[K + 1][N][M];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<Position> queue = new ArrayDeque();
        queue.add(new Position(0, 0, 0, 1, true));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Position position = queue.remove();

            if (position.x == N - 1 && position.y == M - 1) {
                distance = position.distance;
                break;
            }

            boolean anyWall = false;

            for (int d = 0; d < 4; d++) {
                int nx = position.x + dx[d];
                int ny = position.y + dy[d];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (space[nx][ny] == Space.WALL) {
                        anyWall = true;
                    }

                    if (space[nx][ny] == Space.EMPTY && !visited[position.k][nx][ny]) {
                        queue.add(new Position(nx, ny, position.k, position.distance + 1, !position.day));
                        visited[position.k][nx][ny] = true;
                    } else if (position.day && space[nx][ny] == Space.WALL && position.k < K && !visited[position.k + 1][nx][ny]) {
                        queue.add(new Position(nx, ny, position.k + 1, position.distance + 1, !position.day));
                        visited[position.k + 1][nx][ny] = true;
                    }
                }
            }

            if (!position.day && anyWall) {
                queue.add(new Position(position.x, position.y, position.k, position.distance + 1, !position.day));
            }
        }

        bw.write(distance + "\n");
        bw.close();
    }

    private static class Position {
        int x;
        int y;
        int k;
        int distance;
        boolean day;

        public Position(int x, int y, int k, int distance, boolean day) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.distance = distance;
            this.day = day;
        }
    }

    private enum Space {
        EMPTY, WALL
    }
}