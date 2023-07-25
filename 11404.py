import sys
input = sys.stdin.readline

N = int(input())
M = int(input())

graph = [[int(1e9) for _ in range(N + 1)] for _ in range(N + 1)]
for i in range(1, N + 1):
    graph[i][i] = 0

for _ in range(M):
    A, B, C = map(int, input().split())
    graph[A][B] = min(graph[A][B], C)

for k in range(1, N + 1):
    for a in range(1, N + 1):
        for b in range(1, N + 1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

for i in range(1, N + 1):
    for j in range(1, N + 1):
        if graph[i][j] < int(1e9):
            print(graph[i][j], end=' ')
        else:
            print(0, end=' ')
    print()