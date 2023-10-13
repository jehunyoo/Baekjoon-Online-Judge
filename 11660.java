import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // faster

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = in[0], M = in[1];

        int[][] table = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = table[i - 1][j] + table[i][j - 1] - table[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            bw.write((table[x2][y2] - table[x1 - 1][y2] - table[x2][y1 - 1] + table[x1 - 1][y1 - 1]) + "\n");
        }


        bw.close();
    }
}

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] table;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = in[0], M = in[1];

        table = new int[N][N];
        for (int i = 0; i < N; i++)
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] ranges = new int[M][];
        for (int i = 0; i < M; i++)
            ranges[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(value -> Integer.parseInt(value) - 1).toArray();

        dp = new int[N][N];

        for (int[] range : ranges) {
            int x1 = range[0], y1 = range[1], x2 = range[2], y2 = range[3];

            int sum = cumulativeSum2D(x2, y2);

            if (x1 - 1 >= 0)
                sum -= cumulativeSum2D(x1 - 1, y2);
            if (y1 - 1 >= 0)
                sum -= cumulativeSum2D(x2, y1 - 1);
            if (x1 - 1 >= 0 && y1 - 1 >= 0)
                sum += cumulativeSum2D(x1 - 1, y1 - 1);

            bw.write(sum + "\n");
        }

        bw.close();
    }

    private static int cumulativeSum2D(int x, int y) {
        if (dp[x][y] > 0)
            return dp[x][y];

        dp[x][y] = table[x][y];

        if (x - 1 >= 0)
            dp[x][y] += cumulativeSum2D(x - 1, y);
        if (y - 1 >= 0)
            dp[x][y] += cumulativeSum2D(x, y - 1);
        if (x - 1 >= 0 && y - 1 >= 0)
            dp[x][y] -= cumulativeSum2D(x - 1, y - 1);

        return dp[x][y];
    }
}