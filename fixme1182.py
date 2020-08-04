# https://www.acmicpc.net/problem/1182
# 부분수열의 합
# FIXME memory excess

# ----- tracemalloc -----
import tracemalloc
tracemalloc.start()
# ----- tracemalloc -----

def subseq(N, depth=0, center=[]):
    if depth == 0:
        left = [True]
        right = [False]
    elif depth < N:
        left = center + [True]
        right = center + [False]
    else:
        return center
    # print(depth ,left, right)
    depth += 1
    lcenter = subseq(N, depth, left)
    rcenter = subseq(N, depth, right)
    lrcenter = lcenter + rcenter
    if depth == 1:
        return [lrcenter[N*i:N*i+N] for i in range(2**N)]
    else:
        return lrcenter

N, S = map(int, input().split())
seq = list(map(int, input().split()))

subidx = subseq(N)
subidx.pop()
ans = 0
for sub in subidx:
    s = 0
    for idx, val in enumerate(sub):
        if val == True:
            s += seq[idx]
    if s == S:
        ans += 1
print(ans)

# ----- tracemalloc -----
snapshot = tracemalloc.take_snapshot()
top_stats = snapshot.statistics('lineno')

for stat in top_stats[:10]:
    print(stat)
# ----- tracemalloc -----