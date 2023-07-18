from collections import deque

N = int(input())
K = int(input())
apples = {tuple(map(int, input().split())): True for _ in range(K)}
L = int(input())
moves = [tuple(input().split()) for _ in range(L)][::-1]

snake = deque([(1, 1)])
next_cell = (1, 2)
direction = (0, 1)
time = 0
left = {(0, 1): (-1, 0), (1, 0): (0, 1), (0, -1): (1, 0), (-1, 0): (0, -1)}
right = {(0, 1): (1, 0), (1, 0): (0, -1), (0, -1): (-1, 0), (-1, 0): (0, 1)}

while next_cell not in snake and 1 <= next_cell[0] <= N and 1 <= next_cell[1] <= N:
    time += 1
    if next_cell in apples and apples[next_cell]:
        apples[next_cell] = False
        snake.append(next_cell)
    else:
        snake.append(next_cell)
        snake.popleft()

    if moves and time == int(moves[-1][0]):
        if moves[-1][1] == 'L':
            direction = left[direction]
        elif moves[-1][1] == 'D':
            direction = right[direction]
        moves.pop()
    next_cell = (next_cell[0] + direction[0], next_cell[1] + direction[1])

print(time + 1)