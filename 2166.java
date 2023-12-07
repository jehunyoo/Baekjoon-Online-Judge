import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            long X = Long.parseLong(in[0]);
            long Y = Long.parseLong(in[1]);

            points.add(new Point(X, Y));
        }

        points.add(points.get(0));

        double sumOfProduct = 0;

        for (int i = 0; i < N; i++) {
            Point point = points.get(i);
            Point nextPoint = points.get(i + 1);

            sumOfProduct += point.x * nextPoint.y - point.y * nextPoint.x;
        }

        double area = Math.round(Math.abs(sumOfProduct) / 2 * 10) / 10.0;

        bw.write(String.format("%.1f", area) + "\n");
        bw.close();
    }

    private static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}