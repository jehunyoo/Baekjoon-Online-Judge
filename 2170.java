import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]);
            int Y = Integer.parseInt(in[1]);

            Line line = new Line(X, Y);
            lines.add(line);
        }

        Collections.sort(lines);

        int length = 0;
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;

        for (Line line : lines) {
            if (right < line.x) {
                length += right - left;
                left = line.x;
                right = line.y;
            } else if (right < line.y) {
                right = line.y;
            }
        }

        length += right - left;

        bw.write(length + "\n");
        bw.close();
    }

    private static class Line implements Comparable<Line> {
        int x;
        int y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line other) {
            return this.x - other.x;
        }
    }
}