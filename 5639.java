import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int[] nums = new int[10000];
        int count = 0;

        for (; count < nums.length; count++) {
            String line = br.readLine();
            if (line == null || line.equals(""))
                break;

            nums[count] = Integer.parseInt(line);
        }

        postorder(nums, 0, count);

        bw.close();
    }

    private static void postorder(int[] nums, int start, int end) throws IOException {
        if (start >= end)
            return;

        int pivot = nums[start];
        int mid = start + 1;

        for (; mid < end; mid++) {
            if (nums[mid] > pivot) {
                break;
            }
        }

        postorder(nums, start + 1, mid);
        postorder(nums, mid, end);
        bw.write(pivot + "\n");
    }
}