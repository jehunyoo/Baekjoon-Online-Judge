import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] nges = new int[N];
        Arrays.fill(nges, -1);

        Deque<Element> stack = new ArrayDeque<>();

        for (int index = 0; index < N; index++) {
            int value = A[index];
            Element element = new Element(index, value);

            while (!stack.isEmpty() && stack.peek().value < value) {
                Element topElement = stack.pop();
                nges[topElement.index] = value;
            }

            stack.push(element);
        }

        for (int nge : nges) {
            bw.write(nge + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static class Element {
        int index;
        int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}