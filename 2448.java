import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static List<String> stars;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        stars = new ArrayList<>();
        stars.add("*");
        stars.add("* *");
        stars.add("*****");

        buildStars();

        int length = (N / 3) * 5 + (N / 3) - 1;

        for (String star : stars) {
            bw.write(attachBlank(star, length) + "\n");
        }

        bw.close();
    }

    private static void buildStars() {
        for (int level = 3; level < N; level *= 2) {
            int size = stars.size();
            for (int i = 0, blank = 2 * size - 1; i < size; i++, blank -= 2) {
                String next = stars.get(i) + " ".repeat(blank) + stars.get(i);
                stars.add(next);
            }
        }
    }

    private static String attachBlank(String star, int length) {
        int blank = (length - star.length()) / 2;
        return " ".repeat(blank) + star + " ".repeat(blank);
    }
}