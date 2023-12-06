import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        for (int m = 0; m < M; m++) {
            in = br.readLine().split(" ");
            int A = Integer.parseInt(in[0]);
            int B = Integer.parseInt(in[1]);

            Student studentA = Student.from(A);
            Student studentB = Student.from(B);

            studentA.addNextStudent(studentB);
            studentB.inDegree++;
        }

        Queue<Student> queue = new ArrayDeque<>();

        for (int id = 1; id <= N; id++) {
            Student student = Student.from(id);

            if (student.inDegree == 0) {
                queue.add(student);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            Student student = queue.remove();

            sb.append(student.id).append(" ");

            for (Student nextStudent : student.nextStudents) {
                nextStudent.inDegree--;

                if (nextStudent.inDegree == 0) {
                    queue.add(nextStudent);
                }
            }
        }

        bw.write(sb.toString().strip() + "\n");
        bw.close();
    }

    private static class Student implements Comparable<Student> {

        private static Map<Integer, Student> cache = new HashMap<>();

        int id;
        int inDegree = 0;
        List<Student> nextStudents = new ArrayList<>();

        private Student(int id) {
            this.id = id;
        }

        public static Student from(int id) {
            if (cache.containsKey(id)) {
                return cache.get(id);
            }

            Student student = new Student(id);
            cache.put(id, student);

            return student;
        }

        public void addNextStudent(Student student) {
            nextStudents.add(student);
        }

        @Override
        public int compareTo(Student other) {
            return this.inDegree - other.inDegree;
        }
    }
}