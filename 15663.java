import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer> permutation;
    private static int[] counter = new int[10001];

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = in[0], M = in[1];
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        permutation = new ArrayList<>(M);

        for (int num : nums) {
            counter[num]++;
        }

        permute(Arrays.stream(nums).distinct().toArray(), M);

        bw.close();
    }

    private static void permute(int[] nums, int level) throws IOException {
        if (level == 0) {
            for (int num : permutation) {
                bw.write(num + " ");
            }
            bw.write("\n");
            return;
        }

        for (int num : nums) {
            if (counter[num] > 0) {
                counter[num]--;
                permutation.add(num);
                permute(nums, level - 1);
                permutation.remove(permutation.size() - 1);
                counter[num]++;
            }
        }
    }
}