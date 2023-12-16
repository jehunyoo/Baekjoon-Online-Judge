import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<Long, Long> dp = new HashMap<>();
    private static long divisor = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        long N = Long.parseLong(br.readLine());

        dp.put(0L, 0L);
        dp.put(1L, 1L);
        dp.put(2L, 1L);

        bw.write(fibonacci(N) +"\n");
        bw.close();
    }

    private static long fibonacci(long n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        long value;

        if (n % 2 == 0) {
            value = fibonacci(n / 2) * ((fibonacci(n / 2 + 1) + fibonacci(n / 2 - 1)) % divisor) % divisor;
        } else {
            value = ((fibonacci((n + 1) / 2) * fibonacci((n + 1) / 2) % divisor) + (fibonacci((n - 1) / 2) * fibonacci((n - 1) / 2) % divisor)) % divisor;
        }

        dp.put(n, value);

        return value;
    }
}