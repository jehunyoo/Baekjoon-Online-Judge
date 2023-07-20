N = int(input())
seq = list(map(int, input().split()))
op = list(map(int, input().split())) # + - * /

answer_max, answer_min = -int(1e9), int(1e9)
stack = [(seq[0], o, 1, op[:]) for o, c in enumerate(op) if c != 0]

while stack:
    value, o, n, count = stack.pop()
    if n < N:
        if o == 0:
            value += seq[n]
        elif o == 1:
            value -= seq[n]
        elif o == 2:
            value *= seq[n]
        elif o == 3:
            if value >= 0:
                value //= seq[n]
            else:
                value = -((-value) // seq[n])
        
        count[o] -= 1
        
        for i, c in enumerate(count):
            if c != 0:
                stack.append((value, i, n + 1, count[:]))
        
        if n == N - 1:
            answer_max = max(answer_max, value)
            answer_min = min(answer_min, value)

print(answer_max)
print(answer_min)