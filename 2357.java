import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        int[] values = new int[N];
        for (int n = 0; n < N; n++) {
            values[n] = Integer.parseInt(br.readLine());
        }

        MinMaxSegmentTree segmentTree = new MinMaxSegmentTree(values);

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int start = Integer.parseInt(in[0]) - 1;
            int end = Integer.parseInt(in[1]) - 1;

            Pair pair = segmentTree.query(start, end);
            bw.write(pair + "\n");
        }

        bw.close();
    }

    private static class MinMaxSegmentTree {

        private Pair[] tree;
        private int leafCounts;

        public MinMaxSegmentTree(int[] values) {
            int height = (int) Math.ceil(Math.log(values.length) / Math.log(2));
            int spaceSize = (int) Math.pow(2, height + 1);

            tree = new Pair[spaceSize];
            leafCounts = values.length;
            initTree(values, 1, 0, leafCounts - 1);
        }

        private void initTree(int[] values, int node, int start, int end) {
            if (start == end) {
                tree[node] = new Pair(values[start], values[start]);
                return;
            }

            initTree(values, node * 2, start, (start + end) / 2);
            initTree(values, node * 2 + 1, (start + end) / 2 + 1, end);

            int min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
            int max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);

            tree[node] = new Pair(min, max);
        }

        public Pair query(int queryStart, int queryEnd) {
            return query(1, 0, leafCounts - 1, queryStart, queryEnd);
        }

        private Pair query(int node, int start, int end, int queryStart, int queryEnd) {
            if (end < queryStart || queryEnd < start) {
                return new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            if (queryStart <= start && end <= queryEnd) {
                return tree[node];
            }

            Pair left = query(node * 2, start, (start + end) / 2, queryStart, queryEnd);
            Pair right = query(node * 2 + 1, (start + end) / 2 + 1, end, queryStart, queryEnd);

            return new Pair(Math.min(left.min, right.min), Math.max(left.max, right.max));
        }
    }

    private static class Pair {
        int min;
        int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return min + " " + max;
        }
    }
}