import sys
input = sys.stdin.readline

N = int(input())
student = []
for _ in range(N):
    name, ko, en, math = input().split()
    student.append((name, int(ko), int(en), int(math)))

student = sorted(student, key=lambda x: (-x[1], x[2], -x[3], x[0]))
for name, _, _, _ in student:
    print(name)