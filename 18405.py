from collections import deque

N, K = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
S, X, Y = map(int, input().split())

virus = []
for row, line in enumerate(board):
    for col, cell in enumerate(line):
        if 1 <= cell <= K:
            virus.append((row, col, cell, 0))

visited = [[False for _ in range(N)] for _ in range(N)]
queue = deque(sorted(virus, key=lambda x: (x[2], x[0], x[1])))
while queue:
    x, y, code, time = queue.popleft()
    if x is None:
        continue
    if not visited[x][y]:
        visited[x][y] = True
        if board[x][y] == 0:
            board[x][y] = code
        if x - 1 >= 0 and not visited[x - 1][y] and board[x - 1][y] == 0 and time + 1 <= S:
            queue.append((x - 1, y, code, time + 1))
        if y - 1 >= 0 and not visited[x][y - 1] and board[x][y - 1] == 0 and time + 1 <= S:
            queue.append((x, y - 1, code, time + 1))
        if x + 1 < N and not visited[x + 1][y] and board[x + 1][y] == 0 and time + 1 <= S:
            queue.append((x + 1, y, code, time + 1))
        if y + 1 < N and not visited[x][y + 1] and board[x][y + 1] == 0 and time + 1 <= S:
            queue.append((x, y + 1, code, time + 1))

print(board[X - 1][Y - 1])