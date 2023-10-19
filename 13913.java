import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);

        final int MAX_RANGE = 2 * K + N;
        boolean[] visited = new boolean[MAX_RANGE + 1];

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(N, 0, null));

        int time = Math.abs(N - K);
        Position position = null;

        while (queue.size() > 0) {
            Position p = queue.remove();

            if (p.pos == K) {
                time = p.time;
                position = p;
                break;
            }

            if (visited[p.pos])
                continue;

            visited[p.pos] = true;

            if (0 <= p.pos * 2 && p.pos * 2 <= MAX_RANGE && !visited[p.pos * 2])
                queue.add(new Position(p.pos * 2, p.time + 1, p));

            if (0 <= p.pos + 1 && p.pos + 1 <= MAX_RANGE && !visited[p.pos + 1])
                queue.add(new Position(p.pos + 1, p.time + 1, p));

            if (0 <= p.pos - 1 && p.pos - 1 <= MAX_RANGE && !visited[p.pos - 1])
                queue.add(new Position(p.pos - 1, p.time + 1, p));
        }

        bw.write(time + "\n");
        Deque<Integer> path = new ArrayDeque<>();
        while (position != null) {
            path.push(position.pos);
            position = position.prev;
        }
        while (!path.isEmpty()) {
            bw.write(path.pop() + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static class Position {
        int pos;
        int time;
        Position prev;

        public Position(int pos, int time, Position prev) {
            this.pos = pos;
            this.time = time;
            this.prev = prev;
        }
    }
}