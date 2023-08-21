import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int[][] candy = new int[N][M];

        for(int i=0; i<N; i++) {
            in = br.readLine().split(" ");
            for(int j=0; j<M; j++)
                candy[i][j] = Integer.parseInt(in[j]);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(i - 1 >= 0 && j - 1 >= 0)
                    candy[i][j] += Math.max(candy[i - 1][j], candy[i][j - 1]);
                else if(i - 1 >= 0 && j - 1 < 0)
                    candy[i][j] += candy[i - 1][j];
                else if(j - 1 >= 0 && i - 1 < 0)
                    candy[i][j] += candy[i][j - 1];
            }
        }

        bw.write(candy[N-1][M-1] + "\n");

        bw.flush();
        bw.close();
    }
}