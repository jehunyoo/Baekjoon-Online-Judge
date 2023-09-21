import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Member[] members = new Member[N];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            members[i] = new Member(in[1], Integer.parseInt(in[0]));
        }

        Arrays.stream(members)
                .sorted(Comparator.comparing(Member::getAge))
                .forEach((m) -> {
                    try {
                        bw.write(m + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        bw.close();
    }

    static class Member {
        String name;
        int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return age + " " + name;
        }
    }
}