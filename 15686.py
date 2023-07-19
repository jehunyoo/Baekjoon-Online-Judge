from itertools import combinations

N, M = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(N)]
flat = []
for line in city:
    flat += line

distances = []
for i, house in enumerate(flat):
    if house == 1:
        r1, c1 = divmod(i , N)
        distance = []
        for j, chicken in enumerate(flat):
            if chicken == 2:
                r2, c2 = divmod(j , N)
                distance.append(abs(r1 - r2) + abs(c1 - c2))
        distances.append(distance)

city_chicken_distance = int(1e9)
how_many_chickens = len(distances[0])

for m in range(M, 0, -1):
    for comb in combinations(range(how_many_chickens), m):
        chicken_distance = 0
        for distance in distances:
            dist = int(1e9)
            for chicken in comb:
                dist = min(dist, distance[chicken])
            chicken_distance += dist
        city_chicken_distance = min(city_chicken_distance, chicken_distance)

print(city_chicken_distance)