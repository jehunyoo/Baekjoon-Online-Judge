import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = bf.readLine().split(" ");
        int K = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        int[] lan = new int[K];
        int max = 0;
        for(int k=0; k<K; k++) {
            lan[k] = Integer.parseInt(bf.readLine());
            max = max > lan[k]? max: lan[k];
        }

        long left = 1, right = max, mid;
        long count;
        while(left <= right) {
            count = 0;
            mid = left + (right - left) / 2;
            for (int k = 0; k < K; k++)
                count += lan[k] / mid;
            if (count < N) {
                right = mid - 1;
            } else if (count >= N) {
                left = mid + 1;
            }
        }

        bw.write(right + "\n");

        bw.flush();
        bw.close();
    }
}