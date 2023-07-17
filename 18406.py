N = list(map(int, list(input())))
left, right = N[:len(N)//2], N[len(N)//2:]
if sum(left) == sum(right):
    print("LUCKY")
else:
    print("READY")