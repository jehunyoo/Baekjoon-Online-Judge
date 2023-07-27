import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
plan = list({city - 1 for city in map(int, input().split())})

root = [i for i in range(N)]

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

for start in range(N):
    for end in range(start + 1, N):
        if graph[start][end]:
            union(root, start, end)

possible = True
prev = find(root, plan[0])
for city in plan[1:]:
    if prev != (f := find(root, city)):
        possible = False
    prev = f

if possible:
    print("YES")
else:
    print("NO")