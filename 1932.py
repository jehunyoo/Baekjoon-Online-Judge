N = int(input())
tri = [list(map(int, input().split())) for _ in range(N)]
dp = [0 for _ in range(N)]
dp_next = dp[:]
dp[0] = tri[0][0]

for row, line in enumerate(tri[1:], 1):
    for col, num in enumerate(line):
        max_num = 0
        if col - 1 >= 0:
            max_num = max(max_num, dp[col - 1])
        dp_next[col] = max(max_num, dp[col]) + tri[row][col]
    
    dp = dp_next[:]

print(max(dp))