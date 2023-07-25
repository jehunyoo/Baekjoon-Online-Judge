import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] S = br.readLine().split(" ");
        int[] swch = new int[N];
        for(int i=0; i<N; i++)
            swch[i] = Integer.parseInt(S[i]);


        int M = Integer.parseInt(br.readLine());
        String[] command;

        for(int m=0; m<M; m++) {
            command = br.readLine().split(" ");

            if(command[0].equals("1")) {
                int mul = Integer.parseInt(command[1]);
                for(int i=mul; i<=N; i+=mul)
                    swch[i - 1] ^= 1;
            } else {
                int mid = Integer.parseInt(command[1]) - 1;
                int i=0;
                while(0 <= mid - i && mid + i < N && swch[mid + i] == swch[mid - i]) {
                    swch[mid + i] ^= 1;
                    swch[mid - i] ^= 1;
                    i++;
                }
                swch[mid] ^= 1;
            }
        }

        for(int i=0; i<N; i++) {
            bw.write(swch[i] + " ");
            if(i % 20 == 19)
                bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}