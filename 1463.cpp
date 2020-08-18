// 1로 만들기
#include <iostream>
#include <cstdio>
#include <algorithm>
#define MAXSIZE 1000001
using namespace std;

int arr[MAXSIZE];

int toOne(int num);

int main(void)
{
    int N, result;
    scanf("%d", &N);
    fill(arr, arr+MAXSIZE, -1);

    result = toOne(N);
    printf("%d\n", result);

    return 0;
}

int toOne(int num)
{
    int result;
    if (num == 1) return 0;
    if (arr[num] != -1) return arr[num];

    result = toOne(num - 1) + 1;
    if (num % 3 == 0) result = min(result, toOne(num / 3) + 1);
    if (num % 2 == 0) result = min(result, toOne(num / 2) + 1);
    arr[num] = result;
    return result;
}