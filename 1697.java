import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{N, 0});
        int num, count = -1;
        boolean[] visited = new boolean[K * 2 + N + 1];

        while(!queue.isEmpty()) {
            Integer[] arr = queue.remove();
            num = arr[0];
            count = arr[1];
            if(num == K)
                break;
            if(!visited[num]) {
                visited[num] = true;
                if(num + 1 < 2 * K)
                    queue.add(new Integer[]{num + 1, count + 1});
                if(num - 1 >= 0)
                    queue.add(new Integer[]{num - 1, count + 1});
                if(num * 2 < 2 * K)
                    queue.add(new Integer[]{num * 2, count + 1});
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}