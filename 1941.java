import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int count = 0;
    private static String[][] graph = new String[5][5];
    private static List<Integer> members = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            graph[i] = br.readLine().split("");
        }

        combine(0);

        bw.write(count + "\n");
        bw.close();
    }

    private static void combine(int where) {
        if (members.size() == 7) {
            if (countS() >= 4 && connected()) {
                count++;
            }

            return;
        }

        for (int student = where; student < 25; student++) {
            members.add(student);
            combine(student + 1);
            members.remove(members.size() - 1);
        }
    }

    private static int countS() {
        int countS = 0;

        for (int member : members) {
            int x = member / 5;
            int y = member % 5;

            if (graph[x][y].equals("S")) {
                countS++;
            }
        }

        return countS;
    }

    private static boolean connected() {
        boolean[] visited = new boolean[25];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[members.get(0)] = true;
        queue.add(members.get(0));

        while (!queue.isEmpty()) {
            int member = queue.remove();

            if (member + 5 < 25 && !visited[member + 5] && members.contains(member + 5)) {
                queue.add(member + 5);
                visited[member + 5] = true;
            }

            if (member - 5 >= 0 && !visited[member - 5] && members.contains(member - 5)) {
                queue.add(member - 5);
                visited[member - 5] = true;
            }

            if (member % 5 < 4 && member + 1 < 25 && !visited[member + 1] && members.contains(member + 1)) {
                queue.add(member + 1);
                visited[member + 1] = true;
            }

            if (member % 5 > 0 && member - 1 >= 0 && !visited[member - 1] && members.contains(member - 1)) {
                queue.add(member - 1);
                visited[member - 1] = true;
            }
        }

        for (int member : members) {
            if (!visited[member]) {
                return false;
            }
        }

        return true;
    }
}