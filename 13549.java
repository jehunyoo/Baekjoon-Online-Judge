import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);

        final int maxRange = 2 * K + N;
        boolean[] visited = new boolean[maxRange + 1];
        PriorityQueue<Position> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Position(N, 0));

        while (priorityQueue.size() > 0) {
            Position p = priorityQueue.remove();

            if (p.pos == K) {
                bw.write(p.time + "\n");
                break;
            }

            if (visited[p.pos])
                continue;

            visited[p.pos] = true;

            if (0 <= p.pos * 2 && p.pos * 2 <= maxRange && !visited[p.pos * 2])
                priorityQueue.add(new Position(p.pos * 2, p.time));

            if (0 <= p.pos + 1 && p.pos + 1 <= maxRange && !visited[p.pos + 1])
                priorityQueue.add(new Position(p.pos + 1, p.time + 1));

            if (0 <= p.pos - 1 && p.pos - 1 <= maxRange && !visited[p.pos - 1])
                priorityQueue.add(new Position(p.pos - 1, p.time + 1));
        }

        bw.close();
    }

    private static class Position implements Comparable<Position> {
        int pos;
        int time;

        public Position(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Position other) {
            return this.time - other.time;
        }
    }
}