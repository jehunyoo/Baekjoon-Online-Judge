import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, Integer> levels = new HashMap<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            int P = Integer.parseInt(in[0]);
            int L = Integer.parseInt(in[1]);

            problems.add(new Problem(P, L));
            levels.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            String[] query = br.readLine().split(" ");

            if (query[0].equals("recommend")) {
                if (query[1].equals("1")) {
                    Problem lastProblem = problems.last();
                    bw.write(lastProblem.number + "\n");
                } else {
                    Problem firstProblem = problems.first();
                    bw.write(firstProblem.number + "\n");
                }
            } else if (query[0].equals("add")) {
                Problem problem = new Problem(Integer.parseInt(query[1]), Integer.parseInt(query[2]));
                problems.remove(problem);
                problems.add(problem);
                levels.put(problem.number, problem.level);
            } else if (query[0].equals("solved")) {
                int number = Integer.parseInt(query[1]);
                Problem problem = new Problem(number, levels.get(number));
                problems.remove(problem);
            }
        }

        bw.close();
    }

    private static class Problem implements Comparable<Problem> {

        int number;
        int level;

        public Problem(int number, int level) {
            this.number = number;
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return number == problem.number && level == problem.level;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, level);
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level != o.level) {
                return this.level - o.level;
            }

            return this.number - o.number;
        }
    }
}