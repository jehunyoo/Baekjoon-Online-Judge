// 카드 2
#include <iostream>
#include <cstdio>
#include <queue>
using namespace std;

int main(void)
{
    int N, front = 0, rear = 0;
    queue<int> q;

    scanf("%d", &N);

    for (int i=0; i<N; i++)
        q.push(i + 1);

    while (q.size() != 1) {
        q.pop();
        q.push(q.front());
        q.pop();
    }

    printf("%d\n", q.front());

    return 0;
}