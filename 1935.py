N = int(input())
postfix = input()
stack = []
operands = []

for _ in range(N):
    num = int(input())
    operands.append(num)

for ch in postfix:
    if ch.isalpha():
        stack.append(operands[ord(ch) - ord('A')])
    else:
        right = stack.pop()
        left = stack.pop()
        stack.append(eval(f'{left}{ch}{right}'))

print(format(stack[0], '.2f'))