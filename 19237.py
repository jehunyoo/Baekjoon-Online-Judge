import sys

input = sys.stdin.readline

N, M, K = map(int, input().split())
ocean = [[None for _ in range(N)] for _ in range(N)]
sharks = {}
theta = {m: {} for m in range(1, M + 1)}
for i in range(N):
    l = list(map(int, input().split()))
    for j in range(N):
        if l[j] != 0:
            ocean[i][j] = (l[j], K)
            sharks[l[j]] = (i, j)
        else:
            ocean[i][j] = (0, 0)

for m, d in enumerate(map(int, input().split()), 1):
    sharks[m] = (*sharks[m], d)

for m in range(1, M + 1):
    for d in range(1, 4 + 1):
        theta[m][d] = list(map(int, input().split()))

delta = [None, (-1, 0), (1, 0), (0, -1), (0, 1)] # up down left right
alive = set(m for m in range(1, M + 1))

for time in range(1, 1001 + 1):
    for m in range(1, M + 1):
        if m not in alive:
            continue
        x, y, d = sharks[m]
        found = False
        for cell in [0, m]:
            for th in theta[m][d]:
                dx, dy = delta[th]
                nx = x + dx
                ny = y + dy
                if 0 <= nx < N and 0 <= ny < N and ocean[nx][ny][0] == cell:
                    sharks[m] = (nx, ny, th)
                    found = True
                    break
            if found:
                break

    for m1 in range(1, M + 1):
        for m2 in range(m1 + 1, M + 1):
            if sharks[m1] is not None and sharks[m2] is not None and sharks[m1][:2] == sharks[m2][:2]:
                sharks[m2] = None
                alive.remove(m2)
    
    for i in range(N):
        for j in range(N):
            if ocean[i][j][1] > 0:
                ocean[i][j] = (ocean[i][j][0], ocean[i][j][1] - 1)
                if ocean[i][j][1] == 0:
                    ocean[i][j] = (0, 0)

    for m in range(1, M + 1):
        if sharks[m] is None:
            continue
        x, y, _ = sharks[m]
        ocean[x][y] = (m, K)
    
    if len(alive) == 1 and 1 in alive:
        break

if time > 1000:
    print(-1)
else:
    print(time)