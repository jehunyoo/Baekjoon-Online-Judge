import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[][] sudoku = new int[9][9];
        int blank = 0;

        for (int i = 0; i < 9; i++) {
            String[] in = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(in[j]);

                if (sudoku[i][j] == 0) {
                    blank++;
                }
            }
        }

        backtrack(sudoku, 0, blank);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(sudoku[i][j] + "");
            }
            bw.newLine();
        }

        bw.close();
    }

    private static boolean backtrack(int[][] sudoku, int next, int blank) {
        if (blank == 0) {
            return false;
        }

        int id = findNextId(sudoku, next);
        List<Integer> candidates = findCandidates(sudoku, id);

        int row = id / 9;
        int col = id % 9;

        for (int candidate : candidates) {
            sudoku[row][col] = candidate;

            boolean continueBackTrack = backtrack(sudoku, id + 1,blank - 1);
            if (!continueBackTrack) {
                return false;
            }

            sudoku[row][col] = 0;
        }

        return true;
    }

    private static int findNextId(int[][] sudoku, int next) {
        int id = -1;

        for (int pos = next; pos < 9 * 9; pos++) {
            int row = pos / 9;
            int col = pos % 9;

            if (sudoku[row][col] == 0) {
                id = pos;
                break;
            }
        }

        return id;
    }

    private static List<Integer> findCandidates(int[][] sudoku, int id) {
        boolean[] used = new boolean[10];

        int row = id / 9;
        int col = id % 9;

        for (int c = 0; c < 9; c++) {
            if (sudoku[row][c] > 0) {
                used[sudoku[row][c]] = true;
            }
        }

        for (int r = 0; r < 9; r++) {
            if (sudoku[r][col] > 0) {
                used[sudoku[r][col]] = true;
            }
        }

        for (int r = (row / 3) * 3; r < (row / 3) * 3 + 3; r++) {
            for (int c = (col / 3) * 3; c < (col / 3) * 3 + 3; c++) {
                if (sudoku[r][c] > 0) {
                    used[sudoku[r][c]] = true;
                }
            }
        }

        List<Integer> candidates = new ArrayList<>();

        for (int number = 1; number <= 9; number++) {
            if (!used[number]) {
                candidates.add(number);
            }
        }

        return candidates;
    }
}