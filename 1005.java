import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] in = br.readLine().split(" ");
            int N = Integer.parseInt(in[0]);
            int K = Integer.parseInt(in[1]);
            int[] D = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

            Building[] buildings = new Building[N + 1];

            for (int n = 1; n <= N; n++) {
                buildings[n] = new Building(n, D[n]);
            }

            for (int k = 0; k < K; k++) {
                in = br.readLine().split(" ");
                int X = Integer.parseInt(in[0]);
                int Y = Integer.parseInt(in[1]);

                buildings[X].nextBuildings.add(buildings[Y]);
                buildings[Y].inDegree++;
            }

            int W = Integer.parseInt(br.readLine());

            Queue<Building> queue = new ArrayDeque<>();

            for (int n = 1; n <= N; n++) {
                if (buildings[n].inDegree == 0) {
                    queue.add(buildings[n]);
                }
            }

            int time = 0;

            while (!queue.isEmpty()) {
                Building building = queue.remove();

                if (building.number == W) {
                    time = building.time + building.cost;
                }

                for (Building nextBuilding : building.nextBuildings) {
                    nextBuilding.inDegree--;
                    nextBuilding.time = Math.max(nextBuilding.time, building.time + building.cost);

                    if (nextBuilding.inDegree == 0) {
                        queue.add(nextBuilding);
                    }
                }
            }

            bw.write(time + "\n");
        }

        bw.close();
    }

    private static class Building {
        int number;
        int cost;
        int time = 0;
        int inDegree = 0;
        List<Building> nextBuildings = new ArrayList<>();

        public Building(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }
}