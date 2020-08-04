// https://www.acmicpc.net/problem/10448
// 유레카 이론

#include <stdio.h>
#define MAX_SIZE 1000
#define Nth 45 // 45th triangular number is 2070. 1st ~ 44th triangular numbers will be used.

int is_eureka(int K);

int main(void)
{
    int T;
    int K[MAX_SIZE];

    scanf("%d", &T);
    for(int i=0; i<T; i++)
        scanf("%d", &K[i]);

    for(int i=0; i<T; i++)
        printf("%d\n", is_eureka(K[i]));

    return 0;
}

int is_eureka(int K)
{
    int tri[Nth] = {0};
    int ith = 45, T_i;

    for(int i=1; i<Nth; i++) {
        T_i = (i * (i + 1)) / 2;
        if(T_i < K)
            tri[i] = T_i;
        else {
            ith = i;
            break;
        }
    }

    for(int i=1; i<ith; i++) {
        for(int j=i; j<ith; j++) {
            for(int k=j; k<ith; k++) {
                if(tri[i] + tri[j] + tri[k] == K)
                    return 1;
            }
        }
    }

    return 0;
}