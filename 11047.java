import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), K = Integer.parseInt(in[1]);
        int[] coins = new int[N];

        for(int i=0; i<N; i++)
            coins[i] = Integer.parseInt(br.readLine());

        int answer = 0;
        for(int i=N-1; i>=0; i--) {
            int coin = coins[i];
            answer += K / coin;
            K %= coin;
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }
}