import sys
input = sys.stdin.readline

N, P = map(int, input().split())
stacks = [[] for _ in range(6)]
count = 0

for _ in range(N):
    line, fret = map(int, input().split())
    line -= 1
    
    while stacks[line] != [] and stacks[line][-1] > fret:
        stacks[line].pop()
        count += 1
    if stacks[line] == [] or stacks[line][-1] < fret:
        stacks[line].append(fret)
        count += 1

print(count)