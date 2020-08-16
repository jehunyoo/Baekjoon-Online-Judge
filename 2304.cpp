// 창고 다각형
#include <iostream>
#include <cstdio>
#include <stack>
#define MAX(alpha, beta) alpha > beta ? alpha : beta
#define MAXSIZE 1002

int main(void)
{
    int N, L, H, mid, right = 0, area = 0, idx;
    int house[MAXSIZE] = {0};
    std::stack<int> index;
    scanf("%d", &N);

    for (int i=0; i<N; i++) {
        scanf("%d %d", &L, &H);
        house[L] = H;
        right = MAX(right, L);
    }

    index.push(0);
    for (int i=1; i<=right; i++)
        if (house[i] != 0 && house[i] >= house[index.top()])
            index.push(i);
    
    mid = index.top();
    index.push(mid);
    index.push(right+1);

    for (int i=right; i>mid; i--)
        if (house[i] != 0 && house[i] >= house[index.top()])
            index.push(i);
    
    index.push(mid);

    int order = 0;

    while (!index.empty()) {
        
        idx = index.top(); index.pop();
        // printf(">>> %d ", idx);
        if (idx == mid && order == 1) {
            area += house[idx];
            order++;
        }
        
        else if (idx == right + 1)
            continue;
        
        else if ((mid < idx && idx <= right) || (order == 0)) {
            area += house[index.top()] * (index.top() - idx);
            if (order == 0)
                order++;
        }

        else if ((0 < idx && idx < mid) || (order == 2)) {  
            area += house[index.top()] * (idx - index.top());
            if (order == 2)
                order++;
        }
        // printf("%d\n", area);
    }
    
    printf("%d\n", area);


    return 0;
}