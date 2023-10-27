import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean[][] visited;
    private static boolean[] alphabets;
    private static char[][] graph;
    private static int R, C;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");
        R = Integer.parseInt(in[0]);
        C = Integer.parseInt(in[1]);

        graph = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[R][C];
        alphabets = new boolean['Z' - 'A' + 1];

        int count = backtrack(0, 0);

        bw.write(count + "\n");
        bw.close();
    }

    private static int backtrack(int x, int y) {
        visited[x][y] = true;
        alphabets[graph[x][y] - 'A'] = true;

        int count = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (0 <= nx && nx < R && 0 <= ny && ny < C && !visited[nx][ny] && !alphabets[graph[nx][ny] - 'A']) {
                count = Math.max(count, backtrack(nx, ny));
            }
        }

        alphabets[graph[x][y] - 'A'] = false;
        visited[x][y] = false;

        return count + 1;
    }
}