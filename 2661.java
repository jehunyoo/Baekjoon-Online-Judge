import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final List<Integer> sequence = new ArrayList<>();
    private static List<Integer> minGoodSequence;
    private static boolean found = false;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        findMinGoodSequence(N);

        for (int element : minGoodSequence) {
            bw.write(element + "");
        }
        bw.newLine();
        bw.close();
    }

    private static void findMinGoodSequence(int N) {
        if (N == 0) {
            found = true;
            minGoodSequence = new ArrayList<>(sequence);
            return;
        }

        for (int num = 1; num <= 3; num++) {
            if (found) {
                break;
            }

            sequence.add(num);
            if (!isBadSequence()) {
                findMinGoodSequence(N - 1);
            }
            sequence.remove(sequence.size() - 1);
        }
    }

    private static boolean isBadSequence() {
        int index = sequence.size() - 1;

        for (int i = 1; i <= sequence.size() / 2; i++) {
            boolean isBad = true;

            for (int j = 0; j < i; j++) {
                if (!sequence.get(index - j).equals(sequence.get(index - j - i))) {
                    isBad = false;
                }
            }

            if (isBad) {
                return true;
            }
        }

        return false;
    }
}