import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);

        List<String> josephus = new ArrayList<>(N);
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++)
            queue.add(i);

        for (int i = 0; !queue.isEmpty(); i = (i + 1) % K) {
            Integer num = queue.remove();

            if (i == K - 1) {
                josephus.add(num.toString());
                continue;
            }

            queue.add(num);
        }

        bw.write("<" + String.join(", ", josephus) + ">\n");
        bw.close();
    }
}