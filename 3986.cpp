#include <iostream>
#include <cstdio>
#include <cstring>
#include <stack>
#define MAXLEN 100001
using namespace std;

int isGoodWord(char word[], int len);

int main(void)
{
    int N, count = 0;
    char word[MAXLEN];

    scanf("%d", &N);

    for(int i=0; i<N; i++) {
        scanf("%s", word);
        count += isGoodWord(word, strlen(word));
    }

    printf("%d\n", count);

    return 0;
}

int isGoodWord(char word[], int len)
{
    stack<char> st;

    for(int i=0; i<len; i++){
        if (st.empty()) st.push(word[i]);
        else {
            if (st.top() == word[i]) st.pop();
            else st.push(word[i]);
        }
    }
    return st.empty();
}