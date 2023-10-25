import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] preorder;
    private static int[] inorder;
    private static int[] postorder;
    private static int[] inorderIndex;
    private static int index = 0;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        inorderIndex = new int[N + 1];
        for (int i = 0; i < N; i++) {
            inorderIndex[inorder[i]] = i;
        }

        preorder = new int[N];
        toPreorder(0, N, 0, N);

        for (int node : preorder) {
            bw.write(node + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static void toPreorder(int inLeft, int inRight, int postLeft, int postRight) {
        if (postRight - postLeft <= 0) {
            return;
        }

        int root = postorder[postRight - 1];
        preorder[index++] = root;

        int mid = inorderIndex[root];
        int leftCount = mid - inLeft;

        toPreorder(inLeft, mid, postLeft, postLeft + leftCount);
        toPreorder(mid + 1, inRight, postLeft + leftCount, postRight - 1);
    }
}

/*
8
8 4 2 5 1 6 3 7
8 4 5 2 6 7 3 1

9
8 4 2 5 1 6 9 3 7
8 4 5 2 9 6 7 3 1
*/