import java.util.stream.*;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] root;

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }

        in = br.readLine().split(" ");
        int C = Integer.parseInt(in[0]);
        List<Integer> whoKnowsTruth = new ArrayList<>();

        for (int i = 1; i <= C; i++) {
            whoKnowsTruth.add(Integer.parseInt(in[i]));
        }

        for (int who : whoKnowsTruth) {
            union(who, whoKnowsTruth.get(0)); // todo
        }

        int answer = M;
        List<List<Integer>> parties = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            List<Integer> party = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            parties.add(party);

            if (C == 0) {
                continue;
            }

            int P = party.get(0);

            for (int p = 2; p <= P; p++) {
                union(party.get(p), party.get(p - 1));
            }
        }

        if (C > 0) {
            int who = whoKnowsTruth.get(0);
            for (List<Integer> party : parties) {

                for (int i = 1; i < party.size(); i++) {
                    int member = party.get(i);

                    if (find(who) == find(member)) {
                        answer--;
                        break;
                    }
                }
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }

    private static int find(int x) {
        if (x != root[x]) {
            root[x] = find(root[x]);
        }

        return root[x];
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            root[y] = x;
        } else {
            root[x] = y;
        }
    }
}