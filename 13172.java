import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long prime = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(br.readLine());
        long sum = 0L;

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");
            int N = Integer.parseInt(in[0]);
            int S = Integer.parseInt(in[1]);

            int gcd = gcd(N, S);
            N /= gcd;
            S /= gcd;

            long inverse = power(N, prime - 2);
            sum += (inverse * S) % prime;
        }

        bw.write(sum % prime + "\n");
        bw.close();
    }

    private static int gcd(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);

        while (min > 0) {
            int temp = max;
            max = min;
            min = temp % min;
        }

        return max;
    }

    private static long power(long base, long exponent) {
        if (exponent == 1) {
            return base;
        }

        long half = power(base, exponent / 2);
        long result = half * half % prime;

        if (exponent % 2 == 1) {
            result = (result * base) % prime;
        }

        return result % prime;
    }
}