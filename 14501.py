N = int(input())
T, P = [], []
for _ in range(N):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)

dp = [0 for _ in range(N + 1)] # i번째 날부터 마지막 날까지 가능한 최대 이익 / top-down approach
max_profit = 0

for day in range(N - 1, -1, -1):
    time = day + T[day]
    if time <= N:
        max_profit = dp[day] = max(max_profit, P[day] + dp[time])
    else:
        dp[day] = max_profit

print(max_profit)