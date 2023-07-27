# O(N^2)
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
taller = [set() for _ in range(N + 1)]
shorter = [set() for _ in range(N + 1)]
for _ in range(M):
    A, B = map(int, input().split())
    taller[A].add(B)
    shorter[B].add(A)

for me in range(1, N + 1):
    for other in shorter[me]:
        taller[other].update(taller[me])
    for other in taller[me]:
        shorter[other].update(shorter[me])

count = 0
for student in range(1, N + 1):
    if len(taller[student]) + len(shorter[student]) == N - 1:
        count += 1

print(count)

# floyd-warshall: O(N^3)
# import sys
# input = sys.stdin.readline
# INF = int(1e9)

# N, M = map(int, input().split())
# graph = [[INF if i != j else 0 for j in range(N + 1)] for i in range(N + 1)]
# for _ in range(M):
#     A, B = map(int, input().split())
#     graph[A][B] = 1

# for k in range(1, N + 1):
#     for i in range(1, N + 1):
#         for j in range(1, N + 1):
#             graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

# count = 0
# for i in range(1, N + 1):
#     c = 0
#     for j in range(1, N + 1):
#         if graph[i][j] != INF or graph[j][i] != INF:
#             c += 1
#     if c == N:
#         count += 1

# print(count)