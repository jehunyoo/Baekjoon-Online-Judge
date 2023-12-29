import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        Problem[] problems = new Problem[N + 1];

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);

            if (problems[A] == null) {
                problems[A] = new Problem(A);
            }
            if (problems[B] == null) {
                problems[B] = new Problem(B);
            }

            Problem problemA = problems[A];
            Problem problemB = problems[B];

            problemA.nextProblems.add(problemB);
            problemB.inDegree++;
        }

        Queue<Problem> pq = new PriorityQueue<>();

        for (int n = 1; n <= N; n++) {
            if (problems[n] == null) {
                problems[n] = new Problem(n);
            }

            if (problems[n].inDegree == 0) {
                pq.add(problems[n]);
            }
        }

        while (!pq.isEmpty()) {
            Problem problem = pq.remove();

            bw.write(problem.number + " ");

            for (Problem nextProblem : problem.nextProblems) {
                nextProblem.inDegree--;

                if (nextProblem.inDegree == 0) {
                    pq.add(nextProblem);
                }
            }
        }

        bw.newLine();
        bw.close();
    }

    private static class Problem implements Comparable<Problem> {

        int number;
        int inDegree = 0;
        List<Problem> nextProblems = new ArrayList<>();

        public Problem(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(Problem other) {
            return this.number - other.number;
        }
    }
}