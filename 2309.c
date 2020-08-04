#include <stdio.h>
#define IN 9

int main(void)
{
    int heights[IN] = {0};
    int dwarfs[7] = {0};
    int excess = -100;
    int spy1 = -1, spy2 = -1;
    int temp;

    for(int i=0; i<IN; i++) {
        scanf("%d", &heights[i]);
        excess += heights[i];
    }

    for(int i=0; i<IN; i++) {
        if(heights[i] < excess)
            for(int j=i+1; j<IN; j++)
                if(heights[j] < excess && heights[i] + heights[j] == excess) {
                    spy1 = i;
                    spy2 = j;
                    break;
                }
        if(spy1 != -1 && spy2 != -1)
            break;
    }

    heights[spy1] = -1;
    heights[spy2] = -1;

    for(int i=0; i<IN; i++) {
        for(int j=i+1; j<IN; j++) {
            if(heights[i] > heights[j]) {
                temp = heights[j];
                heights[j] = heights[i];
                heights[i] = temp;
            }
        }
    }
    // printf("output\n");
    for(int i=2; i<IN; i++)
        printf("%d\n", heights[i]);

    return 0;
}

/*
#2309 일곱 난쟁이 (https://www.acmicpc.net/problem/2309)
solved by Jehun Yoo
2020.02.05
*/