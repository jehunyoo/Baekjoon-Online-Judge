import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int L = Integer.parseInt(in[1]);
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] D = new int[N];

        Deque<Element> deque = new ArrayDeque<>();

        for (int index = 0; index < N; index++) {
            Element element = new Element(index, A[index]);

            if (deque.isEmpty()) {
                deque.addLast(element);
            } else {
                while (!deque.isEmpty() && deque.getLast().compareTo(element) > 0) {
                    deque.removeLast();
                }

                deque.addLast(element);
            }

            if (deque.getFirst().index < index - L + 1) {
                deque.removeFirst();
            }

            D[index] = deque.getFirst().value;
        }


        for (int d : D) {
            bw.write(d + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static class Element implements Comparable<Element> {
        int index;
        int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Element other) {
            return this.value - other.value;
        }
    }
}