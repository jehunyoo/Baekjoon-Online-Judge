import sys
input = sys.stdin.readline

G = int(input())
P = int(input())

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

root = [g for g in range(G + 1)]
answer = 0
for plane in range(P):
    if (gate := find(root, int(input()))) == 0:
        for _ in range(plane + 1, P):
            input()
        break
    else:
        answer += 1
        union(root, gate, gate - 1)

print(answer)