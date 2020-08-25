// 연결 요소의 개수
#include <iostream>
#include <cstdio>
#include <stack>
#define MAXSIZE 1001


int main(void)
{
    int N, M, node1, node2;
    int mat[MAXSIZE][MAXSIZE] = {0};

    scanf("%d %d", &N, &M);

    for(int i=0; i<M; i++) {
        scanf("%d %d", &node1, &node2);
        mat[node1][node2] = 1;
        mat[node2][node1] = 1;
    }
    
    int count = 0, node;
    int visited[MAXSIZE] = {0};
    std::stack<int> st;

    for(int i=1; i<=N; i++) {
        for(int j=1; j<=N; j++) {
            if(mat[i][j] == 1 && visited[i] != 1) {
                st.push(i);
                count++;
                while(!st.empty()) {
                    node = st.top(); st.pop();
                    visited[node] = 1;
                    for(int k=1; k<=N; k++) {
                        if(mat[node][k] == 1 && visited[k] != 1)
                            st.push(k);
                    }
                }
            }
        }
    }
    for(int i=1; i<=N; i++)
        if(visited[i] == 0)
            count++;

    printf("%d\n", count);

    return 0;
}