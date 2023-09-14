import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(in[j]);
            }
        }

        int count = 0;
        int[] dx = {1, 1, 0};
        int[] dy = {0, 1, 1};
        Deque<Pipe> stack = new ArrayDeque<>();
        stack.push(new Pipe(new int[]{0, 1}, Status.H));

        while (stack.size() > 0) {
            Pipe pipe = stack.pop();
            int x = pipe.head[0], y = pipe.head[1];

            if (x == N - 1 && y == N - 1) {
                count++;
                continue;
            }

            boolean[] next = new boolean[3]; // vertical, diagonal, horizontal
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && house[nx][ny] == 0) {
                    next[i] = true;
                }
            }

            boolean vertical = next[0];
            boolean diagonal = next[1];
            boolean horizontal = next[2];

            if (pipe.status == Status.H) {
                if (horizontal)
                    stack.push(new Pipe(new int[]{x, y + 1}, Status.H));
                if (vertical && diagonal && horizontal)
                    stack.push(new Pipe(new int[]{x + 1, y + 1}, Status.D));
            } else if (pipe.status == Status.V) {
                if (vertical)
                    stack.push(new Pipe(new int[]{x + 1, y}, Status.V));
                if (vertical && diagonal && horizontal)
                    stack.push(new Pipe(new int[]{x + 1, y + 1}, Status.D));
            } else if (pipe.status == Status.D) {
                if (horizontal)
                    stack.push(new Pipe(new int[]{x, y + 1}, Status.H));
                if (vertical)
                    stack.push(new Pipe(new int[]{x + 1, y}, Status.V));
                if (vertical && diagonal && horizontal)
                    stack.push(new Pipe(new int[]{x + 1, y + 1}, Status.D));
            }
        }


        bw.write(count + "\n");
        bw.close();
    }
}

class Pipe {
    int[] head;
    Status status;

    public Pipe(int[] head, Status status) {
        this.head = head;
        this.status = status;
    }
}

enum Status {
    H, V, D // Horizontal, Vertical, Diagonal
}