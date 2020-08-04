# https://www.acmicpc.net/problem/3085
# 사탕게임


def eat(bom, N, option, where=None): # FIXME : only for continuous candies!!!
    if option == "all":
        ans = 0
        for n in range(N):
            candies1 = [bom[n][k] for k in range(N)]
            candies2 = [bom[k][n] for k in range(N)]
            
            candy1 = max(list(map(candies1.count, candies1)))
            candy2 = max(list(map(candies2.count, candies2)))

            ans = max(ans, candy1, candy2)

        return ans
    
    elif option == "col":
        candies1 = [bom[k][where] for k in range(N)]
        candies2 = [bom[k][where+1] for k in range(N)]
    elif option == "row":
        candies1 = [bom[where][k] for k in range(N)]
        candies2 = [bom[where+1][k] for k in range(N)]

    candy1 = max(list(map(candies1.count, candies1)))
    candy2 = max(list(map(candies2.count, candies2)))
    return max(candy1, candy2)
    
def change(bom, N, i, j):
    tmp = bom[:]
    # FIXME: 'str' object does not support item assignment
    # FIXED: replace input().split() to lst(input()) // split by each characters
    bom[i][j], bom[i][j+1] = bom[i][j+1], bom[i][j] # change right and left
    candy1 = eat(bom, N, option="col", where=j)
    bom = tmp[:]
    bom[i][j], bom[i+1][j] = bom[i+1][j], bom[i][j] # change up and down
    candy2 = eat(bom, N, option="row", where=i)
    return max(candy1, candy2)


N = int(input())
bom = []

for _ in range(N):
    bom.append(list(input())) # FIXED

ans = eat(bom, N, option="all")
print(ans)
# print(bom)

for i in range(N-1):
    for j in range(N-1):
        ans = max(ans, change(bom, N, i, j))

print(ans)