// 큐
#include <iostream>
#include <cstdio>
#include <cstring>
#define MAXLEN 10000
using namespace std;

int main(void)
{
    int N, front = 0, rear = 0, out = 0;
    int queue[MAXLEN], output[MAXLEN];
    char command[10];

    scanf("%d", &N);
    for (int i=0; i<N; i++) {
        scanf("%s", command);
        if (strcmp(command, "push") == 0) {
            rear = (rear + 1) % MAXLEN;
            scanf("%d", &queue[rear]);
        }
        else if (strcmp(command, "size") == 0) {
            output[out++] = (rear - front + MAXLEN) % MAXLEN;
        }
        else if (strcmp(command, "empty") == 0) {
            output[out++] = (front == rear);
        }
        else if (strcmp(command, "pop") == 0) {
            if (front == rear)
                output[out++] = -1;
            else {
                front = (front + 1) % MAXLEN;
                output[out++] = queue[front];
            }   
        }
        else if (strcmp(command, "front") == 0) {
            if (front == rear)
                output[out++] = -1;
            else
                output[out++] = queue[(front + 1) % MAXLEN];
        }
        else if (strcmp(command, "back") == 0) {
            if (front == rear)
                output[out++] = -1;
            else
                output[out++] = queue[rear];
        }
    }

    for (int i=0; i<out; i++)
        printf("%d\n", output[i]);

    return 0;
}