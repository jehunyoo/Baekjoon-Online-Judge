// 후위표기식 2
#include <iostream>
#include <cstdio>
#include <cstring>
#include <stack>
#define MAXLEN 101
#define MAXOPERAND 26
using namespace std;

int main(void)
{
    int N, len;
    int operand[MAXOPERAND];
    char ch;
    char postfix[MAXLEN];
    double op1, op2;
    stack<double> st;

    scanf("%d", &N);
    scanf("%s", postfix);
    len = strlen(postfix);
    for (int i=0; i<N; i++)
        scanf("%d", &operand[i]);

    for (int i=0; i<len; i++) {
        ch = postfix[i];
        if (('A' <= ch) && (ch <= 'Z'))
            st.push(operand[ch - 'A']);
        else {
            op2 = st.top(); st.pop();
            op1 = st.top(); st.pop();
            switch (ch)
            {
                case '+':
                    st.push(op1 + op2);
                    break;
                case '-':
                    st.push(op1 - op2);
                    break;
                case '*':
                    st.push(op1 * op2);
                    break;
                case '/':
                    st.push(op1 / op2);
                    break;
            }
        }
    }
    printf("%.2f\n", st.top());

    return 0;
}