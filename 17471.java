import java.util.*;
import java.io.*;

public class Main {

    static City[] cities;
    static int answer ,sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] in = br.readLine().split(" ");
        cities = new City[N + 1];
        for(int i=0; i<N; i++) {
            cities[i + 1] = new City(i + 1, Integer.parseInt(in[i]));
            sum += cities[i + 1].population;
        }

        for(int i=1; i<=N; i++) {
            in = br.readLine().split(" ");
            int n = Integer.parseInt(in[0]);
            cities[i].neighbors = new City[n];
            for(int j=0; j<n; j++)
                cities[i].neighbors[j] = cities[Integer.parseInt(in[j + 1])];
        }

        answer = Integer.MAX_VALUE;
        combinationsWithRecursiveCheck(new ArrayList<City>(), 1, N / 2);

        answer = answer != Integer.MAX_VALUE? answer: -1;

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    // make combinations of cities
    public static void combinationsWithRecursiveCheck(List<City> combination, int r, int end) {
        if(r > end)
            return;

        int size = combination.size();
        int index = size > 0? combination.get(size - 1).id + 1: 1;

        for(int i=index; i<cities.length; i++) {
            combination.add(cities[i]);
            if(isValid(combination)) {
                calculateDiff(combination);
            }
            combinationsWithRecursiveCheck(combination, r + 1, end);
            combination.remove(cities[i]);
        }
    }

    // check if a combination of cities is valid by BFS
    public static boolean isValid(List<City> combination) {
        List<City> anotherCombination = new ArrayList<>();
        for(int i=1; i<cities.length; i++) {
            if(!combination.contains(cities[i]))
                anotherCombination.add(cities[i]);
        }

        return bfs(combination) && bfs(anotherCombination);
    }

    public static boolean bfs(List<City> combination) {
        boolean[] visited = new boolean[cities.length + 1];
        Queue<City> queue = new ArrayDeque<>();
        queue.add(combination.get(0));
        while(queue.size() > 0) {
            City city = queue.remove();
            if(!visited[city.id]) {
                visited[city.id] = true;
                for(City neighbor: city.neighbors) {
                    if(combination.contains(neighbor) && !visited[neighbor.id])
                        queue.add(neighbor);
                }
            }
        }

        for(City city: combination) {
            if(!visited[city.id])
                return false;
        }

        return true;
    }

    // calculate difference of populations between area
    public static void calculateDiff(List<City> combination) {
        int population = 0;
        for(City city: combination) {
            population += city.population;
        }
        answer = Math.min(answer, Math.abs((sum - population) - population));
    }

    static class City {
        int id;
        int population;
        City[] neighbors;

        public City(int id, int population, City[] neighbors) {
            this.id = id;
            this.population = population;
            this.neighbors = neighbors;
        }

        public City(int id, int population) {
            this(id, population, null);
        }

        @Override
        public String toString() {
            return String.format("%d", id);
        }
    }
}