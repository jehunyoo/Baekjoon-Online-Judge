import sys
from collections import defaultdict, deque
input = sys.stdin.readline

N, M, K, X = map(int, input().split())
graph = defaultdict(set)
distance = [int(1e9) for _ in range(N + 1)]

for _ in range(M):
    A, B = map(int, input().split())
    graph[A].add(B)

queue = deque([(X, 0)])
while queue:
    node, dist = queue.popleft()
    distance[node] = min(distance[node], dist)

    for neighbor in graph[node]:
        if dist + 1 < distance[neighbor] and dist + 1 <= K:
            queue.append((neighbor, dist + 1))

result = [city for city, dist in enumerate(distance) if dist == K]
if result:
    for city in result:
        print(city)
else:
    print(-1)