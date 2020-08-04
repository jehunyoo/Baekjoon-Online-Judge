# https://www.acmicpc.net/problem/1978
# 소수 찾기

def isPrime(n):
    if n == 2:
        return True
    elif (n == 1) or (n % 2 == 0):
        return False
    for i in range(3, n, 2):
        if n % i == 0:
            return False
    else:
        return True

N = int(input())
num = list(map(int, input().split()))
count = 0

for n in num:
    if isPrime(n):
        count += 1
print(count)