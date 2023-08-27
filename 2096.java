import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][3];
        for(int i=0; i<N; i++) {
            String[] in = br.readLine().split(" ");
            for(int j=0; j<3; j++)
                graph[i][j] = Integer.parseInt(in[j]);
        }

        int[][] max = new int[N][3];
        int[][] min = new int[N][3];

        for(int j=0; j<3; j++) {
            max[0][j] = graph[0][j];
            min[0][j] = graph[0][j];
        }

        for(int i=1; i<N; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + graph[i][0];
            max[i][1] = Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + graph[i][1];
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + graph[i][2];

            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + graph[i][0];
            min[i][1] = Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + graph[i][1];
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + graph[i][2];
        }

        int maxScore = Math.max(Math.max(max[N - 1][0], max[N - 1][1]), max[N - 1][2]);
        int minScore = Math.min(Math.min(min[N - 1][0], min[N - 1][1]), min[N - 1][2]);

        bw.write(maxScore + " " + minScore + "\n");

        bw.flush();
        bw.close();
    }
}