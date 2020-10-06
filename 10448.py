def is_eureka(K, tri):
    trik = list(filter(lambda x: x < K, tri))
    for a in trik:
        for b in trik:
            for c in trik:
                if a + b + c == K:
                    return 1
    else:
        return 0

T = int(input())
tri = [n * (n+1) / 2 for n in range(1, 45)] # less than 1000

for _ in range(T):
    K = int(input())
    print(is_eureka(K, tri))