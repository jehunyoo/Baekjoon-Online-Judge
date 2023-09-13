import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();

        bw.write(calculateLength(S) + "\n");
        bw.close();
    }

    private static int calculateLength(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int left = -1, right = -1;
        int length = 0;
        int invalid = 0;

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                if (stack.size() == 0) {
                    left = i;
                }
                stack.push(ch);
            } else if (ch == ')') {
                stack.pop();
                if (stack.size() == 0) {
                    right = i;
                    length += (s.charAt(left - 1) - '0') * calculateLength(s.substring(left + 1, right));
                    invalid += right - left + 2;
                }
            }
        }

        length += s.length() - invalid;

        return length;
    }
}