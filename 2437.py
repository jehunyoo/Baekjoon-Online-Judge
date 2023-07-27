N = int(input())
weight = sorted(list(map(int, input().split())))

answer = 1
for w in weight:
    if answer < w:
        break
    answer += w

print(answer)