import java.io.*;

public class Main {

    static int R, C, M;
    static int[][] ocean, shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        R = Integer.parseInt(in[0]);
        C = Integer.parseInt(in[1]);
        M = Integer.parseInt(in[2]);
        shark = new int[M + 1][5]; // row, column, speed, direction, size
        for(int sharkId=1; sharkId<=M; sharkId++) {
            in = br.readLine().split(" ");
            for(int i=0; i<5; i++)
                shark[sharkId][i] = Integer.parseInt(in[i]);
        }

        int point = 0;
        ocean = new int[R + 1][C + 1];
        int fisherman = 1;

        for(int sharkId=1; sharkId<=M; sharkId++) {
            int r = shark[sharkId][Shark.ROW.index()], c = shark[sharkId][Shark.COL.index()];
            ocean[r][c] = sharkId;
        }

        while(fisherman <= C && M > 0) {
            point += catchShark(fisherman);
            moveShark();
            fisherman++;
        }

        bw.write(point + "\n");

        bw.flush();
        bw.close();
    }

    public static int catchShark(int fisherman) {
        for(int row=1; row<=R; row++) {
            if(ocean[row][fisherman] != 0) {
                int sharkId = ocean[row][fisherman];
                int size = shark[sharkId][Shark.SIZE.index()];
                ocean[row][fisherman] = 0;
                shark[sharkId][Shark.SIZE.index()] = -1; // catch shark
                return size;
            }
        }
        return 0;
    }

    public static void moveShark() {
        int[] dx = {0, -1, 1, 0, 0}; // none, up, down, right, left 
        int[] dy = {0, 0, 0, 1, -1};

        for(int sharkId=1; sharkId<=M; sharkId++) {
            int size = shark[sharkId][Shark.SIZE.index()];
            if(size < 0)
                continue;

            int row = shark[sharkId][Shark.ROW.index()];
            int col = shark[sharkId][Shark.COL.index()];
            int speed = shark[sharkId][Shark.SPEED.index()];
            int dir = shark[sharkId][Shark.DIR.index()];

            if(ocean[row][col] == sharkId)
                ocean[row][col] = 0;

            int time = speed;
            switch (dir) { // optimize time
                case 1: case 2:
                    time = speed % (2 * (R - 1)); break;
                case 3: case 4:
                    time = speed % (2 * (C - 1)); break;
            }

            int x = row, y = col;
            while(time > 0) {
                int nx = x + dx[dir], ny = y + dy[dir];
                if(1 <= nx && nx <= R && 1 <= ny && ny <= C) {
                    x = nx;
                    y = ny;
                    time--;
                } else {
                    switch (dir) {
                        case 1: dir = 2; break;
                        case 2: dir = 1; break;
                        case 3: dir = 4; break;
                        case 4: dir = 3; break;
                    }
                }
            }

            if(ocean[x][y] == 0) { // empty cell, move
                ocean[x][y] = sharkId;
                shark[sharkId][Shark.ROW.index()] = x;
                shark[sharkId][Shark.COL.index()] = y;
                shark[sharkId][Shark.DIR.index()] = dir;
            }
            else { // non-empty cell
                if(sharkId < ocean[x][y]) { // if shark@ocean[x][y] hasn't moved yet, move
                    ocean[x][y] = sharkId;
                    shark[sharkId][Shark.ROW.index()] = x;
                    shark[sharkId][Shark.COL.index()] = y;
                    shark[sharkId][Shark.DIR.index()] = dir;
                }  else if(shark[ocean[x][y]][Shark.SIZE.index()] > size) { // if shark@ocean[x][y] has moved and bigger, make size -1
                    shark[sharkId][Shark.SIZE.index()] = -1;
                } else if(shark[ocean[x][y]][Shark.SIZE.index()] < size) { // if shark@ocean[x][y] has moved and smaller, move and eat
                    shark[ocean[x][y]][Shark.SIZE.index()] = -1;
                    ocean[x][y] = sharkId;
                    shark[sharkId][Shark.ROW.index()] = x;
                    shark[sharkId][Shark.COL.index()] = y;
                    shark[sharkId][Shark.DIR.index()] = dir;
                }
            }
        }
    }
}

enum Shark {
    ROW {int index() { return 0; }},
    COL {int index() { return 1; }},
    SPEED {int index() { return 2; }},
    DIR {int index() { return 3; }},
    SIZE {int index() { return 4; }};

    abstract int index();
}