import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int F = Integer.parseInt(in[0]);
        int S = Integer.parseInt(in[1]);
        int G = Integer.parseInt(in[2]);
        int U = Integer.parseInt(in[3]);
        int D = Integer.parseInt(in[4]);

        boolean[] visited = new boolean[F + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{S, 0});

        int answer = -1;

        while (!queue.isEmpty()) {
            int[] item = queue.remove();
            int floor = item[0];
            int count = item[1];

            if (floor == G) {
                answer = count;
                break;
            }

            if (!visited[floor]) {
                visited[floor] = true;

                if (floor + U <= F) {
                    queue.add(new int[]{floor + U, count + 1});
                }

                if (floor - D > 0) {
                    queue.add(new int[]{floor - D, count + 1});
                }
            }
        }

        if (answer == -1)
            bw.write("use the stairs");
        else
            bw.write(answer + "\n");
        bw.close();
    }
}