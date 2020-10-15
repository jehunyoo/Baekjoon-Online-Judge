N = int(input())
count = 0

for _ in range(N):
    word = input()
    if len(word) % 2 != 0:
        continue
    stack = []
    for ch in word:
        if stack == [] or stack[-1] != ch:
            stack.append(ch)
        else:
            stack.pop()
    if stack == []:
        count += 1
print(count)