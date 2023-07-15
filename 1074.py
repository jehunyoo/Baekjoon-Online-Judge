# Both are answers
# def zed(N,R,C):
#     if N == 0:
#         return 0
#     return 2 * (R % 2) + (C % 2) + 4 * zed(N - 1, R // 2, C // 2)

# N, R, C = map(int, input().split())
# print(zed(N,R,C))

def zed(N, R, C):
    if N == 1:
        match R, C:
            case 0, 0:
                return 0
            case 0, 1:
                return 1
            case 1, 0:
                return 2
            case 1, 1:
                return 3
    else:
        top = 0 <= R < pow(2, N - 1)
        left = 0 <= C < pow(2, N - 1)
        
        if top and left:
            area = 0
        elif top and not left:
            area = 1
        elif not top and left:
            area = 2
        elif not top and not left:
            area = 3

        next_R = R % pow(2, N - 1)
        next_C = C % pow(2, N - 1)
        return pow(4, N - 1) * area + zed(N - 1, next_R, next_C)
                


N, R, C = map(int, input().split())
print(zed(N, R, C))