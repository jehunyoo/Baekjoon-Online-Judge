def fibonacci(n, dp):
    if n == 0:
        dp[0] = (1, 0)
        return dp[0]
    elif n == 1:
        dp[1] = (0, 1)
        return dp[1]
    else:
        if n in dp:
            return dp[n]
        else:
            f1 = fibonacci(n - 1, dp)
            f2 = fibonacci(n - 2, dp)
            dp[n] = (f1[0] + f2[0], f1[1] + f2[1])
            return dp[n]

T = int(input())
dp = {}
for _ in range(T):
    N = int(input())
    print(*fibonacci(N, dp))