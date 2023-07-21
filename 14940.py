from collections import deque

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
distance = [[None for _ in range(M)] for _ in range(N)]
start = None
for row, line in enumerate(graph):
    for col, cell in enumerate(line):
        if cell == 2:
            start = (row, col, 0)
            break
    if start:
        break

delta = [(1, 0), (-1, 0), (0, 1), (0, -1)]

queue = deque([start])
while queue:
    x, y, dist = queue.popleft()
    distance[x][y] = dist
    for dx, dy in delta:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 1:
            graph[nx][ny] = 0
            queue.append((nx, ny, dist + 1))

for row, line in enumerate(distance):
    for col, cell in enumerate(line):
        if cell == None:
            if graph[row][col] == 0:
                distance[row][col] = 0
            elif graph[row][col] == 1:
                distance[row][col] = -1

for row, line in enumerate(distance):
    for col, cell in enumerate(line):
        print(distance[row][col], end=' ')
    print()