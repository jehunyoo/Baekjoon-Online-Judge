import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        long[] P = new long[100 + 1];
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            if (P[N] == 0)
                calculateLength(P, N);

            bw.write(P[N] + "\n");
        }


        bw.close();
    }

    public static long calculateLength(long[] P, int n) {
        if (n < 4 || P[n] > 0) {
            return P[n];
        }

        P[n] = calculateLength(P, n - 2) + calculateLength(P, n - 3);
        return P[n];
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        long[] P = new long[100 + 1];
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        P[4] = 2;

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            if (P[N] == 0)
                calculateLength(P, N);

            bw.write(P[N] + "\n");
        }


        bw.close();
    }

    public static long calculateLength(long[] P, int n) {
        if (n < 5 || P[n] > 0) {
            return P[n];
        }

        P[n] = calculateLength(P, n - 1) + calculateLength(P, n - 5);
        return P[n];
    }
}