N = int(input())
paper = [list(map(int, input().split())) for _ in range(N)]

def isAtom(paper: list[list[int]]):
    cell = paper[0][0]
    n = len(paper)
    for i in range(n):
        for j in range(n):
            if paper[i][j] != cell:
                return False
    return True

colors = [0, 0]
stack = [paper]
while stack:
    pp = stack.pop()
    if isAtom(pp):
        colors[pp[0][0]] += 1
    else:
        n = len(pp)

        div = []
        # division I
        div.append([[pp[i][j] for j in range(n // 2)] for i in range(n // 2)])
        # division II
        div.append([[pp[i][j] for j in range(n // 2, n)] for i in range(n // 2)])
        # division III
        div.append([[pp[i][j] for j in range(n // 2)] for i in range(n // 2, n)])
        # division IV
        div.append([[pp[i][j] for j in range(n // 2, n)] for i in range(n // 2, n)])

        for d in range(4):
            stack.append(div[d])

for color in colors:
    print(color)