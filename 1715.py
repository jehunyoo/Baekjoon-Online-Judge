import sys
import heapq
input = sys.stdin.readline

N = int(input())
heap = []
for _ in range(N):
    heapq.heappush(heap, int(input()))

total = 0
while len(heap) > 1:
    x = heapq.heappop(heap)
    y = heapq.heappop(heap)
    heapq.heappush(heap, x+ y)
    total += x + y

print(total)