import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, TreeSet<Problem>> problemsByGroup = new HashMap<>();
        Map<Integer, Problem> problemMap = new HashMap<>();

        for (int n = 0; n < N; n++) {
            String[] in = br.readLine().split(" ");
            int P = Integer.parseInt(in[0]);
            int L = Integer.parseInt(in[1]);
            int G = Integer.parseInt(in[2]);

            if (!problemsByGroup.containsKey(G)) {
                problemsByGroup.put(G, new TreeSet<>());
            }

            Problem problem = new Problem(P, L, G);
            problems.add(problem);
            problemsByGroup.get(G).add(problem);
            problemMap.put(P, problem);
        }

        int M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");
            String query = in[0];

            if (query.equals("recommend")) {
                int G = Integer.parseInt(in[1]);
                int x = Integer.parseInt(in[2]);

                if (x == 1) {
                    Problem last = problemsByGroup.get(G).last();
                    bw.write(last.number + "\n");
                } else {
                    Problem first = problemsByGroup.get(G).first();
                    bw.write(first.number + "\n");
                }

            } else if (query.equals("recommend2")) {
                int x = Integer.parseInt(in[1]);

                if (x == 1) {
                    Problem last = problems.last();
                    bw.write(last.number + "\n");
                } else {
                    Problem first = problems.first();
                    bw.write(first.number + "\n");
                }

            } else if (query.equals("recommend3")) {
                int x = Integer.parseInt(in[1]);
                int L = Integer.parseInt(in[2]);

                if (x == 1) {
                    Problem ceiling = problems.ceiling(new Problem(0, L, 0));

                    if (ceiling != null) {
                        bw.write(ceiling.number + "\n");
                    } else {
                        bw.write("-1\n");
                    }
                } else {
                    Problem floor = problems.floor(new Problem(0, L, 0));

                    if (floor != null) {
                        bw.write(floor.number + "\n");
                    } else {
                        bw.write("-1\n");
                    }
                }

            } else if (query.equals("add")) {
                int P = Integer.parseInt(in[1]);
                int L = Integer.parseInt(in[2]);
                int G = Integer.parseInt(in[3]);

                if (!problemsByGroup.containsKey(G)) {
                    problemsByGroup.put(G, new TreeSet<>());
                }

                Problem problem = new Problem(P, L, G);
                problems.add(problem);
                problemsByGroup.get(G).add(problem);
                problemMap.put(P, problem);

            } else {
                int P = Integer.parseInt(in[1]);
                Problem problem = problemMap.get(P);

                problems.remove(problem);
                problemsByGroup.get(problem.group).remove(problem);
                problemMap.remove(P);
            }
        }

        bw.close();
    }

    private static class Problem implements Comparable<Problem> {

        int number;
        int level;
        int group;

        public Problem(int number, int level, int group) {
            this.number = number;
            this.level = level;
            this.group = group;
        }

        @Override
        public int compareTo(Problem other) {
            if (this.level != other.level) {
                return this.level - other.level;
            }

            return this.number - other.number;
        }
    }
}