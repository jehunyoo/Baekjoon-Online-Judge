import sys
input = sys.stdin.readline
print = sys.stdout.write

M = int(input())
S = set()
for _ in range(M):
    command = input().split()
    if len(command) == 2:
        x = int(command[1])
    else:
        x = None

    match command[0]:
        case "add":
            S.add(x)
        case "remove":
            if x in S:
                S.remove(x)
        case "check":
            print(str(int(x in S)) + "\n")
        case "toggle":
            if x in S:
                S.remove(x)
            else:
                S.add(x)
        case "all":
            S = set(i for i in range(1, 21))
        case "empty":
            S = set()