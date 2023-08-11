N = int(input())
M = int(input())

graph = [[False for _ in range(N + 1)] for _ in range(N + 1)]

for _ in range(M):
    A, B = map(int, input().split())
    graph[A][B] = graph[B][A] = True

count = 0
stack = [1]
visited = [False for _ in range(N + 1)]
while stack:
    computer = stack.pop()
    if not visited[computer]:
        visited[computer] = True
        count += 1

        for com, connected in enumerate(graph[computer]):
            if connected and not visited[com]:
                stack.append(com)

print(count - 1)