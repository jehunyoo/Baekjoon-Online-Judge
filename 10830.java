import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        long B = Long.parseLong(in[1]);

        int[][] arr = new int[N][N];
        int divisor = 1000;

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(in[j]) % divisor;
            }
        }

        Matrix matrix = new Matrix(arr);

        bw.write(matrix.power(B) + "\n");
        bw.close();
    }

    private static class Matrix {

        private int[][] matrix;
        private static int divisor = 1000;

        public Matrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public Matrix multiply(Matrix other) {
            if (this.matrix[0].length != other.matrix.length) {
                throw new IllegalArgumentException();
            }

            int[][] result = new int[this.matrix.length][other.matrix[0].length];

            for (int row = 0; row < this.matrix.length; row++) {
                for (int col = 0; col < other.matrix[0].length; col++) {
                    for (int k = 0; k < this.matrix[0].length; k++) {
                        result[row][col] += this.matrix[row][k] * other.matrix[k][col] % divisor;
                        result[row][col] %= divisor;
                    }
                }
            }

            return new Matrix(result);
        }

        public Matrix power(long exponent) {
            if (exponent == 0) {
                return getIdentity();
            } else if (exponent == 1) {
                return this;
            }

            Matrix halfPowered = power(exponent / 2);

            if (exponent % 2 == 0) {
                return halfPowered.multiply(halfPowered);
            } else {
                return this.multiply(halfPowered).multiply(halfPowered);
            }
        }

        private Matrix getIdentity() {
            int[][] identity = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                Arrays.fill(identity[i], 1);
            }

            return new Matrix(identity);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    sb.append(matrix[row][col]).append(" ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }
}