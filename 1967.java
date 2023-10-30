import java.util.stream.*;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        TreeNode[] tree = new TreeNode[N + 1];

        for (int i = 0; i < N - 1; i++) {
            String[] in = br.readLine().split(" ");
            int id = Integer.parseInt(in[0]);
            if (tree[id] == null) {
                tree[id] = new TreeNode(id);
            }

            tree[id].add(Integer.parseInt(in[1]), Integer.parseInt(in[2]));
        }

        getMaxDiameter(tree, 1);

        bw.write(answer + "\n");
        bw.close();
    }

    private static int getMaxDiameter(TreeNode[] tree, int root) {
        TreeNode node = tree[root];
        if (node == null || node.weights.isEmpty())
            return 0;

        List<Integer> values = node.weights.entrySet().stream()
                .map(entry -> getMaxDiameter(tree, entry.getKey()) + entry.getValue())
                .sorted()
                .collect(Collectors.toList());

        int max = values.get(values.size() - 1);
        if (values.size() > 1) {
            int secondMax = values.get(values.size() - 2);
            answer = Math.max(answer, max + secondMax);
        }

        answer = Math.max(answer, max);

        return max;
    }

    private static class TreeNode {
        int root;
        Map<Integer, Integer> weights = new HashMap<>();

        public TreeNode(int root) {
            this.root = root;
        }

        public void add(int leaf, int weight) {
            this.weights.put(leaf, weight);
        }
    }
}