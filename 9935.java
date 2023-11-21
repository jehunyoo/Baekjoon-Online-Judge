import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        char[] chars = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        char tail = bomb[bomb.length - 1];

        for (char ch : chars) {
            stack.push(ch);

            if (ch == tail) {
                Deque<Character> tempStack = new ArrayDeque<>();
                for (int i = bomb.length - 1; i >= 0 && !stack.isEmpty() && stack.peek() == bomb[i]; i--) {
                    tempStack.push(stack.pop());
                }

                if (tempStack.size() != bomb.length) {
                    while (!tempStack.isEmpty()) {
                        stack.push(tempStack.pop());
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            bw.write("FRULA\n");
            bw.close();
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        bw.write(sb + "\n");
        bw.close();
    }
}