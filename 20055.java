import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);
        String[] A = br.readLine().split(" ");
        int[] durability = new int[2 * N];

        for (int i = 0; i < 2 * N; i++)
            durability[i] = Integer.parseInt(A[i]);

        int stage = 0;
        int broken = 0;
        int upPosition = 0; // initial upPosition
        Queue<Integer> queue = new ArrayDeque<>();

        while (broken < K) {
            // start
            stage++;

            // step 1
            upPosition = moveIndex(upPosition, -1, N);
            int downPosition = moveIndex(upPosition, N - 1, N);

            // step 2
            for (int queueSize = queue.size(); queueSize > 0; queueSize--) {
                int index = queue.remove();

                if (index == downPosition)
                    continue;

                // step 2 - 1
                int next = moveIndex(index, 1, N);
                if (durability[next] > 0 && !queue.contains(next)) {
                    if (next == downPosition) {
                        durability[next]--;
                        if (durability[next] == 0)
                            broken++;

                        continue;
                    }

                    queue.add(next);

                    durability[next]--;
                    if (durability[next] == 0)
                        broken++;
                }
                else
                    queue.add(index);
            }


            // step 3
            if (durability[upPosition] > 0) {
                queue.add(upPosition);

                durability[upPosition]--;
                if (durability[upPosition] == 0)
                    broken++;
            }
        }

        bw.write(stage + "\n");
        bw.close();
    }

    private static int moveIndex(int index, int delta, int N) {
        return (index + 2 * N + delta) % (2 * N);
    }
}