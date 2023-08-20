import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] in = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(in[i]);

        Deque<List<Integer>> stack = new ArrayDeque<>();
        for(int index=0; index<N; index++) {
            List<Integer> list = new ArrayList<>();
            list.add(index);
            stack.push(list);
        }

        int answer = Integer.MIN_VALUE;
        while(stack.size() > 0) {
            List<Integer> list = stack.pop();
            if(list.size() == N) {
                answer = Math.max(answer, sumDifference(list, arr));
                continue;
            }

            for(int index=0; index<N; index++) {
                if(!list.contains(index)) {
                    List<Integer> l = new ArrayList<>(list);
                    l.add(index);
                    stack.push(l);
                }
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    public static int sumDifference(List<Integer> list, int[] arr) {
        int sum = 0;
        for(int i=0; i<list.size()-1; i++) {
            sum += Math.abs(arr[list.get(i)] - arr[list.get(i + 1)]);
        }
        return sum;
    }
}