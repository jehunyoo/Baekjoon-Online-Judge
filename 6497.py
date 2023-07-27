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

while True:
    N, M = map(int, input().split())
    if N == M == 0:
        break
    edges = []
    cost = 0
    for _ in range(M):
        X, Y, Z = map(int, input().split())
        edges.append((Z, X, Y))
        cost += Z
    edges.sort()

    root = [i for i in range(N)]
    saved = 0
    count = 0
    for z, x, y in edges:
        if find(root, x) != find(root, y):
            union(root, x, y)
            saved += z
            count += 1
        if count == N - 1:
            break

    print(cost - saved)