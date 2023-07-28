import sys
from collections import deque
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    prev = list(map(int, input().split()))
    graph = [[False for _ in range(N + 1)] for _ in range(N + 1)]
    indegree = [0 for _ in range(N + 1)]
    for i in range(N):
        for j in range(i + 1, N):
            graph[prev[i]][prev[j]] = True
            indegree[prev[j]] += 1

    M = int(input())

    for m in range(M):
        A, B = map(int, input().split())
        if graph[A][B]:
            graph[A][B] = False
            graph[B][A] = True
            indegree[A] += 1
            indegree[B] -= 1
        else:
            graph[A][B] = True
            graph[B][A] = False
            indegree[A] -= 1
            indegree[B] += 1
    
    rank = []
    queue = deque()
    for team in range(1, N + 1):
        if indegree[team] == 0:
            queue.append(team)
    
    only = True
    cycle = False

    for _ in range(N):
        if len(queue) == 0:
            cycle = True
            break
        if len(queue) >= 2:
            only = False
            break
        team = queue.popleft()
        rank.append(team)

        for other, connected in enumerate(graph[team][1:], 1):
            if connected:
                indegree[other] -= 1
                if indegree[other] == 0:
                    queue.append(other)
        
    if not only:
        print("?")
    elif cycle:
        print("IMPOSSIBLE")
    else:
        for team in rank:
            print(team, end= " ")
        print()