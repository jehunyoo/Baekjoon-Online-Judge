import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int lcs = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    lcs = Math.max(lcs, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        bw.write(lcs + "\n");

        if (lcs > 0) {
            StringBuilder sb = new StringBuilder();

            int x = s1.length();
            int y = s2.length();

            while (dp[x][y] > 0) {
                if (dp[x][y] == dp[x - 1][y]) {
                    x--;
                } else if (dp[x][y] == dp[x][y - 1]) {
                    y--;
                } else {
                    sb.append(s1.charAt(x - 1));
                    x--;
                    y--;
                }
            }
            bw.write(sb.reverse() + "\n");
        }

        bw.close();
    }
}