import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);

        combine(1, N, M, new ArrayList<>());

        bw.close();
    }

    public static void combine(int next, int N, int M, List<Integer> combination) throws IOException {
        if (M == 0) {
            printSequence(combination);
            return;
        }

        for (int item = next; item <= N; item++) {
            combination.add(item);
            combine(item + 1, N, M - 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    public static void printSequence(List<Integer> sequence) throws IOException {
        for (int num : sequence) {
            bw.write(num + " ");
        }
        bw.write("\n");
    }
}