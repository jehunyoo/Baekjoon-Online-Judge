import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);
        int K = Integer.parseInt(in[2]);

        long[] numbers = new long[N];

        for (int n = 0; n < N; n++) {
            numbers[n] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(numbers);

        for (int i = 0; i < M + K; i++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);

            if (A == 1) {
                int index = Integer.parseInt(in[1]) - 1;
                long value = Long.parseLong(in[2]);

                segmentTree.update(index, value);
            } else if (A == 2) {
                int rangeStart = Integer.parseInt(in[1]) - 1;
                int rangeEnd = Integer.parseInt(in[2]) - 1;

                long sum = segmentTree.query(rangeStart, rangeEnd);

                bw.write(sum + "\n");
            }
        }

        bw.close();
    }

    private static class SegmentTree {

        private long[] tree;
        private int leafCount;

        public SegmentTree(long[] numbers) {
            int height = (int) Math.ceil(Math.log(numbers.length) / Math.log(2));
            int spaceSize = (int) Math.pow(2, height + 1);

            this.tree = new long[spaceSize];
            this.leafCount = numbers.length;

            initTree(numbers, 1, 0, leafCount - 1);
        }

        private void initTree(long[] array, int node, int start, int end) {
            if (start == end) {
                tree[node] = array[start];
                return;
            }

            initTree(array, node * 2, start, (start + end) / 2);
            initTree(array, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int queryStart, int queryEnd) {
            return query(1, 0, leafCount - 1, queryStart, queryEnd);
        }

        private long query(int node, int start, int end, int queryStart, int queryEnd) {
            if (queryStart > end || queryEnd < start) {
                return 0;
            }

            if (queryStart <= start && end <= queryEnd) {
                return tree[node];
            }

            long leftSum = query(node * 2, start, (start + end) / 2, queryStart, queryEnd);
            long rightSum = query(node * 2 + 1, (start + end) / 2 + 1, end, queryStart, queryEnd);

            return leftSum + rightSum;
        }

        public void update(int index, long value) {
            update(1, 0, leafCount - 1, index, value);
        }

        private void update(int node, int start, int end, int index, long value) {
            if (index < start || end < index) {
                return;
            }

            if (start == end && start == index) {
                tree[node] = value;
                return;
            }

            update(node * 2, start, (start + end) / 2, index, value);
            update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }
}