import sys
input = sys.stdin.readline

N = int(input())
seq = [int(input()) for _ in range(N)]

stack = []
op = []
index = 0
possible = True
n = 1 # 오름차순으로 입력할 때 사용
while index < N:
    if n == seq[index]: # n이 기준값과 같으면 push & pop
        op.append('+')
        op.append('-')
        n += 1
        index += 1
    elif n < seq[index]: # n이 기준값보다 작으면 push
        stack.append(n)
        op.append('+')
        n += 1
    elif n > seq[index]: # n이 기준값보다 크면
        if stack and stack[-1] == seq[index]: # stack[top]을 검사후 기준값과 같으면 pop
            stack.pop()
            op.append('-')
            index += 1
        else: # 그렇지 않은 경우에는 불가능한 경우
            possible = False
            break

if not possible:
    print("NO")
else:
    for o in op:
        print(o)