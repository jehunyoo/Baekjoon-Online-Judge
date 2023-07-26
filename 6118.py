import sys
import heapq
from collections import defaultdict
input = sys.stdin.readline

N, M = map(int, input().split())
# 2D array requires too much memories / max N^2 >> max M (20000 * 20000) >> 50000
barns = defaultdict(list)
for _ in range(M):
    A, B = map(int, input().split())
    barns[A].append(B)
    barns[B].append(A)

distance = [int(1e9) for _ in range(N + 1)]
distance[1] = 0 # necessary
heap = []
heapq.heappush(heap, (0, 1))

while heap:
    dist, barn = heapq.heappop(heap)
    if distance[barn] < dist:
        continue
    for neighbor in barns[barn]:
        if dist + 1 < distance[neighbor]:
            distance[neighbor] = dist + 1
            heapq.heappush(heap, (dist + 1, neighbor))

far = -1 # cannot be 0
for barn, dist in enumerate(distance[1:], 1):
    if dist > far:
        where = barn
        far = dist
        count = 1
    elif dist == far:
        count += 1

print(where, far, count)