import sys
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().split())
book = {}
page = {}
for i in range(1, N + 1):
    name = input().strip()
    book[name] = i
    page[i] = name

for _ in range(M):
    query = input().strip()
    if query.isalpha():
        print(f"{book[query]}\n")
    elif query.isnumeric():
        print(f"{page[int(query)]}\n")