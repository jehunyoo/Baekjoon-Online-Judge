N = int(input())
P = sorted(map(int, input().split()))

sum = 0
for i, p in enumerate(P):
    sum += p * (N - i)

print(sum)