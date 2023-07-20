N = int(input())
grid = [input().split() for _ in range(N)]
teacher = []
for row, line in enumerate(grid):
    for col, cell in enumerate(line):
        if cell == 'T':
            teacher.append((row, col))

answer = "NO"
for i in range(N * N):
    for j in range(i + 1, N * N):
        for k in range(j + 1, N * N):
            
            

            x1, y1 = divmod(i, N)
            x2, y2 = divmod(j, N)
            x3, y3 = divmod(k, N)

            if not (grid[x1][y1] == grid[x2][y2] == grid[x3][y3] == 'X'):
                continue
            
            g = [line[:] for line in grid]
            g[x1][y1] = g[x2][y2] = g[x3][y3] = 'O'

            hide = True
            for x, y in teacher:
                for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                    var_x = x
                    var_y = y
                    while 0 <= var_x + dx < N and 0 <= var_y + dy < N:
                        var_x += dx
                        var_y += dy
                        match g[var_x][var_y]:
                            case 'O':
                                break
                            case 'S':
                                hide = False
                                break

            if hide:
                answer = "YES"

print(answer)