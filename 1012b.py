T = int(input())

for _ in range(T):
    M, N, K = map(int, input().split())
    ground = [[0 for _ in range(N)] for _ in range(M)]

    for _ in range(K):
        X, Y = map(int, input().split())
        ground[X][Y] = 1

    answer = 0
    queue = []
    visited = [[0 for _ in range(N)] for _ in range(M)]

    for m in range(M):
        for n in range(N):
            if ground[m][n] and not visited[m][n]:
                queue.append((m, n))
                while queue:
                    x, y = queue[0]
                    del queue[0]
                    if not visited[x][y]:
                        visited[x][y] = 1
                        if 0 <= x-1 and ground[x-1][y] and not visited[x-1][y]:
                            queue.append((x-1, y))
                        if 0 <= y-1 and ground[x][y-1] and not visited[x][y-1]:
                            queue.append((x, y-1))
                        if x+1 < M and ground[x+1][y] and not visited[x+1][y]:
                            queue.append((x+1, y))
                        if y+1 < N and ground[x][y+1] and not visited[x][y+1]:
                            queue.append((x, y+1))
                answer += 1
    print(answer)
