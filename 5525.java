import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();

        int count = 0;
        int len = 2 * N + 1;
        boolean matched = false;

        for (int start = 0; start + len <= M; ) {
//            System.out.println("start = " + start + " count = " + count + " " + len);
            if (!matched && S[start] == 'I') {
                /* when matched = false */
                for (int i = start + 1; i < start + len; i++) {
                    if ((i - start) % 2 == 0 && S[i] == 'I') {
                        ;
                    } else if ((i - start) % 2 == 1 && S[i] == 'O') {
                        ;
                    } else {
                        start = i;
                        break;
                    }

                    if (i == start + len - 1) {
                        count++;
                        start += 2;
                        matched = true;
                        break;
                    }
                }
            } else if (matched) {
                /* when matched = true */
                if (S[start + len - 2] == 'O' && S[start + len - 1] == 'I') {
                    count++;
                    start += 2;
                } else {
                    start += len - 2;
                    matched = false;
                }
            } else {
                start++;
            }
        }

        bw.write(count + "\n");
        bw.close();
    }
}