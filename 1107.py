N = int(input())
M = int(input())
button = {btn for btn in range(10)}

if M != 0:
    button -= {btn for btn in map(int, input().split())}

answer = abs(N - 100)

stack = []
stack.append("")
while stack:
    s = stack.pop()
    for btn in button:
        new_s = s + str(btn)
        answer = min(answer, len(new_s) + abs(int(new_s) - N))
        if len(new_s) < 6:
            stack.append(new_s)

print(answer)