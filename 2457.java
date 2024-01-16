import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Flower> flowers = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");

            int startDay = flatten(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
            int endDay = flatten(Integer.parseInt(in[2]), Integer.parseInt(in[3]));

            flowers.add(new Flower(startDay, endDay));
        }

        Collections.sort(flowers);

        int lastDay = flatten(12, 1);
        int fallingDay = flatten(3, 1);

        int count = 0;

        while (fallingDay < lastDay) {
            int nextFallingDay = 0;

            for (Flower flower : flowers) {
                if (flower.startDay <= fallingDay) {
                    nextFallingDay = Math.max(nextFallingDay, flower.endDay);
                }
            }

            if (fallingDay >= nextFallingDay) {
                break;
            }

            fallingDay = nextFallingDay;
            count++;
        }
        

        if (fallingDay < lastDay) {
            count = 0;
        }

        bw.write(count + "\n");
        bw.close();
    }

    private static int flatten(int month, int day) {
        int flattenedDay = day;

        for (int m = 1; m < month; m++) {
            flattenedDay += days[m];
        }

        return flattenedDay;
    }

    private static class Flower implements Comparable<Flower> {
        int startDay;
        int endDay;

        public Flower(int startDay, int endDay) {
            this.startDay = startDay;
            this.endDay = endDay;
        }

        @Override
        public int compareTo(Flower other) {
            if (this.startDay != other.startDay) {
                return this.startDay - other.startDay;
            }

            return other.endDay - this.endDay;
        }
    }
}