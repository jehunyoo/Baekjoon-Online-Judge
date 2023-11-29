import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String code = br.readLine();
        int divisor = 1_000_000;

        int[] dp = new int[code.length() + 1];
        dp[0] = 1;

        for (int l = 1; l <= code.length(); l++) {
            if (checkValid(code.substring(l - 1, l))) {
                dp[l] = (dp[l] + dp[l - 1]) % divisor;
            }

            if (l - 2 >= 0 && checkValid(code.substring(l - 2, l))) {
                dp[l] = (dp[l] + dp[l - 2]) % divisor;
            }
        }

        bw.write(dp[code.length()] + "\n");
        bw.close();
    }

    private static boolean checkValid(String code) {
        int num = Integer.parseInt(code);

        if (code.length() == 1 && 1 <= num && num <= 9) {
            return true;
        }

        if (code.length() == 2 && 10 <= num && num <= 26) {
            return true;
        }

        return false;
    }
}