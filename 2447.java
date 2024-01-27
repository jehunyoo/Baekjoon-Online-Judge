import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        boolean[][] pattern = new boolean[N][N];

        makePattern(N, pattern);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write((pattern[i][j] ? "*" : " "));
            }
            bw.newLine();
        }

        bw.close();
    }

    private static void makePattern(int n, boolean[][] pattern) {
        if (n == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pattern[i][j] = true;
                }
            }

            pattern[1][1] = false;
            return;
        }

        makePattern(n / 3, pattern);

        for (int i = 0; i < n; i += n / 3) {
            for (int j = 0; j < n; j += n / 3) {
                if ((i == 0 && j == 0) || (i == n / 3 && j == n / 3)) {
                    continue;
                }

                for (int x = 0; x < n / 3; x++) {
                    for (int y = 0; y < n / 3; y++) {
                        pattern[i + x][j + y] = pattern[x][y];
                    }
                }
            }
        }
    }
}