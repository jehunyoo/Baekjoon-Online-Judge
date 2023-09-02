import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            String[] in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);

            Queue<Register> queue = new ArrayDeque<>();
            queue.add(new Register(A, ""));
            Set<Integer> visited = new HashSet<>();
            visited.add(A);

            while (queue.size() > 0) {
                Register register = queue.remove();
                int value = register.value;
                String s = register.s;

                if (value == B) {
                    bw.write(s + "\n");
                    break;
                }

                int D = Register.operateD(value);
                if(!visited.contains(D)) {
                    queue.add(new Register(D, s + "D"));
                    visited.add(D);
                }
                int S = Register.operateS(value);
                if(!visited.contains(S)) {
                    queue.add(new Register(S, s + "S"));
                    visited.add(S);
                }
                int L = Register.operateL(value);
                if(!visited.contains(L)) {
                    queue.add(new Register(L, s + "L"));
                    visited.add(L);
                }
                int R = Register.operateR(value);
                if(!visited.contains(R)) {
                    queue.add(new Register(R, s + "R"));
                    visited.add(R);
                }
            }
        }

        bw.flush();
        bw.close();
    }
    static class Register {
        int value;
        String s;

        public Register(int value, String s) {
            this.value = value;
            this.s = s;
        }

        public static int operateD(int value) {
            return (value * 2) % 10000;
        }

        public static int operateS(int value) {
            return value == 0? 9999: value - 1;
        }

        public static int operateL(int value) {
            return (value % 1000) * 10 + value / 1000;
        }

        public static int operateR(int value) {
            return (value % 10) * 1000 + value / 10;
        }
    }
}