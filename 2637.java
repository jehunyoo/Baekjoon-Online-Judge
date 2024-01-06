import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Part[] parts = new Part[N + 1];

        for (int n = 1; n <= N; n++) {
            parts[n] = new Part(n);
        }

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]);
            int Y = Integer.parseInt(in[1]);
            int K = Integer.parseInt(in[2]);

            parts[X].components.put(parts[Y], K);
            parts[Y].inDegree++;
        }

        Queue<Part> queue = new ArrayDeque<>();
        queue.add(parts[N]);
        parts[N].count = 1;

        Set<Part> basics = new HashSet<>();

        while (!queue.isEmpty()) {
            Part part = queue.remove();

            for (Map.Entry<Part, Integer> entry : part.components.entrySet()) {
                Part component = entry.getKey();
                int count = entry.getValue();

                component.inDegree--;
                component.count += count * part.count;

                if (component.inDegree == 0) {
                    if (component.isBasic()) {
                        basics.add(component);
                    } else {
                        queue.add(component);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        basics.stream()
                .sorted(Comparator.comparingInt(Part::getId))
                .forEach(basic -> sb.append(basic.id).append(" ").append(basic.count).append("\n"));

        bw.write(sb.toString());
        bw.close();
    }

    private static class Part {
        int id;
        int inDegree = 0;
        int count = 0;
        Map<Part, Integer> components = new HashMap<>();

        public Part(int id) {
            this.id = id;
        }

        public boolean isBasic() {
            return components.isEmpty();
        }

        public int getId() {
            return id;
        }
    }

    /* ================================================== */

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Part[] parts = new Part[N + 1];

        for (int n = 1; n <= N; n++) {
            parts[n] = new Part(n);
        }

        for (int m = 0; m < M; m++) {
            String[] in = br.readLine().split(" ");
            int X = Integer.parseInt(in[0]);
            int Y = Integer.parseInt(in[1]);
            int K = Integer.parseInt(in[2]);

            parts[X].components.put(parts[Y], K);
        }

        Queue<PartContainer> queue = new ArrayDeque<>();
        queue.add(PartContainer.from(parts[N], 1));

        Map<Part, Integer> basics = new HashMap<>();

        while (!queue.isEmpty()) {
            PartContainer partContainer = queue.remove();

            Part part = partContainer.part;
            int countPart = partContainer.count;

            for (Map.Entry<Part, Integer> entry : part.components.entrySet()) {
                Part component = entry.getKey();
                int countComponent = entry.getValue();

                if (component.isBasic()) {
                    basics.merge(component, countPart * countComponent, Integer::sum);
                } else {
                    if (PartContainer.cache.containsKey(component.id) && PartContainer.cache.get(component.id).isActivated()) {
                        PartContainer.cache.get(component.id).count += countPart * countComponent;
                    } else {
                        queue.add(PartContainer.from(component, countPart * countComponent));
                    }
                }
            }

            partContainer.deactivate();
        }

        StringBuilder sb = new StringBuilder();

        basics.entrySet().stream()
                .sorted(Comparator.comparingInt(o -> o.getKey().id))
                        .forEach(o -> sb.append(o.getKey().id).append(" ").append(o.getValue()).append("\n"));

        bw.write(sb.toString());
        bw.close();
    }

    private static class PartContainer {

        static Map<Integer, PartContainer> cache = new HashMap<>();

        Part part;
        int count;

        private PartContainer(Part part, int count) {
            this.part = part;
            this.count = count;
        }

        public static PartContainer from(Part part, int count) {
            if (cache.containsKey(part.id) && cache.get(part.id).isActivated()) {
                return cache.get(part.id);
            }

            PartContainer partContainer = new PartContainer(part, count);
            cache.put(part.id, partContainer);

            return partContainer;
        }

        public void deactivate() {
            this.count = 0;
        }

        private boolean isActivated() {
            return this.count > 0;
        }
    }

    private static class Part {
        int id;
        Map<Part, Integer> components = new HashMap<>();

        public Part(int id) {
            this.id = id;
        }

        public boolean isBasic() {
            return components.isEmpty();
        }
    }
}