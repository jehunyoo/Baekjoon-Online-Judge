import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int K = Integer.parseInt(in[2]);

        int[] array = new int[N];
        for (int n = 0; n < N; n++) {
            array[n] = Integer.parseInt(br.readLine());
        }

        ProductSegmentTree segmentTree = new ProductSegmentTree(array);

        for (int i = 0; i < M + K; i++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);
            int C = Integer.parseInt(in[2]);

            if (A == 1) {
                segmentTree.update(B - 1, C);
            } else if (A == 2) {
                long product = segmentTree.query(B - 1, C - 1);
                bw.write(product + "\n");
            }
        }

        bw.close();
    }

    private static class ProductSegmentTree {

        private static int divisor = 1_000_000_007;

        private long[] tree;
        private int leafCounts;

        public ProductSegmentTree(int[] array) {
            int height = (int) Math.ceil(Math.log(array.length) / Math.log(2));
            int spaceSize = (int) Math.pow(2, height + 1);

            this.tree = new long[spaceSize];
            this.leafCounts = array.length;

            initTree(array, 1, 0, leafCounts - 1);
        }

        private void initTree(int[] array, int node, int start, int end) {
            if (start == end) {
                tree[node] = array[start];
                return;
            }

            initTree(array, node * 2, start, (start + end) / 2);
            initTree(array, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % divisor;
        }

        public long query(int queryStart, int queryEnd) {
            return query(1, 0, leafCounts - 1, queryStart, queryEnd);
        }

        private long query(int node, int start, int end, int queryStart, int queryEnd) {
            if (queryEnd < start || end < queryStart) {
                return 1;
            }

            if (queryStart <= start && end <= queryEnd) {
                return tree[node];
            }

            long leftProduct = query(node * 2, start, (start + end) / 2, queryStart, queryEnd);
            long rightProduct = query(node * 2 + 1, (start + end) / 2 + 1, end, queryStart, queryEnd);

            return (leftProduct * rightProduct) % divisor;
        }

        public void update(int index, int value) {
            update(1, 0, leafCounts - 1, index, value);
        }

        private void update(int node, int start, int end, int index, int value) {
            if (index < start || end < index) {
                return;
            }

            if (start == end) {
                tree[node] = value;
                return;
            }

            update(node * 2, start, (start + end) / 2, index, value);
            update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);

            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % divisor;
        }
    }
}