import sys
from collections import defaultdict
from itertools import combinations
input = sys.stdin.readline
print = sys.stdout.write

for _ in range(int(input())):
    N = int(input())
    mbtis = defaultdict(int)
    dist = 12

    for mbti in input().strip().split():
        mbtis[mbti] += 1
        if mbtis[mbti] == 3:
            dist = 0
            break
    
    if dist > 0:
        for mbti, count in mbtis.items():
            if count == 2:
                for other in mbtis:
                    if other != mbti:
                        dist = min(dist, 2 * len(set(other) - set(mbti)))

    if dist > 1:
        for comb in combinations(mbtis, 3):
            dist = min(dist, len(set(comb[0]) - set(comb[1])) + len(set(comb[1]) - set(comb[2])) + len(set(comb[2]) - set(comb[0])))
    
    print(f"{dist}\n")