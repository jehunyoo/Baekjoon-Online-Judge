import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int H = Integer.parseInt(in[1]);

        int[] obstacles = new int[N];
        for (int n = 0; n < N; n++) {
            obstacles[n] = Integer.parseInt(br.readLine());
        }

        int[] countsFromBottom = count(obstacles, 0, H);
        int[] countsFromTop = count(obstacles, 1, H);

        int minCount = N;
        int totalCount = 0;

        for (int i = 1; i <= H; i++) {
            int count = countsFromBottom[i] + countsFromTop[H - i + 1];
            if (count < minCount) {
                minCount = count;
                totalCount = 1;
            } else if (count == minCount) {
                totalCount++;
            }
        }

        bw.write(minCount + " " + totalCount + "\n");
        bw.close();
    }

    private static int[] count(int[] obstacles, int bias, int H) {
        int[] counts = new int[H + 1];
        for (int i = bias; i < obstacles.length; i += 2) {
            counts[obstacles[i]]++;
        }

        for (int i = H - 1; i > 0; i--) {
            counts[i - 1] += counts[i];
        }

        return counts;
    }
}