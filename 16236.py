from collections import deque

N = int(input())
ocean = [list(map(int, input().split())) for _ in range(N)]
fish = {}

for row, line in enumerate(ocean):
    for col, cell in enumerate(line):
        if cell == 9:
            shark = (row, col)
            ocean[row][col] = 0
        if 0 < cell < 9:
            fish[(row, col)] = cell

delta = [(-1, 0), (0, -1), (0, 1), (1, 0)]
time = 0
shark_size = 2
eat_count = 0
for _ in range(len(fish)):
    queue = deque([(0, shark[0], shark[1])]) # time (reset to 0), x, y
    visited = [[False for _ in range(N)] for _ in range(N)]
    edible = []
    while queue:
        t, x, y = queue.popleft()
        if edible and t > edible[0][0]:
            break
        if not visited[x][y]:
            visited[x][y] = True
            if 0 < ocean[x][y] < shark_size: # eadible
                edible.append((t, x, y))
            for dx, dy in delta:
                nx = x + dx
                ny = y + dy
                if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and ocean[nx][ny] <= shark_size:
                    queue.append((t + 1, nx, ny))
    
    if not edible:
        break
    else:
        t, x, y = min(edible)
        ocean[x][y] = 0
        shark = (x, y)
        eat_count += 1
        time += t
        if eat_count == shark_size:
            shark_size += 1
            eat_count = 0


print(time)