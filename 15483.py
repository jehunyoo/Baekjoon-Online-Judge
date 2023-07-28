A = input()
B = input()

dp = [[0 for _ in range(len(B) + 1)] for _ in range(len(A) + 1)]

for i in range(1, len(A) + 1):
    dp[i][0] = i
for j in range(1, len(B) + 1):
    dp[0][j] = j

for row in range(1, len(A) + 1):
    for col in range(1, len(B) + 1):
        if A[row - 1] == B[col - 1]:
            dp[row][col] = dp[row - 1][col - 1]
        else:
            dp[row][col] = dp[row - 1][col - 1] + 1
        
        if dp[row][col - 1] + 1 < dp[row][col]:
            dp[row][col] = dp[row][col - 1] + 1
        if dp[row - 1][col] + 1 < dp[row][col]:
            dp[row][col] = dp[row - 1][col] + 1
        
print(dp[len(A)][len(B)])