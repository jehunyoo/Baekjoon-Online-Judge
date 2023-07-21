from collections import Counter

N = int(input())
X = list(map(int, input().split()))
dp = {}
total = 0
counter = Counter(set(X))
for key in sorted(counter.keys()):
    dp[key] = total
    total += counter[key]

for x in X:
    print(dp[x], end=' ')
print()