import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            String[] in = br.readLine().split(" ");
            int W = Integer.parseInt(in[0]), H = Integer.parseInt(in[1]);
            if(W == 0 && H == 0)
                break;

            boolean[][] island = new boolean[H][W];
            for(int h=0; h<H; h++) {
                in = br.readLine().split(" ");
                for(int w=0; w<W; w++)
                    island[h][w] = in[w].equals("1")? true: false;
            }

            int count = 0;
            int[] dx = {1, 1, 1, 0, -1, -1, -1, 0};
            int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
            for(int h=0; h<H; h++) {
                for(int w=0; w<W; w++) {
                    if(island[h][w]) {
                        count++;
                        Deque<Pair> stack = new ArrayDeque<>();
                        stack.push(new Pair(h, w));
                        while(stack.size() > 0) {
                            Pair p = stack.pop();
                            int x = p.x, y = p.y;

                            island[x][y] = false;
                            for(int i=0; i<8; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];
                                if(0 <= nx && nx < H && 0 <= ny && ny < W && island[nx][ny])
                                    stack.push(new Pair(nx, ny));
                            }
                        }
                    }
                }
            }

            bw.write(count + "\n");
        }


        bw.flush();
        bw.close();
    }
}

class Pair {
    int x;
    int y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}