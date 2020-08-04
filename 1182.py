# https://www.acmicpc.net/problem/1182
# 부분수열의 합

# ----- tracemalloc -----
# import tracemalloc
# tracemalloc.start()
# ----- tracemalloc -----

def sumSeq(seq, N, S, depth=0, s=0):
    if depth < N:
        left = s + seq[0]
        right = s
    else:
        return 1 if s == S else 0
    depth += 1
    lcount = sumSeq(seq[1:], N, S, depth, left)
    rcount = sumSeq(seq[1:], N, S, depth, right)
    return lcount + rcount
    

N, S = map(int, input().split())
seq = list(map(int, input().split()))

ans = sumSeq(seq, N, S)
if S == 0: # sum of [False, False, ... , False] is 0
    ans -= 1
print(ans)

# ----- tracemalloc -----
# snapshot = tracemalloc.take_snapshot()
# top_stats = snapshot.statistics('lineno')
#
# for stat in top_stats[:10]:
#    print(stat)
# ----- tracemalloc -----