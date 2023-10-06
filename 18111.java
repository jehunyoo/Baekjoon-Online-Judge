import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]), B = Integer.parseInt(in[2]);
        int[][] land = new int[N][M];

        int min = 256, max = 0;

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                land[i][j] = Integer.parseInt(in[j]);
                min = Math.min(min, land[i][j]);
                max = Math.max(max, land[i][j]);
            }
        }

        int time = 256 * 500 * 500 * 2;
        int height = -1;

        for (int targetHeight = min; targetHeight <= max; targetHeight++) {
            int timeForTargetHeight = 0;
            int inventory = B;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int heightIJ = land[i][j];

                    if (heightIJ > targetHeight) {
                        timeForTargetHeight += 2 * (heightIJ - targetHeight);
                        inventory += heightIJ - targetHeight;
                    } else if (heightIJ < targetHeight) {
                        timeForTargetHeight += targetHeight - heightIJ;
                        inventory -= targetHeight - heightIJ;
                    }
                }
            }

            if (inventory >= 0 && timeForTargetHeight <= time) {
                time = timeForTargetHeight;
                height = Math.max(height, targetHeight);
            }
        }

        bw.write(time + " " + height + "\n");
        bw.close();
    }
}