// 이친수
#include <iostream>
#include <cstdio>
#include <algorithm>
#define MAXSIZE 91
using namespace std;

int main(void)
{
    int N;
    unsigned long result, end0[MAXSIZE], end1[MAXSIZE];
    scanf("%d", &N);
    fill(end0, end0+MAXSIZE, -1);
    fill(end1, end1+MAXSIZE, -1);

    end0[1] = 0;
    end1[1] = 1;

    for (int i=2; i<=N; i++) {
        end0[i] = end0[i - 1] + end1[i - 1];
        end1[i] = end0[i - 1];
    }
    result = end0[N] + end1[N];
    printf("%lu\n", result);

    return 0;
}