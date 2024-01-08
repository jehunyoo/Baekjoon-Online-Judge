import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String parentheses = br.readLine();

        bw.write(valueOfParentheses(parentheses) + "\n");
        bw.close();
    }

    private static int valueOfParentheses(String parentheses) {
        if (parentheses.length() % 2 == 1) {
            return 0;
        }

        int value = 0;

        Deque<Pair> stack = new ArrayDeque<>();

        for (int index = 0; index < parentheses.length(); index++) {
            char ch = parentheses.charAt(index);

            if (ch == '(' || ch == '[') {
                stack.push(new Pair(ch, index));
            } else {
                if (!stack.isEmpty() && stack.peek().matches(ch)) {
                    if (stack.size() == 1) {

                        Pair pair = stack.peek();
                        int nextValue = index - pair.index > 1 ? valueOfParentheses(parentheses.substring(pair.index + 1, index)) : 1;

                        if (nextValue > 0) {
                            value += pair.value() * nextValue;
                        } else {
                            return 0;
                        }
                    }
                    stack.pop();
                } else {
                    return 0;
                }
            }
        }

        if (!stack.isEmpty()) {
            return 0;
        }

        return value;
    }

    private static class Pair {
        char ch;
        int index;

        public Pair(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }

        public boolean matches(char ch) {
            return (this.ch == '(' && ch == ')') || (this.ch == '[' && ch == ']');
        }

        public int value() {
            return ch == '(' ? 2 : 3;
        }
    }
}