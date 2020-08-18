// 후위표기식
#include <iostream>
#include <cstdio>
#include <cstring>
#define MAXLEN 101
using namespace std;

int priority(char ch);

int main(void)
{
    int len, post = 0, top = -1;
    char infix[MAXLEN], postfix[MAXLEN] = {0}, op[MAXLEN];
    scanf("%s", infix);
    len = strlen(infix);

    for (int i=0; i<len; i++) {
        switch (infix[i])
        {
        case '(':
            op[++top] = infix[i];
            break;
        case ')':
            while (op[top] != '(')
                postfix[post++] = op[top--];
            top--;
            break;
        case '+': case '-': case '*': case '/':
            while ((top != -1) && (priority(infix[i]) <= priority(op[top])))
                postfix[post++] = op[top--];
            op[++top] = infix[i];
            break;
        default:
            postfix[post++] = infix[i];
            break;
        }
    }
    while (top != -1)
        postfix[post++] = op[top--];
    printf("%s\n", postfix);

    return 0;
}

int priority(char ch)
{
    switch (ch)
    {
    case '(': case ')':
        return 0;
    case '+': case '-':
        return 1;
    case '*': case '/':
        return 2;
    }
    return -1;
}
// A+B*(C+D)/E+F