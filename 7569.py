import sys
from collections import deque
input = sys.stdin.readline

M, N, H = map(int, input().strip().split())
tomato = [[list(map(int, input().strip().split())) for _ in range(N)] for _ in range(H)]
ripen = []

for height, plane in enumerate(tomato):
    for row, line in enumerate(plane):
        for col, cell in enumerate(line):
            if cell == 1:
                ripen.append((height, row, col))

delta = [(1, 0, 0), (-1, 0, 0), (0, 1, 0), (0, -1, 0), (0, 0, 1), (0, 0, -1)]

queue = deque(ripen)
while queue:
    z, x, y = queue.popleft()

    for dz, dx, dy in delta:
        nz = z + dz
        nx = x + dx
        ny = y + dy
        if 0 <= nz < H and 0 <= nx < N and 0 <= ny < M and tomato[nz][nx][ny] == 0:
            queue.append((nz, nx, ny))
            tomato[nz][nx][ny] = tomato[z][x][y] + 1

answer = 0
possible = True
for height, plane in enumerate(tomato):
    for row, line in enumerate(plane):
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