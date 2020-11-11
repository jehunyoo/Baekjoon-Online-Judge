T = int(input())
for _ in range(T):
    N = int(input())
    students = [student - 1 for student in map(int, input().split())]

    cycle = 0
    stack = []
    visited = [0 for _ in range(N)]

    for student in range(N):
        if not visited[student]:
            stack.append(student)
            path = []
            while stack:
                s = stack.pop()
                if not visited[s]:
                    visited[s] = 1
                    stack.append(students[s])
                    path.append(s)
                elif s in path:
                    cycle += (len(path) - path.index(s))
                else:
                    break
    print(N - cycle)
