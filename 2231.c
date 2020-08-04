#include <stdio.h>

int deSum(int m);

int main(void)
{
    int N;

    scanf("%d", &N);

    for(int m=1; m<N; m++) {
        if(deSum(m) == N) {
            printf("%d\n", m);
            return 0;
        }
    }
    printf("0\n");

    return 0;
}

int deSum(int m)
{
    int sum = m;

    while(m>0) {
        sum += m % 10;
        m = (int)(m / 10);
    }
    
    return sum;
}