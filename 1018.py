# https://www.acmicpc.net/problem/1018
# 체스판 다시 칠하기


X, Y = 8, 8

def paint(chess):
    count = 0
    for i in range(Y):
        for j in range(X):
            if (i + j) % 2 == 0 and chess[i][j] != 'W':
                count += 1
            elif (i + j) % 2 == 1 and chess[i][j] != 'B':
                count += 1
    return min(count, (X * Y) - count)


N, M = map(int, input().split())
board = []
ans = X * Y

for _ in range(N):
    board.append(input())

for i in range(N - Y + 1):
    for j in range(M - X + 1):
        chess = [board[row][j:j + X] for row in range(i, i + Y)]
        ans = min(ans, paint(chess))

print(ans)