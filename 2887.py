import sys
input = sys.stdin.readline

def find(root, x):
    if root[x] != x:
        root[x] = find(root, root[x])
    return root[x]

def union(root, x, y):
    x = find(root, x)
    y = find(root, y)
    if x < y:
        root[y] = x
    else:
        root[x] = y

N = int(input())
x, y, z = [], [], []
for planet in range(N):
    X, Y, Z = map(int, input().split())
    x.append((X, planet))
    y.append((Y, planet))
    z.append((Z, planet))

x.sort()
y.sort()
z.sort()

edges = []
for i in range(N - 1):
    edges.append((x[i + 1][0] - x[i][0], x[i][1], x[i + 1][1]))
    edges.append((y[i + 1][0] - y[i][0], y[i][1], y[i + 1][1]))
    edges.append((z[i + 1][0] - z[i][0], z[i][1], z[i + 1][1]))
edges.sort()

root = [i for i in range(N)]
cost = 0

for c, p1, p2 in edges:
    if find(root, p1) != find(root, p2):
        union(root, p1, p2)
        cost += c

print(cost)