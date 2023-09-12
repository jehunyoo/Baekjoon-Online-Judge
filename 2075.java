import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
//        int[][] table = new int[N][N];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
//                table[i][j] = Integer.parseInt(in[j]);
                priorityQueue.add(-Integer.parseInt(in[j]));
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = -priorityQueue.remove();
        }
        bw.write(answer + "\n");
        bw.close();
    }
}