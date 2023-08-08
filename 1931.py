import sys
input = sys.stdin.readline

N = int(input())
info = sorted([tuple(map(int, input().split())) for _ in range(N)], key=lambda x: (x[1], x[0]))

count = 0
for start, end in info:
    if not count or last <= start:
        count += 1
        last = end
        
print(count)