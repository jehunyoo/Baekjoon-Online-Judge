# 프린터 큐

T = int(input())
orders = []

for _ in range(T):
    N, M = map(int, input().split())
    queue = list(map(int, input().split()))

    order = 0
    urgent = max(queue)
    while True:
        if queue[0] == urgent:
            order += 1
            if M == 0:
                break
            del queue[0]
            urgent = max(queue)
            M -= 1
        else:
            queue.append(queue[0])
            del queue[0]
            if M == 0:
                M = len(queue) - 1
            else:
                M -= 1
    orders.append(order)

for _, val in enumerate(orders):
    print(val)