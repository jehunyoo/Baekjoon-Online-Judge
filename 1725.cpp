// 히스토그램
#include <iostream>
#include <cstdio>
#include <stack>
#define MAX(alpha, beta) alpha > beta ? alpha : beta
#define MAXSIZE 100002
using namespace std;

// INT32_MAX : 2147483647

int main(void)
{
    int N, largest = 0;
    int hist[MAXSIZE] = {0};
    stack<int> index;
    scanf("%d", &N);
    for (int i=1; i<=N; i++)
        scanf("%d", &hist[i]);

    printf("\n");
    index.push(0);
    for (int i=1; i<=N+1; i++) {

        while (!index.empty() && hist[index.top()] > hist[i]) {
            int height = hist[index.top()];
            index.pop();
            int width = i - index.top() - 1;
            largest = MAX(largest, height * width);
            printf("%d ", i);
        }
        printf("\n");
        index.push(i);
    }
    printf("%d\n", largest);
    return 0;
}