import sys
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().split())
never_heard = set(input() for _ in range(N))
never_seen = set(input() for _ in range(M))

never = never_heard & never_seen
print(f"{len(never)}\n")
for name in sorted(never):
    print(name)