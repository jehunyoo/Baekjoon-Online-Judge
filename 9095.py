T = int(input())
dp = [0 for _ in range(11)]
dp[1] = 1
dp[2] = 2
dp[3] = 4
for _ in range(T):
    N = int(input())
    if N <= 3:
        print(dp[N])
    elif dp[N] > 0:
        print(dp[N])
    else:
        for n in range(4, N + 1):
            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
        print(dp[N])