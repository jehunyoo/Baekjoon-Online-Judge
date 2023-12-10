import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        int[] root = new int[N];

        for (int n = 0; n < N; n++) {
            root[n] = n;
        }

        int[][] edges = new int[M + 1][2];

        for (int i = 1; i <= M; i++) {
            in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]);
            int Y = Integer.parseInt(in[1]);

            edges[i][0] = X;
            edges[i][1] = Y;
        }

        int answer = 0;

        for (int i = 1; i <= M; i++) {
            int X = edges[i][0];
            int Y = edges[i][1];

            if (find(root, X) == find(root, Y)) {
                answer = i;
                break;
            }

            union(root, X, Y);
        }

        bw.write(answer + "\n");
        bw.close();
    }

    private static int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }

        return root[x];
    }

    private static void union(int[] root, int x, int y) {
        x = find(root, x);
        y = find(root, y);

        if (x < y) {
            root[y] = x;
        } else {
            root[x] = y;
        }
    }
}