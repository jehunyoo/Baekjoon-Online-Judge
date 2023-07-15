import sys
from collections import Counter
input = sys.stdin.readline

N = int(input())
nums = sorted([int(input()) for _ in range(N)])

counter = Counter(nums).most_common()
common = counter[0][1]
items = []
for item, count in counter:
    if count == common:
        items.append(item)

print(round(sum(nums) / N))
print(nums[N // 2])
if len(items) > 1:
    print(sorted(items)[1])
else:
    print(items[0])
print(nums[-1] - nums[0])