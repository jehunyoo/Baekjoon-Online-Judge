from collections import deque

N, M = map(int, input().split())
original = [list(map(int, input().split())) for _ in range(N)]
virus = []
wall_count = 0
for i, line in enumerate(original):
    for j, cell in enumerate(line):
        if cell == 2:
            virus.append((i, j))
        if cell == 1:
            wall_count += 1

virus_count = N * M

for i in range(N * M):
    for j in range(i + 1, N * M):
        for k in range(j + 1, N * M):
            valid = True
            for wall in [i, j, k]:
                x, y = wall // M, wall % M
                if original[x][y] != 0:
                    valid = False

            if not valid:
                continue
            else:
                area = [line[:] for line in original]
                visited = [[False for _ in range(M)] for _ in range(N)]
                for wall in [i, j, k]:
                    x, y = wall // M, wall % M
                    area[x][y] = 1

                queue = deque(virus)
                count = 0
                while queue:
                    x, y = queue.popleft()
                    if not visited[x][y]:
                        visited[x][y] = True
                        count += 1
                        if x - 1 >= 0 and not visited[x - 1][y] and area[x - 1][y] == 0:
                            queue.append((x - 1, y))
                        if y - 1 >= 0 and not visited[x][y - 1] and area[x][y - 1] == 0:
                            queue.append((x, y - 1))
                        if x + 1 < N and not visited[x + 1][y] and area[x + 1][y] == 0:
                            queue.append((x + 1, y))
                        if y + 1 < M and not visited[x][y + 1] and area[x][y + 1] == 0:
                            queue.append((x, y + 1))

                virus_count = min(virus_count, count)

print(M * N - (wall_count + virus_count + 3))