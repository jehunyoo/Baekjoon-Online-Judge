import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static Set<Integer> diagonalAddition;
    private static Set<Integer> diagonalSubtraction;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        boolean[][] oddBoard = new boolean[N][N];
        boolean[][] evenBoard = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                oddBoard[i][j] = in[j].equals("1");
                evenBoard[i][j] = in[j].equals("1");
            }

            if (i % 2 == 0) {
                for (int j = 0; j < N; j += 2) {
                    oddBoard[i][j] = false;
                }
                for (int j = 1; j < N; j += 2) {
                    evenBoard[i][j] = false;
                }
            } else {
                for (int j = 0; j < N; j += 2) {
                    evenBoard[i][j] = false;
                }
                for (int j = 1; j < N; j += 2) {
                    oddBoard[i][j] = false;
                }
            }
        }

        diagonalAddition = new HashSet<>();
        diagonalSubtraction = new HashSet<>();

        int odd = backtrack(oddBoard, 0);
        int even = backtrack(evenBoard, 0);
        int answer = odd + even;

        bw.write(answer + "\n");
        bw.close();
    }

    private static int backtrack(boolean[][] board, int index) {
        int count = 0;

        for (int i = index; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (board[x][y] && !diagonalAddition.contains(x + y) && !diagonalSubtraction.contains(x - y)) {
                diagonalAddition.add(x + y);
                diagonalSubtraction.add(x - y);
                count = Math.max(count, backtrack(board, i + 1) + 1);
                diagonalAddition.remove(x + y);
                diagonalSubtraction.remove(x - y);
            }
        }

        return count;
    }
}

/*
10
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
 */