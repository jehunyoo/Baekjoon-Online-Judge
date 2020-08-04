def deSum(m):
    M = m

    while m > 0:
        M += m % 10
        m //= 10
    return M


if __name__ == "__main__":

    N = int(input())

    for m in range(1, N):
        if N == deSum(m):
            print(m)
            break
    else:
        print(0)