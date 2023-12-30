import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Problem> problems = new ArrayList<>();

        for (int n = 1; n <= N; n++) {
            String[] in = br.readLine().split(" ");
            int D = Integer.parseInt(in[0]);
            int C = Integer.parseInt(in[1]);

            problems.add(new Problem(D, C));
        }

        Collections.sort(problems);

        int maxCupNoodle = 0;
        Queue<Integer> cupNoodles = new PriorityQueue<>();

        int time = 1;

        for (Problem problem : problems) {
            if (time <= problem.deadline) {
                cupNoodles.add(problem.cupNoodle);
                maxCupNoodle += problem.cupNoodle;
                time++;
            } else {
                if (cupNoodles.peek() < problem.cupNoodle) {
                    maxCupNoodle -= cupNoodles.remove();
                    cupNoodles.add(problem.cupNoodle);
                    maxCupNoodle += problem.cupNoodle;
                }
            }
        }

        bw.write(maxCupNoodle + "\n");
        bw.close();
    }

    private static class Problem implements Comparable<Problem> {

        int deadline;
        int cupNoodle;

        public Problem(int deadline, int cupNoodle) {
            this.deadline = deadline;
            this.cupNoodle = cupNoodle;
        }

        @Override
        public int compareTo(Problem other) {
            if (this.deadline == other.deadline) {
                return other.cupNoodle - this.cupNoodle;
            }

            return this.deadline - other.deadline;
        }
    }
}