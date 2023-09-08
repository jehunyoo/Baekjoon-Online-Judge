import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);

        int L = 100;
        int[] board = new int[L + 1];
        int[] ladders = new int[L + 1];
        int[] snakes = new int[L + 1];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            int x = Integer.parseInt(in[0]), y = Integer.parseInt(in[1]);
            ladders[x] = y;
        }

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int u = Integer.parseInt(in[0]), v = Integer.parseInt(in[1]);
            snakes[u] = v;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});

        while (queue.size() > 0) {
            int[] e = queue.remove();
            int pos = e[0], count = e[1];

            if (pos == L)
                break;

            if (board[pos] < count)
                continue;

            for (int dice = 1; dice <= 6 && pos + dice <= L; dice++) {
                int next = pos + dice;
                if (board[next] == 0 || count + 1 < board[next]) {
                    if (ladders[next] > 0)
                        next = ladders[next];
                    else if (snakes[next] > 0)
                        next = snakes[next];

                    queue.add(new int[]{next, count + 1});
                    board[next] = board[pos + dice] = count + 1;
                }
            }

        }

        bw.write(board[L] + "\n");
        bw.close();
    }
}