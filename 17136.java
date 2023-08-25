import java.io.*;

public class Main {

    static int[][] paper;
    static int[] colored = new int[]{0, 5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        paper = new int[10][10];
        for(int i=0; i<10; i++) {
            String[] in = br.readLine().split(" ");
            for(int j=0; j<10; j++)
                paper[i][j] = Integer.parseInt(in[j]);
        }
        dfs(0, 0, 0);
        bw.write((answer == Integer.MAX_VALUE? -1: answer)+ "\n");

        bw.flush();
        bw.close();
    }
    public static void dfs(int x, int y, int count) {
        if(x >= 9 && y > 9) {
            answer = Math.min(answer, count);
            return;
        }
        if(answer <= count)
            return;
        if(y > 9) {
            dfs(x + 1, 0, count);
            return;
        }
        
        if(paper[x][y] == 1) {
            for(int size=5; size>0; size--) {
                if(colored[size] > 0 && attachable(x, y, size)) {
                    colored[size]--;
                    flip(x, y, size);
                    dfs(x, y + 1, count + 1);
                    flip(x, y, size);
                    colored[size]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }
    public static boolean attachable(int x, int y, int size) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(!(i < 10 && j < 10 && paper[i][j] == 1))
                    return false;
            }
        }
        return true;
    }
    public static void flip(int x, int y, int size) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                paper[i][j] ^= 1;
            }
        }
    }
}

/*
1 1 1  1 1 0 0 0 0 0
1 1 1  1 1 0 0 0 0 0
1 1 1  1 1 0 0 0 0 0

1 1 1  1 1 0 0 0 0 0
1 1 1  1 1 0 0 0 0 0
1 1 1  0 0 0 0 0 0 0
0 0 0  0 0 0 0 0 0 0
0 0 0  0 0 0 0 0 0 0
0 0 0  0 0 0 0 0 0 0
0 0 0  0 0 1 0 1 0 1
*/