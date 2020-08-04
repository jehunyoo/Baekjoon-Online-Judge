#include <iostream>
#include <cstdio>
#include <cstring>
#include <stack>
#define MAXLEN 100
using namespace std;

bool checkPS(char ps[]);

int main(void)
{
    int T;
    char ps[MAXLEN];
    bool validity[MAXLEN];
    scanf("%d", &T);

    for(int i=0; i<T; i++) {
        scanf("%s", ps);
        validity[i] = checkPS(ps);
    }
    for(int i=0; i<T; i++) {
        if (validity[i])
            printf("YES\n");
        else
            printf("NO\n");
    } 
    return 0;
}

bool checkPS(char ps[])
{
    stack<char> st;
    int len = strlen(ps);
    char ch, top;

    for(int i=0; i<len; i++) {
        ch = ps[i];
        switch (ch)
        {
        case '(':
            st.push(ch);
            break;
        case ')':
            if (st.empty()) return false;
            else {
                top = st.top(); st.pop();
                if (top != '(' && ch == ')') return false;
            }
            break;
        }
    }
    if (!st.empty()) return false;
    else return true;
}