import java.io.*;

public class Main {

    private static int[][] house;
    private static int R, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        R = Integer.parseInt(in[0]);
        C = Integer.parseInt(in[1]);
        int T = Integer.parseInt(in[2]);

        house = new int[R][C];
        int machine = -1;

        for (int i = 0; i < R; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                house[i][j] = Integer.parseInt(in[j]);

                if (house[i][j] == -1)
                    machine = i - 1;
            }
        }

        for (int time = 0; time < T; time++) {
            int[][] nextHouse = new int[R][C];
            nextHouse[machine][0] = -1;
            nextHouse[machine + 1][0] = -1;

            for (int x = 0; x < R; x++) {
                for (int y = 0; y < C; y++) {
                    if (house[x][y] > 0) {
                        spread(nextHouse, x, y);
                    }
                }
            }

            purifyUpperArea(nextHouse, machine);
            purifyLowerArea(nextHouse, machine + 1);

            house = nextHouse;
        }

        bw.write(sum(house) + "\n");
        bw.close();
    }

    private static void spread(int[][] nextHouse, int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (0 <= nx && nx < R && 0 <= ny && ny < C && house[nx][ny] != -1) {
                nextHouse[nx][ny] += house[x][y] / 5;
                count++;
            }
        }

        nextHouse[x][y] += house[x][y] - (house[x][y] / 5) * count;
    }

    private static void purifyUpperArea(int[][] nextHouse, int start) {
        int x = start, y = 0;
        int[] vector = {-1, 0};

        do {
            int nx = x + vector[0];
            int ny = y + vector[1];

            if (0 <= nx && nx <= start && 0 <= ny && ny < C) {
                if (nextHouse[x][y] != -1)
                    nextHouse[x][y] = nextHouse[nx][ny];

                x = nx;
                y = ny;

            } else {
                vector = new int[]{vector[1], -vector[0]};
            }
        } while (!(x == start && y == 1));

        nextHouse[start][y] = 0;
    }

    private static void purifyLowerArea(int[][] nextHouse, int start) {
        int x = start, y = 0;
        int[] vector = {1, 0};

        do {
            int nx = x + vector[0];
            int ny = y + vector[1];

            if (start <= nx && nx < R && 0 <= ny && ny < C) {
                if (nextHouse[x][y] != -1)
                    nextHouse[x][y] = nextHouse[nx][ny];

                x = nx;
                y = ny;

            } else {
                vector = new int[]{-vector[1], vector[0]};
            }
        } while (!(x == start && y == 1));

        nextHouse[start][y] = 0;
    }

    private static int sum(int[][] newHouse) {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += newHouse[i][j];
            }
        }

        return sum + 2;
    }
}