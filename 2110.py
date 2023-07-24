import sys
input = sys.stdin.readline

N, C = map(int, input().split())
house = sorted([int(input()) for _ in range(N)])

left, right = 1, house[-1] - house[0] # min gap, max gap
gap = 0
while left <= right:
    mid = (left + right) // 2
    
    count = 1
    wifi = house[0]
    for i in range(1, len(house)):
        if house[i] - wifi >= mid:
            count += 1
            wifi = house[i]

    if count < C:
        right = mid - 1
    elif count >= C:
        gap = mid
        left = mid + 1

print(gap)