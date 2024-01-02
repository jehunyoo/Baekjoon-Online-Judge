import java.util.stream.*;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            List<Long> chapters = Arrays.stream(br.readLine().split(" ")).map(Long::parseLong).collect(Collectors.toList());

            long cost = 0L;
            Queue<Long> pq = new PriorityQueue<>(chapters);

            while (pq.size() >= 2) {
                long chapter = pq.remove();
                long nextChapter = pq.remove();

                long currentCost = chapter + nextChapter;
                cost += currentCost;

                pq.add(currentCost);
            }

            bw.write(cost + "\n");
        }

        bw.close();
    }
}