import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int W = Integer.parseInt(in[0]), H = Integer.parseInt(in[1]);

        Status[][] world = new Status[H][W];

        for (int i = 0; i < H; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                world[i][j] = in[j].equals("0") ? Status.FLATLAND : Status.OBSTACLE;
            }
        }

        Queue<Monkey> queue = new ArrayDeque<>();
        queue.add(new Monkey(0, 0, 0, 0));
        boolean[][][] visited = new boolean[H][W][K + 1];

        Monkey fastest = null;

        while (queue.size() > 0) {
            Monkey monkey = queue.remove();

            if (monkey.x == H - 1 && monkey.y == W - 1) {
                fastest = monkey;
                break;
            }

            if (visited[monkey.x][monkey.y][monkey.k]) {
                continue;
            } else {
                visited[monkey.x][monkey.y][monkey.k] = true;
            }

            for (int i = 0; i < Monkey.actions; i++) {
                int nx = monkey.x + Monkey.dx[i];
                int ny = monkey.y + Monkey.dy[i];
                boolean likeHorse = Math.pow(Monkey.dx[i], 2) + Math.pow(Monkey.dy[i], 2) == 5;

                if (likeHorse && monkey.k == K)
                    continue;

                if (monkey.isMovableTo(world, nx, ny)) {
                    queue.add(
                            new Monkey(nx, ny, likeHorse ? monkey.k + 1 : monkey.k, monkey.count + 1)
                    );
                }
            }

        }

        if (fastest != null)
            bw.write(fastest.count + "\n");
        else
            bw.write("-1\n");
        bw.close();
    }

    enum Status {FLATLAND, OBSTACLE}

    static class Monkey {

        static int[] dx = {1, -1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
        static int[] dy = {0, 0, 1, -1, -2, -1, 1, 2, 2, 1, -1, -2};
        static int actions = dx.length;

        int x;
        int y;
        int k;
        int count;

        public Monkey(int x, int y, int k, int count) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.count = count;
        }

        public boolean isMovableTo(Status[][] world, int nx, int ny) {
            int H = world.length, W = world[0].length;

            return 0 <= nx && nx < H && 0 <= ny && ny < W && world[nx][ny] == Status.FLATLAND;
        }
    }
}