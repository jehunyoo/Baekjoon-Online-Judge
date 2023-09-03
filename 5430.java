import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            String P = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split("]|,|\\[");

            int left = 1, right = N;
            boolean reversed = false;
            boolean error = false;

            for (char p : P.toCharArray()) {
                switch (p) {
                    case 'R':
                        int temp = right;
                        right = left;
                        left = temp;
                        reversed = !reversed;
                        break;
                    case 'D':
                        if (!reversed) {
                            if(left > right)
                                error = true;
                            left++;
                        } else {
                            if(left < right)
                                error = true;
                            left--;
                        }
                        break;
                }

                if (error)
                    break;
            }

            if (!error)
                bw.write("[" + convertArrayToString(arr, left, right, reversed) + "]\n");
            else
                bw.write("error\n");
        }

        bw.flush();
        bw.close();
    }

    public static String convertArrayToString(String[] arr, int left, int right, boolean reversed) {
        String[] result;
        if (!reversed) {
            result = new String[right - left + 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = arr[left + i];
            }
        } else {
            result = new String[left - right + 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = arr[left - i];
            }
        }
            return String.join(",", result);
    }
}