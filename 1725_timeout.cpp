#include <iostream>
#include <cstdio>
#define MAXSIZE 100000
using namespace std;

// INT32_MAX : 2147483647

int histArea(int hist[], int index);

int main(void)
{
    int N, area, maxArea = 0;
    int hist[MAXSIZE];
    scanf("%d", &N);
    for(int i=0; i<N; i++)
        scanf("%d", &hist[i]);

    maxArea = hist[0];
    for(int i=1; i<N; i++) {
        area = histArea(hist, i);
        if (maxArea < hist[i])
            maxArea = hist[i];
        if (maxArea < area)
            maxArea = area;
    }

    printf("%d\n", maxArea);

    return 0;
}

int histArea(int hist[], int index)
{
    int area = 0, block;

    for(int height=1; height<=hist[index-1]; height++) {
        block = 0;
        for(int i=index; hist[i]>=height; i--) {
            block++;
        }
        if (area < block * height)
            area = block * height;
    }

    return area;
}