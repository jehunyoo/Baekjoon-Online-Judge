import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {

        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        Space[][] spaces = new Space[N][M];

        for (int row = 0; row < N; row++) {
            in = br.readLine().split(" ");
            for (int col = 0; col < M; col++) {
                spaces[row][col] = in[col].equals("1") ? Space.CHEESE : Space.EMPTY;
            }
        }

        int time = 0;
        Set<Cheese> remainingCheeses = new HashSet<>();

        findCheeses(spaces, remainingCheeses);
        ventilate(spaces, 0, 0);

        while (!remainingCheeses.isEmpty()) {
            time++;

            List<Cheese> meltingCheeses = findMeltingCheeses(spaces, remainingCheeses);
            List<Position> emptyPositions = new ArrayList<>();

            for (Cheese meltingCheese : meltingCheeses) {
                remainingCheeses.remove(meltingCheese);
                emptyPositions.add(Position.from(meltingCheese));
            }

            for (Position position : emptyPositions) {
                ventilate(spaces, position.row, position.col);
            }
        }

        bw.write(time + "\n");
        bw.close();
    }

    private static void findCheeses(Space[][] spaces, Set<Cheese> remainingCheeses) {
        for (int row = 1; row < spaces.length - 1; row++) {
            for (int col = 1; col < spaces[0].length - 1; col++) {
                if (spaces[row][col] == Space.CHEESE) {
                    remainingCheeses.add(new Cheese(row, col));
                }
            }
        }
    }

    private static void ventilate(Space[][] spaces, int startRow, int startCol) {
        if (spaces[startRow][startCol] == Space.AIR) {
            return;
        }

        Queue<Position> queue = new ArrayDeque<>();
        queue.add(Position.of(startRow, startCol));
        spaces[startRow][startCol] = Space.AIR;

        while (!queue.isEmpty()) {
            Position position = queue.remove();

            for (int d = 0; d < dx.length; d++) {
                int nrow = position.row + dx[d];
                int ncol = position.col + dy[d];

                if (0 <= nrow && nrow < N && 0 <= ncol && ncol < M && spaces[nrow][ncol] == Space.EMPTY) {
                    spaces[nrow][ncol] = Space.AIR;
                    queue.add(Position.of(nrow, ncol));
                }
            }
        }
    }

    private static List<Cheese> findMeltingCheeses(Space[][] spaces, Set<Cheese> remainingCheeses) {
        List<Cheese> meltingCheeses = new ArrayList<>();

        for (Cheese cheese : remainingCheeses) {
            if (isMelting(spaces, cheese)) {
                meltingCheeses.add(cheese);
            }
        }

        return meltingCheeses;
    }

    private static boolean isMelting(Space[][] spaces, Cheese cheese) {
        int air = 0;

        for (int d = 0; d < dx.length; d++) {
            int nrow = cheese.row + dx[d];
            int ncol = cheese.col + dy[d];

            if (spaces[nrow][ncol] == Space.AIR) {
                air++;
            }
        }

        return air >= 2;
    }

    private static class Cheese {
        int row;
        int col;

        public Cheese(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Cheese cheese = (Cheese) o;
            return row == cheese.row && col == cheese.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Position {
        int row;
        int col;

        private Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public static Position from(Cheese cheese) {
            return new Position(cheese.row, cheese.col);
        }

        public static Position of(int row, int col) {
            return new Position(row, col);
        }
    }

    enum Space {
        EMPTY, AIR, CHEESE
    }
}