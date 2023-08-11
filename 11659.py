import sys
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().split())
nums = list(map(int, input().split()))
dp = [0 for _ in range(N)]
for _ in range(M):
    i, j = map(int, input().split())
    if not dp[j-1]:
        for x in range(j):
            if dp[x] == 0:
                dp[x] = dp[x - 1] + nums[x]
    print(f"{dp[j-1] - dp[i-1] + nums[i-1]}\n")