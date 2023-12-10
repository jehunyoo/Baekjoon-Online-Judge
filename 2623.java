import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        Plan[] plans = new Plan[N + 1];
        for (int singer = 1; singer <= N; singer++) {
            plans[singer] = new Plan(singer);
        }

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");

            for (int i = 1; i < Integer.parseInt(in[0]); i++) {
                int singer = Integer.parseInt(in[i]);
                int nextSinger = Integer.parseInt(in[i + 1]);

                plans[singer].addNextPlan(plans[nextSinger]);
                plans[nextSinger].inDegree++;
            }
        }

        Queue<Plan> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            Plan plan = plans[i];

            if (plan.inDegree == 0) {
                queue.add(plan);
            }
        }

        List<Integer> singers = new ArrayList<>();

        while (!queue.isEmpty()) {
            Plan plan = queue.remove();
            singers.add(plan.singer);

            for (Plan nextPlan : plan.nextPlans) {
                nextPlan.inDegree--;

                if (nextPlan.inDegree == 0) {
                    queue.add(nextPlan);
                }
            }
        }

        if (singers.size() == N) {
            for (int singer : singers) {
                bw.write(singer + "\n");
            }
        } else {
            bw.write("0\n");
        }

        bw.close();
    }

    private static class Plan {
        int singer;
        int inDegree = 0;
        List<Plan> nextPlans = new ArrayList<>();

        public Plan(int singer) {
            this.singer = singer;
        }

        public void addNextPlan(Plan plan) {
            nextPlans.add(plan);
        }
    }
}