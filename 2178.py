import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
maze = [list(map(int, list(input().strip()))) for _ in range(N)]

delta = [(1, 0), (-1, 0), (0, 1), (0, -1)]
queue = deque([(0, 0)])
while queue:
    x, y = queue.popleft()

    for dx, dy in delta:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < N and 0 <= ny < M and maze[nx][ny] == 1:
            queue.append((nx, ny))
            maze[nx][ny] = maze[x][y] + 1


print(maze[N - 1][M - 1])