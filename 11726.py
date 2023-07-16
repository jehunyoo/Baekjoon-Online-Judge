N = int(input())
dp = [0 for _ in range(N + 1)]

dp[1] = 1
if N > 1:
    dp[2] = 2

for n in range(3, N + 1):
    dp[n] = dp[n - 1] + dp[n - 2]

print(dp[N] % 10007)