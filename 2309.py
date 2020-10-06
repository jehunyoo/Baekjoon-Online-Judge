dwarves = [int(input()) for _ in range(9)]
tot = sum(dwarves)
breaker = False
for i1, spy1 in enumerate(dwarves):
    for i2, spy2 in enumerate(dwarves):
        if i1 != i2 and tot - (spy1 + spy2) == 100:
            dwarves.remove(spy1)
            dwarves.remove(spy2)
            dwarves.sort()
            breaker = True
            break
    if breaker:
        break
for dwarf in dwarves:
    print(dwarf)