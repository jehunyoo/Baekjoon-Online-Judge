N, M, P = map(int, input().split())
graph = [0 for _ in range(M+1)]

for _ in range(N):
    favourite, hated = map(int, input().split())
    if not graph[hated]:
        graph[hated] = favourite

changes = 0
stack = [P]
visited = [0 for _ in range(M+1)]

while stack:
    channel = stack.pop()
    if not visited[channel]:
        visited[channel] = 1
        if graph[channel]:
            stack.append(graph[channel])
            changes += 1
    else:
        changes = -1
        break

print(changes)
