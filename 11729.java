import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final List<Action> actions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        hanoi(N, 1, 3, 2);

        bw.write(actions.size() + "\n");

        for (Action action : actions) {
            bw.write(action + "\n");
        }

        bw.close();
    }

    private static void hanoi(int n, int from, int to, int temp) {
        if (n == 1) {
            actions.add(new Action(from, to));
            return;
        }

        hanoi(n - 1, from, temp, to);
        actions.add(new Action(from, to));
        hanoi(n - 1, temp, to, from);
    }

    private static class Action {
        int from;
        int to;

        public Action(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return from + " " + to;
        }
    }
}
