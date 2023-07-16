S = input()

count = [0, 0]
now = S[0]
count[int(now)] = 1

for s in S[1:]:
    if now != s:
        count[int(s)] += 1
    now = s

print(min(count))