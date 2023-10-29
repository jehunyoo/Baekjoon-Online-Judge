import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int[] cards = new int[N]; // cards[player number] = card number
        int[] scores = new int[N]; // scores[player number] = score

        int max = 0;
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(in[i]);
            max = Math.max(max, cards[i]);
        }

        int[] players = new int[max + 1]; // players[card number] = player number
        Arrays.fill(players, -1);
        for (int i = 0; i < N; i++) {
            players[cards[i]] = i;
        }

        for (int card : cards) {
            for (int multiple = card * 2; multiple <= max; multiple += card) {
                if (players[multiple] >= 0) {
                    scores[players[multiple]]--;
                    scores[players[card]]++;
                }
            }
        }

        for (int score : scores) {
            bw.write(score + " ");
        }
        bw.newLine();
        bw.close();
    }
}