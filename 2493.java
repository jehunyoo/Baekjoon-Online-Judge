import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] heights = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[N + 1];

        Deque<Tower> stack = new ArrayDeque<>();

        for (int index = N; index > 0; index--) {
            Tower tower = new Tower(index, heights[index]);

            if (stack.isEmpty() || tower.height < stack.peek().height) {
                stack.push(tower);
            } else {
                while (!stack.isEmpty() && tower.height >= stack.peek().height) {
                    Tower prevTower = stack.pop();
                    answer[prevTower.index] = tower.index;
                }

                stack.push(tower);
            }
        }

        for (int index = 1; index <= N; index++) {
            bw.write(answer[index] + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static class Tower {
        int index;
        int height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}