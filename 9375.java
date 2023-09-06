import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> clothes = new HashMap<>();

            for (int i = 0; i < N; i++) {
                String[] in = br.readLine().split(" ");
                if (clothes.containsKey(in[1])) {
                    clothes.put(in[1], clothes.get(in[1]) + 1);
                } else {
                    clothes.put(in[1], 1);
                }
            }

            long answer = 1;
            for (int value : clothes.values()) {
                answer *= (value + 1);
            }

            bw.write(--answer + "\n");
        }


        bw.close();
    }
}