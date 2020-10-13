import sys

input=sys.stdin.readline

N = int(input())
stack = []

for _ in range(N):
    command = input()[:-1]
    if command == "top":
        if stack != []:
            print(stack[-1])
        else:
            print(-1)
    elif command == "pop":
        if stack != []:
            print(stack.pop())
        else:
            print(-1)
    elif command == "size":
        print(len(stack))
    elif command == "empty":
        print(int(stack == []))
    else:
        item = int(command.split()[1])
        stack.append(item)