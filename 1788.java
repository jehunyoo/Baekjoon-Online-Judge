import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Long> abs = new HashMap<>();
        Map<Integer, Integer> sign = new HashMap<>();

        abs.put(0, 0L);
        sign.put(0, 0);
        abs.put(1, 1L);
        sign.put(1, 1);

        long divisor = 1_000_000_000L;

        for (int n = 2; n <= N; n++) {
            long value = abs.get(n - 1) + abs.get(n - 2);
            abs.put(n, value % divisor);
            sign.put(n, 1);
        }

        for (int n = -1; n >= N; n--) {
            long value = (abs.get(n + 2) * sign.get(n + 2)) - (abs.get(n + 1) * sign.get(n + 1));
            int signOfValue = value == 0 ? 0 : (int) (value / Math.abs(value));
            abs.put(n, Math.abs(value) % divisor);
            sign.put(n, signOfValue);
        }

        bw.write(sign.get(N) + "\n");
        bw.write(abs.get(N) + "\n");
        bw.close();
    }
}