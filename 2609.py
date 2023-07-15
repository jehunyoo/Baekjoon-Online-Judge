A, B = map(int, input().split())

large, small = max(A, B), min(A, B)

while small != 0:
    remainder = large % small
    large = small
    small = remainder

gcd = large
lcm = (A * B) // gcd

print(gcd)
print(lcm)