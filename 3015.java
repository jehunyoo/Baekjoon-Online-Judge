import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        long sum = 0L;
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> sizeOfStack = new HashMap<>();

        for (int n = 0; n < N; n++) {
            int height = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && stack.peek() < height) {
                int top = stack.pop();
                sum += stack.size() + 1 - Math.max(sizeOfStack.get(top) - 1, 0);

                if (stack.size() == sizeOfStack.get(top)) {
                    sizeOfStack.remove(top);
                }
            }

            sizeOfStack.merge(height, stack.size(), Math::min);
            stack.push(height);
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            sum += stack.size() - Math.max(sizeOfStack.get(top) - 1, 0);
        }

        bw.write(sum + "\n");
        bw.close();
    }
}