import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        long count = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                break;
            }

            int left = i + 1;
            int right = A.length - 1;

            while (left < right) {
                int sum = A[i] + A[left] + A[right];

                if (sum == 0) {
                    int countLeft = 1;
                    int countRight = 1;

                    if (A[left] == A[right]) {
                        count += (long) (right - left + 1) * (right - left) / 2;
                        break;
                    }

                    for (; A[left] == A[left + 1]; left++) {
                        countLeft++;
                    }

                    for (; A[right] == A[right - 1]; right--) {
                        countRight++;
                    }

                    count += (long) countLeft * countRight;
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        bw.write(count + "\n");
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        long count = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int target = -(A[i] + A[j]);

                if (target >= A[j]) {
                    count += bisectRight(A, j + 1, A.length, target) - bisectLeft(A, j + 1, A.length, target);
                }
            }
        }

        bw.write(count + "\n");
        bw.close();
    }

    private static int bisectLeft(int[] A, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (A[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int bisectRight(int[] A, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (A[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}