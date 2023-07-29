N = int(input())
INF = int(1e9)
dp = [INF for _ in range(N + 1)]

if N >= 3:
    dp[3] = 1
if N >= 5:
    dp[5] = 1

for n in range(6, N + 1):
    dp[n] = min(dp[n - 3], dp[n - 5]) + 1

if dp[N] >= INF:
    print(-1)
else:
    print(dp[N])