def eat(line):
    answer = 0
    count = 0
    before = None
    for candy in line:
        if (not before) or (before == candy):
            count += 1
            before = candy
        else:
            answer = max(answer, count)
            before = candy
            count = 1
    return max(answer, count)

N = int(input())
candies = [[candy for candy in input()] for _ in range(N)]
answer = 0

for row in range(N):
    answer = max(answer, eat(candies[row]))
for col in range(N):
    answer = max(answer, eat([candies[i][col] for i in range(N)]))

for row in range(N):
    for col in range(N):
        if col + 1 < N and candies[row][col] != candies[row][col + 1]:
            candies[row][col], candies[row][col + 1] = candies[row][col + 1], candies[row][col]
            answer = max(answer, eat(candies[row]))
            answer = max(answer, eat([candies[i][col] for i in range(N)]))
            answer = max(answer, eat([candies[i][col + 1] for i in range(N)]))
            candies[row][col], candies[row][col + 1] = candies[row][col + 1], candies[row][col]
            
        if row + 1 < N and candies[row][col] != candies[row + 1][col]:
            candies[row][col], candies[row + 1][col] = candies[row + 1][col], candies[row][col]
            answer = max(answer, eat(candies[row]))
            answer = max(answer, eat(candies[row + 1]))
            answer = max(answer, eat([candies[i][col] for i in range(N)]))
            candies[row][col], candies[row + 1][col] = candies[row + 1][col], candies[row][col]

print(answer)