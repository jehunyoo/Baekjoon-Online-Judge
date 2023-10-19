import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);

        final int MAX_RANGE = 2 * K + N;
        int[] visitedTime = new int[MAX_RANGE + 1];
        Arrays.fill(visitedTime, -1);

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(N, 0));

        int time = Math.abs(N - K);
        int count = 0;

        while (queue.size() > 0) {
            Position p = queue.remove();

            if (p.time > time)
                continue;

            if (p.pos == K) {
                time = p.time;
                count++;
            }

            if (visitedTime[p.pos] >= 0 && p.time > visitedTime[p.pos])
                continue;

            visitedTime[p.pos] = p.time;

            if (0 <= p.pos * 2 && p.pos * 2 <= MAX_RANGE)
                queue.add(new Position(p.pos * 2, p.time + 1));

            if (0 <= p.pos + 1 && p.pos + 1 <= MAX_RANGE)
                queue.add(new Position(p.pos + 1, p.time + 1));

            if (0 <= p.pos - 1 && p.pos - 1 <= MAX_RANGE)
                queue.add(new Position(p.pos - 1, p.time + 1));
        }

        bw.write(time + "\n");
        bw.write(count + "\n");
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