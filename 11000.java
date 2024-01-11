import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Lesson> lessons = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            int S = Integer.parseInt(in[0]);
            int T = Integer.parseInt(in[1]);

            lessons.add(new Lesson(S, T));
        }

        Collections.sort(lessons);

        Queue<Integer> pq = new PriorityQueue<>();

        for (Lesson lesson : lessons) {
            if (pq.isEmpty()) {
                pq.add(lesson.end);
                continue;
            }

            if (pq.peek() <= lesson.start) {
                pq.remove();
            }

            pq.add(lesson.end);
        }

        bw.write(pq.size() + "\n");
        bw.close();
    }

    private static class Lesson implements Comparable<Lesson> {
        int start;
        int end;

        public Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lesson other) {
            return this.start - other.start;
        }
    }
}
