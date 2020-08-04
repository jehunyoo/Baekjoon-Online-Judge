#include <iostream>
#include <cstdio>
#include <cstring>
#define MAXLEN 100
using namespace std;

bool checkPS(char ps[]);

int main(void)
{
    int T;
    char ps[MAXLEN];
    bool valid[MAXLEN];
    scanf("%d", &T);

    for(int i=0; i<T; i++) {
        scanf("%s", ps);
        valid[i] = checkPS(ps);
    }
    for(int i=0; i<T; i++) {
        if (valid[i])
            printf("YES\n");
        else
            printf("NO\n");
    }
    return 0;
}

bool checkPS(char ps[])
{
    int len = strlen(ps), index = 0;
    char ch, top;
    char stack[MAXLEN];

    for(int i=0; i<len; i++) {
        ch = ps[i];
        if (ch == '(')
            stack[index++] = ch;
        else if (ch == ')') {
            if (index == 0) return false;
            else if (stack[--index] != '(') return false;
            else continue;
        }
    }
    if (index != 0) return false;
    return true;
}