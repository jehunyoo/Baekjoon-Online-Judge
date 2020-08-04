# https://www.acmicpc.net/problem/2503
# 숫자 야구


def baseball(num1, num2):
    num1, num2 = str(num1), str(num2)
    strike, ball = 0, 0

    for n1, n2 in zip(num1, num2):
        if n1 == n2:
            strike += 1
        elif n1 in num2:
            ball += 1
        else:
            continue
    return strike, ball


N = int(input())
cases = []
pool = [100*i+10*j+k for i in range(1, 10) for j in range(1, 10)
        for k in range(1, 10) if (i != j and j != k and k != i)]

for _ in range(N):
    case = [int(e) for e in input().split()]
    cases.append(case)

for case in cases:
    tmp = []
    for num in pool:
        if baseball(case[0], num) == (case[1], case[2]):
            tmp.append(num)
    pool = tmp[:]

print(len(pool))