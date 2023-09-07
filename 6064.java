import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] in = br.readLine().split(" ");
            final int M = Integer.parseInt(in[0]);
            final int N = Integer.parseInt(in[1]);
            final int X = Integer.parseInt(in[2]);
            final int Y = Integer.parseInt(in[3]);

            int year = -1;
            for (int i = 0; i < M; i++) {
                int v = (N * i + Y - X);
                if (v % M == 0 && 0 <= v / M && v / M < N) {
                    year = v + X;
                    break;
                }
            }

            bw.write(year + "\n");
        }

        bw.close();
    }
}