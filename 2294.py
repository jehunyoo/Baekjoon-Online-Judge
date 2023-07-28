import sys
input = sys.stdin.readline
INF = int(1e9)

N, K = map(int, input().split())
coins = set()
for _ in range(N):
    coins.add(int(input()))

dp = [INF for _ in range(K + 1)]
for coin in coins:
    if coin <= K:
        dp[coin] = 1

for k in range(1, K + 1):
    for coin in coins:
        if coin < k:
            dp[k] = min(dp[k], dp[k - coin] + 1)

if dp[K] != INF:
    print(dp[K])
else:
    print(-1)