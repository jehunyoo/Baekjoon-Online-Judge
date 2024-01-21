import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String[] names = br.readLine().split(" ");
        Map<String, Person> people = new HashMap<>();

        for (String name : names) {
            people.put(name, new Person(name));
        }

        int M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");

            Person descendant = people.get(in[0]);
            Person ancestor = people.get(in[1]);

            descendant.inDegree++;
            ancestor.nextDescendant.add(descendant);
        }

        List<Person> roots = new ArrayList<>();
        Queue<Person> queue = new ArrayDeque<>();
        Map<String, List<String>> descendants = new HashMap<>();

        Arrays.sort(names);
        for (String name : names) {
            Person person = people.get(name);
            if (person.inDegree == 0) {
                roots.add(person);
                queue.add(person);
            }
        }

        while (!queue.isEmpty()) {
            Person person = queue.remove();

            List<String> descendantNames = new ArrayList<>();

            for (Person descendant : person.nextDescendant) {
                descendant.inDegree--;

                if (descendant.inDegree == 0) {
                    descendantNames.add(descendant.name);
                    queue.add(descendant);
                }
            }

            descendants.put(person.name, descendantNames);
        }

        bw.write(roots.size() + "\n");
        for (Person root : roots) {
            bw.write(root.name + " ");
        }
        bw.newLine();
        for (String name : names) {
            List<String> descendantNames = descendants.get(name);
            Collections.sort(descendantNames);
            bw.write(name + " " + descendantNames.size() + " ");
            for (String descendantName : descendantNames) {
                bw.write(descendantName + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    private static class Person implements Comparable<Person> {

        private static Map<String, Person> cache = new HashMap<>();

        String name;
        int inDegree = 0;
        List<Person> nextDescendant = new ArrayList<>();

        public Person(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person other) {
            return this.name.compareTo(other.name);
        }
    }
}