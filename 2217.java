import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Integer> weights = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int W = Integer.parseInt(br.readLine());
            weights.add(W);
        }

        Collections.sort(weights);

        int answer = 0;

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, (N - i) * weights.get(i));
        }

        bw.write(answer + "\n");
        bw.close();
    }
}