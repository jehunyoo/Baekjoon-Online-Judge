import sys
import heapq
from collections import defaultdict
input = sys.stdin.readline

V, E = map(int, input().split())
K = int(input())
graph = defaultdict(dict)
for _ in range(E):
    u, v, w = map(int, input().split())
    if v in graph[u]:
        graph[u][v] = min(graph[u][v], w)
    else:
        graph[u][v] = w

INF = int(1e9)
distance = [INF if i != K else 0 for i in range(V + 1)]
heap = []
heapq.heappush(heap, (0, K))
while heap:
    dist, node = heapq.heappop(heap)
    if distance[node] < dist:
        continue
    for neighbor, d in graph[node].items():
        if dist + d < distance[neighbor]:
            distance[neighbor] = dist + d
            heapq.heappush(heap, (dist + d, neighbor))

for dist in distance[1:]:
    if dist == INF:
        print("INF")
    else:
        print(dist)