N = int(input())

for M in range(1, N):
    if sum(map(int, str(M))) + M == N:
        print(M)
        break
else:
    print(0)