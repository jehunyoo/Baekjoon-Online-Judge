import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Building> buildings = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            int height = Integer.parseInt(br.readLine());
            buildings.add(new Building(n, height));
        }
        buildings.add(new Building(N, Integer.MAX_VALUE));

        long benchmarks = 0;
        Deque<Building> stack = new ArrayDeque<>();

        for (Building building : buildings) {
            while (!stack.isEmpty() && stack.peek().height <= building.height) {
                Building prevBuilding = stack.pop();
                benchmarks += building.index - prevBuilding.index - 1;
            }
            stack.push(building);
        }

        bw.write(benchmarks + "\n");
        bw.close();
    }

    private static class Building {
        int index;
        int height;

        public Building(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}