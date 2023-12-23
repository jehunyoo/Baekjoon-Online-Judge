import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] H = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int answer = H[N - 1];

        outerLoop: for (int outerLeft = 0; outerLeft < N; outerLeft++) {
            for (int outerRight = outerLeft + 3; outerRight < N; outerRight++) {

                int outerSum = H[outerLeft] + H[outerRight];

                int innerLeft = outerLeft + 1;
                int innerRight = outerRight - 1;

                while (innerLeft < innerRight) {
                    int innerSum = H[innerLeft] + H[innerRight];

                    int difference = outerSum - innerSum;
                    answer = Math.min(answer, Math.abs(difference));

                    if (difference > 0) {
                        innerLeft++;
                    } else if (difference < 0) {
                        innerRight--;
                    } else {
                        break outerLoop;
                    }
                }
            }
        }

        bw.write(answer + "\n");
        bw.close();
    }
}