// 스택
#include <iostream>
#include <cstdio>
#include <cstring>
#define MAXSIZE 10000
using namespace std;

int main(void)
{
    int N, top = -1, out = 0;
    int stack[MAXSIZE], output[MAXSIZE];
    char command[10];

    scanf("%d", &N);

    for(int i=0; i<N; i++) {
        scanf("%s", command);
        if (strcmp(command, "push") == 0)
            scanf("%d", &stack[++top]);
        else if (strcmp(command, "size") == 0)
            output[out++] = top + 1;
        else if (strcmp(command, "empty") == 0)
            output[out++] = (top == -1);
        else if (strcmp(command, "top") == 0) {
            if (top == -1) output[out++] = -1;
            else output[out++] = stack[top];
        }
        else if (strcmp(command, "pop") == 0) {
            if (top == -1) output[out++] = -1;
            else output[out++] = stack[top--];
        }
    }

    if (out == 0)
        return 0;
    else {
        for(int i=0; i<out; i++)
            printf("%d\n", output[i]);
    }
    return 0;
}