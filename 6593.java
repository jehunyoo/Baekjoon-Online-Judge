import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while (true) {
            String[] in = br.readLine().split(" ");
            int L = Integer.parseInt(in[0]);
            int R = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            Position start = null;
            Position end = null;

            boolean[][][] building = new boolean[L][R][C];

            for (int level = 0; level < L; level++) {
                for (int row = 0; row < R; row++) {
                    in = br.readLine().split("");
                    for (int col = 0; col < C; col++) {
                        building[level][row][col] = !in[col].equals("#");

                        if (in[col].equals("S")) {
                            start = new Position(level, row, col, 0);
                        } else if (in[col].equals("E")) {
                            end = new Position(level, row, col, 0);
                        }
                    }
                }

                br.readLine();
            }

            Queue<Position> queue = new ArrayDeque<>();
            queue.add(start);

            Position result = null;

            while (!queue.isEmpty()) {
                Position position = queue.remove();

                int level = position.level;
                int row = position.row;
                int col = position.col;
                int time = position.time;

                if (position.equals(end)) {
                    result = position;
                    break;
                }

                for (int d = 0; d < dx.length; d++) {
                    int nx = row + dx[d];
                    int ny = col + dy[d];
                    int nz = level + dz[d];

                    if (0 <= nx && nx < R && 0 <= ny && ny < C && 0 <= nz && nz < L && building[nz][nx][ny]) {
                        building[nz][nx][ny] = false;
                        queue.add(new Position(nz, nx, ny, time + 1));
                    }
                }
            }

            if (result == null) {
                bw.write("Trapped!\n");
            } else {
                bw.write("Escaped in " + result.time + " minute(s).\n");
            }

        }

        bw.close();
    }

    private static class Position {
        int level;
        int row;
        int col;
        int time;

        public Position(int level, int row, int col, int time) {
            this.level = level;
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Position position = (Position) o;
            return level == position.level && row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, row, col);
        }
    }
}