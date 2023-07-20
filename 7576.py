import sys
from collections import deque
input = sys.stdin.readline

M, N = map(int, input().strip().split())
tomato = [list(map(int, input().strip().split())) for _ in range(N)]
ripen = []

for row, line in enumerate(tomato):
    for col, cell in enumerate(line):
        if cell == 1:
            ripen.append((row, col))

delta = [(1, 0), (-1, 0), (0, 1), (0, -1)]

queue = deque(ripen)
while queue:
    x, y = queue.popleft()

    for dx, dy in delta:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < N and 0 <= ny < M and tomato[nx][ny] == 0:
            queue.append((nx, ny))
            tomato[nx][ny] = tomato[x][y] + 1

answer = 0
possible = True
for row, line in enumerate(tomato):
    for col, cell in enumerate(line):
        if cell == 0:
            possible = False
            break
        elif cell >= 1:
            answer = max(answer, cell)
    if not possible:
        break

if possible:
    print(answer - 1)
else:
    print(-1)