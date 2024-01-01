import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Task[] tasks = new Task[N + 1];

        for (int n = 1; n <= N; n++) {
            String[] in = br.readLine().split(" ");
            int T = Integer.parseInt(in[0]);
            int C = Integer.parseInt(in[1]);

            if (tasks[n] == null) {
                tasks[n] = new Task(n);
            }

            tasks[n].setTime(T);

            for (int c = 0; c < C; c++) {
                int next = Integer.parseInt(in[c + 2]);

                if (tasks[next] == null) {
                    tasks[next] = new Task(next);
                }

                tasks[n].nextTasks.add(tasks[next]);
                tasks[next].inDegree++;
            }
        }

        int time = 0;
        Queue<Task> queue = new ArrayDeque<>();

        for (int n = 1; n <= N; n++) {
            Task task = tasks[n];

            if (task.inDegree == 0) {
                queue.add(task);
            }
        }

        while (!queue.isEmpty()) {
            Task task = queue.remove();

            if (task.nextTasks.isEmpty()) {
                time = Math.max(time, task.triggeredTime + task.time);
                continue;
            }

            for (Task nextTask : task.nextTasks) {
                nextTask.inDegree--;
                nextTask.triggeredTime = Math.max(nextTask.triggeredTime, task.triggeredTime + task.time);

                if (nextTask.inDegree == 0) {
                    queue.add(nextTask);
                }
            }
        }

        bw.write(time + "\n");
        bw.close();
    }

    private static class Task {

        int number;
        int time;
        int triggeredTime = 0;
        int inDegree = 0;
        List<Task> nextTasks = new ArrayList<>();

        public Task(int number) {
            this.number = number;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}