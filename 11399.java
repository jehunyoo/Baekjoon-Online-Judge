import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int[] P = new int[N];
        for(int i=0; i<N; i++)
            P[i] = Integer.parseInt(in[i]);
        
        Arrays.sort(P);
        int sum = 0;
        for(int i=0; i<N; i++) {
            sum += P[i] * (N - i);
        }
        bw.write(sum + "\n");

        bw.flush();
        bw.close();
    }
}