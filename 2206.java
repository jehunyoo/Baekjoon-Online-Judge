import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        int[][] graph = new int[N][M];
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++)
                graph[i][j] = line.charAt(j) - '0';
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int answer = -1;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));

        int[][] distance = new int[N][M];
        boolean[][][] visited = new boolean[N][M][2]; // [][][0] not passed, [][][1] passed

        distance[0][0] = 1;

        while(queue.size() > 0) {
            Node node = queue.remove();
            if(node.x == N - 1 && node.y == M - 1) {
                answer = distance[N - 1][M - 1];
                break;
            }

            if(!visited[node.x][node.y][node.passed]) {
                visited[node.x][node.y][node.passed] = true;
                for(int i=0; i<4; i++) {
                    int nx = node.x + dx[i], ny = node.y + dy[i];
                    if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny][node.passed]) {
                        if(graph[nx][ny] == 0) {
                            queue.add(new Node(nx, ny, node.passed));
                            distance[nx][ny] = distance[node.x][node.y] + 1;
                        } else if(graph[nx][ny] == 1 && node.passed == 0) {
                            queue.add(new Node(nx, ny, 1));
                            distance[nx][ny] = distance[node.x][node.y] + 1;
                        }
                    }
                }
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    static class Node {
        int x;
        int y;
        int passed;

        Node(int x, int y) {
            this(x, y, 0);
        }

        Node(int x, int y, int passed) {
            this.x = x;
            this.y = y;
            this.passed = passed;
        }
    }
}