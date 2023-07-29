N = 4
ocean = []
where = {}

for i in range(N):
    info = list(map(int, input().split()))
    l = []
    for j in range(N):
        A = info[2 * j]
        B = info[2 * j + 1] - 1
        l.append((A, B))
        where[A] = (i, j)
    ocean.append(l)

delta = [(-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1)]

def go(ocean, where, shark):
    x, y = shark
    # 생선 먹기
    fish = ocean[x][y][0]
    stheta = ocean[x][y][1]
    where[fish] = None
    # 먹었던 생선 위치로 상어 이동
    ocean[x][y] = (-1, stheta)

    # 1번부터 16번까지 생선에 대해
    for n in range(1, 17):
        if where[n] is None: # 먹었던 생선이면 skip
            continue
        i, j = where[n] # 생선 위치를 찾고
        theta = ocean[i][j][1] # 생선 방향을 찾는다.
        for dtheta in range(8):
            ntheta = (theta + dtheta) % 8 # 방향을 변경
            di, dj = delta[ntheta] # 새로운 방향에 대한 좌표 변화값
            ni = i + di # 새로운 좌표
            nj = j + dj
            if 0 <= ni < N and 0 <= nj < N and ocean[ni][nj][0] != -1: # 새로운 좌표가 범위 내이고 변경 위치에 상어가 없으면
                m = ocean[ni][nj][0] # 새로운 위치에 원래 있던 생선 번호 또는 빈 공간이면 0
                where[n] = (ni, nj) # 현재 생선의 위치를 새로운 위치로 변경
                where[m] = (i, j) # 새로운 위치에 있던 생선의 위치를 변경 / 빈 공간이면 아무 의미 없는 값 (where[0]을 할 일이 없음)
                ocean[ni][nj], ocean[i][j] = (ocean[i][j][0], ntheta), ocean[ni][nj] # 새로운 위치에 생선 번호와 새로운 방향을 저장, 원래 위치에 새로운 위치에 있던 정보 복사
                break # 한번 이동 했으므로 다음 생선으로 넘어가기
        else: # 만약 이동에 실패했다면 가장 마지막의 새로운 방향으로 방향 업데이트 -> 이걸 빼먹어서 시간 엄청 잡아먹음..
            ocean[i][j][1] = ntheta

    dx, dy = delta[stheta] # 상어의 방향에 대한 좌표 변화값
    ocean[x][y] = (0, 0) # 상어가 이동한 후에 생긴 빈 공간
    fish_next = 0 # 다음 스텝에서의 생선 번호의 합의 최댓값
    for step in range(1, N):
        nx = x + dx * step
        ny = y + dy * step
        if 0 <= nx < N and 0 <= ny < N and ocean[nx][ny][0] != 0: # 공간을 벗어나지 않고 새로운 위치가 빈 공간이 아니면
            fish_next = max(fish_next, go([l[:] for l in ocean], where.copy(), (nx, ny)))

    return fish + fish_next

print(go(ocean, where, (0, 0)))