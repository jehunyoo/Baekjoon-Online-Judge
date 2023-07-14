import sys
input = sys.stdin.readline

def find(x, roots):
    if x != roots[x]:
        roots[x] = find(roots[x], roots)
    return roots[x]

def union(x, y, roots):
    x = find(x, roots)
    y = find(y, roots)

    if x < y:
        roots[y] = x
    else:
        roots[x] = y
    return roots

N, M = map(int, input().split())
roads = sorted([tuple(map(int, input().split())) for _ in range(M)], key=lambda x: x[2])
roots = [x for x in range(N + 1)]

cost = 0
max_cost = 0

for x, y, c in roads:
    if find(x, roots) != find(y, roots):
        union(x, y, roots)
        cost += c
        max_cost = c

print(cost - max_cost)