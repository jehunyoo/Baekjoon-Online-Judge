import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]),K = Integer.parseInt(in[1]);

        final int maxRange = 500000 + 1;
        boolean[][] visited = new boolean[2][maxRange];

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(N, 0));

        int time = -1;

        while (!queue.isEmpty()) {
            Position p = queue.remove();
            visited[p.time % 2][p.pos] = true;

            int target = K + (p.time * (p.time + 1)) / 2;

            if (target >= maxRange)
                break;

            if (visited[p.time % 2][target]) {
                time = p.time;
                break;
            }

            if (0 <= p.pos * 2 && p.pos * 2 < maxRange && !visited[(p.time + 1) % 2][p.pos * 2])
                queue.add(new Position(p.pos * 2, p.time + 1));

            if (0 <= p.pos + 1 && p.pos + 1 < maxRange && !visited[(p.time + 1) % 2][p.pos + 1])
                queue.add(new Position(p.pos + 1, p.time + 1));

            if (0 <= p.pos - 1 && p.pos - 1 < maxRange && !visited[(p.time + 1) % 2][p.pos - 1])
                queue.add(new Position(p.pos - 1, p.time + 1));

        }

        bw.write(time + "\n");
        bw.close();
    }

    private static class Position {
        int pos;
        int time;

        public Position(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}