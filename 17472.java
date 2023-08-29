import java.util.*;
import java.io.*;

public class Main {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int[][] world = new int[N][M];
        for(int i=0; i<N; i++) {
            in = br.readLine().split(" ");
            for(int j=0; j<M; j++)
                world[i][j] = Integer.parseInt(in[j]);
        }

        // DFS
        boolean[][] visited = new boolean[N][M];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int islands = 0;

        for(int i=0; i<N; i++) { // mark island number using dfs
            for(int j=0; j<M; j++) {
                if(world[i][j] == 1 && !visited[i][j]) {
                    islands++;
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j});
                    while(stack.size() > 0) {
                        int[] coord = stack.pop();
                        int x = coord[0], y = coord[1];
                        if(!visited[x][y]) {
                            visited[x][y] = true;
                            world[x][y] = islands;
                            for(int d=0; d<4; d++) {
                                int nx = x + dx[d], ny = y + dy[d];
                                if(0 <= nx && nx < N && 0 <= ny && ny < M && world[nx][ny] == 1 && !visited[nx][ny])
                                    stack.push(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
        
        // brute force: O((NM)^2)
        int[][] distance = new int[islands + 1][islands + 1];
        for(int i=0; i<=islands; i++) {
            for(int j=0; j<=islands; j++)
                distance[i][j] = Integer.MAX_VALUE;
        }
        for(int a=0; a<N*M; a++) { // calculate the shortest distance between islands using brute force
            for(int b=a+1; b<N*M; b++) {
                int x1 = a / M, y1 = a % M;
                int x2 = b / M, y2 = b % M;
                int island1 = world[x1][y1];
                int island2 = world[x2][y2];

                if(island1 != 0 && island2 != 0 && island1 != island2 && (x1 == x2 || y1 == y2)) {
                    int dist = Math.abs((x1 - x2) + (y1 - y2)) - 1;
                    if(dist > 1 && dist < distance[island1][island2]) {
                        int x = x1 + (x1 - x2 == 0? 0: 1);
                        int y = y1 + (y1 - y2 == 0? 0: 1);
                        while(x != x2 || y != y2) {
                            if(world[x][y] != 0) {
                                dist = Integer.MAX_VALUE;
                                break;
                            }
                            x += (x1 - x2 == 0? 0: 1);
                            y += (y1 - y2 == 0? 0: 1);
                        }
                        distance[island1][island2] = distance[island2][island1] = Math.min(distance[island1][island2], dist);
                    }
                }
            }
        }

        // minimum spanning tree: kruskal's algorithm
        int[][] edges = new int[6 * 7 / 2][3];
        int e = 0;
        for(int island1=1; island1<=islands; island1++) {
            for(int island2=island1+1; island2<=islands; island2++) {
                if(distance[island1][island2] < Integer.MAX_VALUE) {
                    edges[e++] = new int[]{distance[island1][island2], island1, island2};
                }
            }
        }

        edges = Arrays.copyOfRange(edges, 0, e);
        Arrays.sort(edges, (arr1, arr2) -> (arr1[0] - arr2[0]));

        int answer = 0;
        root = new int[islands + 1];
        for(int island=1; island<=islands; island++)
            root[island] = island;

        for(int[] edge: edges) {
            int cost = edge[0];
            if(find(edge[1]) != find(edge[2])) {
                union(edge[1], edge[2]);
                answer += cost;
            }
        }

        // check if all islands are connected
        for(int island=1; island<=islands; island++) {
            find(island); // update root island using path compression
            if(root[island] != root[1]) {
                answer = -1;
                break;
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    public static int find(int node) {
        if(root[node] != node)
            root[node] = find(root[node]);
        return root[node];
    }

    public static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if(node1 < node2)
            root[node2] = node1;
        else
            root[node1] = node2;
    }
}