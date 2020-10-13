T = int(input())

for _ in range(T):
    ps = input()
    stack = []
    for char in ps:
        if char == "(":
            stack.append(char)
        else:
            if stack == []:
                print("NO")
                break
            else:
                stack.pop()
    else:
        if stack == []:
            print("YES")
        else:
            print("NO")