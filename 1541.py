equations = input().split('-')

stack = []
for eq in equations:
    subsum = 0
    for num in eq.split('+'):
        subsum += int(num)
    stack.append(subsum)

print(stack[0] - sum(stack[1:]))